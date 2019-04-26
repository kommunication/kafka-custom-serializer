package com.komlan.lab.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main App!
 *
 */
public class CustomConsumerApp 
{
    public static void main( String[] args )
    {
        Logger logger = LoggerFactory.getLogger(CustomConsumerApp.class);
        logger.info( "Starting ..." );
        CustomerConsumer consumer = new CustomerConsumer();
        consumer.configure();
        consumer.run();
        consumer.close();

        logger.info("Done!");
    }
}
