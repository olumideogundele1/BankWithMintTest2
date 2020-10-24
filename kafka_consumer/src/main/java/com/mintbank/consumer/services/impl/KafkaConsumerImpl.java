package com.mintbank.consumer.services.impl;


import com.mintbank.consumer.dto.MintResponse;
import com.mintbank.consumer.services.KafkaConsumer;
import com.mintbank.consumer.utilities.MintResponseListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class KafkaConsumerImpl implements KafkaConsumer {

    private MintResponseListener mintResponseListener;

    public KafkaConsumerImpl(MintResponseListener mintResponseListener) {
        this.mintResponseListener = mintResponseListener;
    }


    @Override
    public Set<MintResponse> consume() {
        Set<MintResponse> responseSet = new HashSet<>();
        //return mintResponseListener.onMessage(new MintResponse());
        responseSet =  mintResponseListener.getMintResponse();

        return responseSet;
    }
}
