package com.mintbank.consumer.services;

import com.mintbank.consumer.dto.MintResponse;

import java.util.Set;

public interface KafkaConsumer {

    Set<MintResponse> consume();
}
