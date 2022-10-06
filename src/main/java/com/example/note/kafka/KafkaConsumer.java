package com.example.note.kafka;

import com.example.note.config.KafkaConsumerConfig;
import com.example.note.dto.CoffeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.note.constant.KafkaConst.TOPIC_JSON;
import static com.example.note.constant.KafkaConst.TOPIC_TEST;

@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = TOPIC_TEST, groupId = KafkaConsumerConfig.GROUP_1)
    public void consume(String message) {
        LOGGER.info("Consumed message: {} ", message);
    }


    @KafkaListener(topics = TOPIC_JSON, groupId = KafkaConsumerConfig.GROUP_2, containerFactory = "userKafkaListenerFactory")
    public void consumeJson(CoffeeDto coffeeDto) throws InterruptedException {
        LOGGER.info("Consumed JSON Message: {} ", coffeeDto);
    }

}
