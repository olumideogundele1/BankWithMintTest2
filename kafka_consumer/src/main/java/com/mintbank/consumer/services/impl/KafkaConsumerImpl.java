package com.mintbank.consumer.services.impl;


import com.mintbank.consumer.dto.MintResponse;
import com.mintbank.consumer.services.KafkaConsumer;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerImpl implements KafkaConsumer {


    @Override
    public MintResponse consume(MintResponse mintResponse) {
        return null;
    }
}
