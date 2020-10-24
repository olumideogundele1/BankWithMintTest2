package com.mintbank.consumer.utilities;

import com.mintbank.consumer.dto.MintResponse;
import com.mintbank.consumer.services.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class MintResponseListener extends KafkaProperties.Listener implements ConsumerSeekAware {

    @Value("${spring.kafka.template.default-topic}")
    private String kafkaTopic;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupie;


    final String groupString = groupie;

    private int count = 0;

    //private KafkaConsumer kafkaConsumer;

    private Set<MintResponse> responses = new HashSet<>();

    public MintResponseListener() {
       // this.kafkaConsumer = kafkaConsumer;
    }

    @KafkaListener(topics = "com.ng.vela.even.card_verified",containerFactory = "listenerFactory")
    public void onMessage(@Payload Set<MintResponse> mintResponses){
        log.info("Mint Response " + mintResponses);
      //  Set<MintResponse> responseSet = new LinkedHashSet<>();
        responses = mintResponses;
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        assignments.forEach((t, o) -> callback.seekToBeginning(t.topic(), t.partition()));
    }

    public Set<MintResponse> getMintResponse(){
        return responses;
    }
}
