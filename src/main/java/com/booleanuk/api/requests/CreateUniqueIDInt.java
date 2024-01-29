package com.booleanuk.api.requests;

import java.util.concurrent.atomic.AtomicLong;

public class CreateUniqueIDInt {

    private static AtomicLong idCounter = new AtomicLong();

    public static int createID()
    {
        return (int) idCounter.getAndIncrement();
    }


}
