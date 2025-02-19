package com.shing.leetmaster.esdao;

import com.shing.leetmaster.annotation.DistributedLock;

public class DistributedLockTest {

    @DistributedLock(key = "testLock", leaseTime = 20000, waitTime = 5000)
    public void testLock() throws InterruptedException {
        System.out.println("print print");
        Thread.sleep(5000L);
    }
}
