package com.komlan.lab.kafka;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

public class CustomerSerializer implements Serializer<Customer> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    /**
     * We are serializing as: 4 byte int for customerId 4 byte int for length of N
     * customerName in UTF-8 bytes (0 if name is null) N byte representing
     * customerName in UTF-8
     */
    public byte[] serialize(String topic, Customer data) {
        byte[] serializedName = null;
        int stringSize = 0;
        if (data == null) {
            return null;
        } else {
            try {
                if (data.getCustomerName() != null) {
                
                        serializedName = data.getCustomerName().getBytes("UTF-8");
                        stringSize = serializedName.length;
                    
                } else  {
                    serializedName = new byte[0];
                    stringSize = 0;
                }
                
                ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
                buffer.putInt(data.getCustomerID());
                buffer.putInt(stringSize);
                buffer.put(serializedName);

                return buffer.array();
            } catch (UnsupportedEncodingException e) {
                throw new SerializationException("Error when serializing Customer to byte[] " + e);
            }
        }
            
      
    }

    @Override
    public void close() {

    }
    
}