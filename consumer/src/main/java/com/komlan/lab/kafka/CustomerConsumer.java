package com.komlan.lab.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.komlan.lab.kafka.model.*;
import com.komlan.lab.kafka.serialization.CustomerDeserializer;

import java.util.Collections;
import java.util.Properties;

public class CustomerConsumer {
    final static String TOPIC = "customer-serialization-topic";
    KafkaConsumer<String, Customer> consumer = null;
    Logger logger = LoggerFactory.getLogger(CustomerConsumer.class);

    public void configure(Properties props){
        consumer = new KafkaConsumer<>(props);
        logger.info(props.toString());
        logger.info("Topic: " + TOPIC);
        consumer.subscribe(Collections.singletonList(TOPIC));
        
    }

    public void configure() {
        Properties props = getDefaultProperties();
        configure(props);
    }

    private Properties getDefaultProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", CustomerDeserializer.class.getCanonicalName());
        props.put("group.id", "customerconsumer");
        return props;
    }

    public void run() {
        while (true) {
            ConsumerRecords<String, Customer> records = consumer.poll(100);

            for (ConsumerRecord<String, Customer> rec : records) {
                //System.out.println(rec.value().getCustomerID() + ": " + rec.value().getCustomerName());
                logger.info(rec.value()!=null ? rec.value().toString() : "");
            }
        }
       
    }
    public void close() {

    }
}