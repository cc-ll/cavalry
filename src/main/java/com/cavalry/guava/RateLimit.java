package com.cavalry.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * author: master
 * date: 2015/12/29
 */
public class RateLimit {

    private RateLimiter rateLimiter = RateLimiter.create(1024);

    public void rateControl() {
        double sleepTime = rateLimiter.acquire();
        if (sleepTime > 0.0) {
            try {
                TimeUnit.SECONDS.sleep((long) sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("here ...");
    }

    public void rateControl2() {
        if (rateLimiter.tryAcquire()) {
            System.out.println("here ...");
        }
    }

    public void rateControl3() {
        if (rateLimiter.tryAcquire(10, TimeUnit.SECONDS)) {
            System.out.println("here ...");
        }
    }
}
