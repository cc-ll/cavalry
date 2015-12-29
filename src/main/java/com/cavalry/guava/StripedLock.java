package com.cavalry.guava;

import com.google.common.util.concurrent.Striped;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * author: master
 * date: 2015/12/29
 */
public class StripedLock {

    private Striped<Lock> striped = Striped.lazyWeakLock(10);

    public void concurrentControl(Object obj) {
        Lock lock = striped.get(obj);
        if (lock == null) {
            System.out.println("lock is null");
            return;
        }
        if (lock.tryLock()) {
            try {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sleep 10s ...");
            } finally {
                lock.unlock();
            }
        }
    }
}
