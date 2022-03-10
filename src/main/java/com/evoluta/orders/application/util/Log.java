package com.evoluta.orders.application.util;

import com.evoluta.orders.application.rest.OrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    Logger LOG = LoggerFactory.getLogger(Log.class);
    public static void info(String message)  {
        Log.info(message);
    }
}
