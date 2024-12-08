package com.shing.leetmaster.aop;

import com.shing.leetmaster.annotation.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁切面
 *
 * @author Shing
 */
public class DistributedLockAspect {

    // 使用Resource注解注入RedissonClient实例，用于分布式锁的管理
    @Resource
    private RedissonClient redissonClient;

    // 定义一个环绕通知，用于处理带有distributedLock注解的方法
    @Around("@annotation(distributedLock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Exception {
        // 从注解中获取锁的键、等待时间和租约时间以及时间单位
        String lockKey = distributedLock.key();
        long waitTime = distributedLock.waitTime();
        long leaseTime = distributedLock.leaseTime();
        TimeUnit timeUnit = distributedLock.timeUnit();

        // 根据锁键获取Redisson中的锁实例
        RLock lock = redissonClient.getLock(lockKey);

        // 初始化获取锁的状态为false
        boolean acquired = false;
        try {
            // 尝试获取锁
            acquired = lock.tryLock(waitTime, leaseTime, timeUnit);
            if (acquired) {
                // 获取锁成功，执行目标方法
                return joinPoint.proceed();
            } else {
                // 获取锁失败，抛出异常或处理逻辑
                throw new RuntimeException("Could not acquire lock: " + lockKey);
            }
        } catch (Throwable e) {
            // 捕获到异常时，重新抛出异常
            throw new Exception(e);
        } finally {
            if (acquired) {
                // 释放锁
                lock.unlock();
            }
        }
    }
}
