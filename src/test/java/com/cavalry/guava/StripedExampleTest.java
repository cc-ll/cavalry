package com.cavalry.guava;

import com.cavalry.common.Sleep;
import com.google.common.util.concurrent.Striped;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import static org.junit.Assert.*;

/**
 * author: master
 * date: 2015/12/29
 */
public class StripedExampleTest {

    private static final Random RANDOM = new Random(923);

    @Test
    public void testName() throws Exception {
        final Striped<Lock> striped = Striped.lazyWeakLock(10);
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Lock lock = striped.get(RANDOM.nextInt(100));
                    assertNotNull(lock);
                    try {
                        lock.lock();
                        Sleep.sleep(1, TimeUnit.SECONDS);
                        System.out.println(lock);
                        System.out.println("sp 1 seconds to op common resource ...");
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }
        Sleep.sleep(2, TimeUnit.MINUTES);
    }
}