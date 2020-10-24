package com.mintbank.consumer.utilities;

import com.mintbank.consumer.dto.MintResponse;
import com.mintbank.consumer.services.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class MintResponseListener implements ConsumerSeekAware {

    @Value("${spring.kafka.template.default-topic}")
    private String kafkaTopic;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupie;

    final String groupString = groupie;

    private KafkaConsumer kafkaConsumer;

    public MintResponseListener(KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @KafkaListener(topics = "com.ng.vela.even.card_verified",containerFactory = "listenerFactory")
    public MintResponse onMessage(MintResponse mintResponse){
        log.info("Mint Response " + mintResponse.toString());
        kafkaConsumer.consume(mintResponse);

        return mintResponse;
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        assignments.forEach((t, o) -> callback.seekToBeginning(t.topic(), t.partition()));

    }
}
