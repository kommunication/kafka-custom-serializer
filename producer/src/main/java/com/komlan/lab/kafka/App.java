package com.komlan.lab.kafka;

/**
 * Main App!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Starting producer ..." );

        CustomerProducer producer = new CustomerProducer();
        producer.configure();
        producer.run();
        producer.close();

        System.out.println("Done!!!");
    }
}
