package com.mintbank.consumer.services;

import com.mintbank.consumer.dto.MintResponse;

public interface KafkaConsumer {

    MintResponse consume(MintResponse mintResponse);
}
