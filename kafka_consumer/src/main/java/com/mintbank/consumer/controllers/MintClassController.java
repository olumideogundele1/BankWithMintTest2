package com.mintbank.consumer.controllers;

import com.mintbank.consumer.services.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
@RequestMapping("/mint/bins")
public class MintClassController {

    private KafkaConsumer kafkaConsumer;


    public MintClassController(KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }


    @GetMapping("/list-of-bins")
    @ResponseBody
    public String listOfBins(Model model){
        //model.addAttribute("bins",kafkaConsumer.consume());
        log.info("Response Set " + kafkaConsumer.consume());
        return "Welcome Kafka";
    }


}
