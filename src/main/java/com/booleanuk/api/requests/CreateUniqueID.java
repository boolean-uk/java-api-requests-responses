package com.booleanuk.api.requests;

import java.util.concurrent.atomic.AtomicLong;

public class CreateUniqueID {



    private static AtomicLong idCounter = new AtomicLong();

    public static String createID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }


}
