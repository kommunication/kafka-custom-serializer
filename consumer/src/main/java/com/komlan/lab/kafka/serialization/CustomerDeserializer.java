package com.komlan.lab.kafka.serialization;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;

import com.komlan.lab.kafka.model.*;

import org.apache.kafka.common.serialization.*;

public class CustomerDeserializer implements Deserializer<Customer> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Customer deserialize(String topic, byte[] data) {
        if (data.length == 0)
            return null;
        
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int id = buffer.getInt();
        int stringSize = buffer.getInt();
        byte[] serilizedName = new byte[stringSize];
        buffer.get(serilizedName, 0, stringSize);
        String name = new String(serilizedName, Charset.forName("UTF-8"));
        return new Customer(id, name);
    }

    @Override
    public void close() {

    }

}