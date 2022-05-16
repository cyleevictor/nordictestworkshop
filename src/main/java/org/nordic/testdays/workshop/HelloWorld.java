package org.nordic.testdays.workshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    public static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public String send(String name) {
        logger.info("Welcome to Nordic testing day, {}", name);
        return name;
    }
}
