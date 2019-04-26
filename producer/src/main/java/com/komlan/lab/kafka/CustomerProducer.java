package com.komlan.lab.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class CustomerProducer {
    final static String TOPIC = "customer-serialization-topic";
    KafkaProducer<String, Customer> producer = null;

    public void configure(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.komlan.lab.kafka.CustomerSerializer");
        props.put("group.id", "customerproducer");
        producer = new KafkaProducer<>(props);
    }

    public void run(){
        int k =25;
        for (int i=1+k; i<=5+k; i++){
            Customer c = new Customer(i, "Customer " + i);
            ProducerRecord<String, Customer> record = new ProducerRecord<String, Customer>(TOPIC, c);
            try {
                producer.send(record);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void close(){
        producer.close();
    }
}