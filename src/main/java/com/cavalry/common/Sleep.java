package com.cavalry.common;

import java.util.concurrent.TimeUnit;

/**
 * author: master
 * date: 2015/12/29
 */
public class Sleep {

    public static void sleep(int time, TimeUnit unit) {
        try {
            unit.sleep(time);
        } catch (InterruptedException ignore) {
        }
    }
}
