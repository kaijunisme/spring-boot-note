package com.example.note.controller;

import com.example.note.dto.CoffeeDto;
import com.example.note.kafka.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.note.constant.KafkaConst.TOPIC_TEST;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaTemplate<String, CoffeeDto> kafkaTemplate;

    @PostMapping
    public void post(@RequestBody CoffeeDto coffeeDto) {
        LOGGER.info("Produce Message: {} ", coffeeDto);
        kafkaTemplate.send(TOPIC_TEST, coffeeDto);
    }

}
