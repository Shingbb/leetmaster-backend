package com.shing.leetmaster.aop;

import com.shing.leetmaster.annotation.AuthCheck;
import com.shing.leetmaster.common.ErrorCode;
import com.shing.leetmaster.exception.BusinessException;
import com.shing.leetmaster.model.entity.User;
import com.shing.leetmaster.model.enums.UserRoleEnum;
import com.shing.leetmaster.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限校验 AOP
 *
 * @author Shing
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 拦截器方法，用于检查注解@AuthCheck标记的方法的访问权限
     *
     * @param joinPoint 切入点对象，用于获取被拦截的方法信息
     * @param authCheck 注解对象，用于获取被拦截方法上的权限检查信息
     * @return 方法返回值
     * @throws Throwable 如果权限检查失败，可能抛出异常
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 获取方法上必须的角色
        String mustRole = authCheck.mustRole();
        // 获取请求属性
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        // 获取请求对象
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        User loginUser = userService.getLoginUser();
        // 根据角色标识获取对应的枚举
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 不需要权限，放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        // 必须有该权限才通过
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        // 如果用户角色无效，则抛出无权限异常
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 如果被封号，直接拒绝
        if (UserRoleEnum.BAN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 必须有管理员权限
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum)) {
            // 用户没有管理员权限，拒绝
            if (!UserRoleEnum.ADMIN.equals(userRoleEnum)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}
