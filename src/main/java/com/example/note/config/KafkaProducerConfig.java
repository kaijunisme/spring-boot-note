package com.example.note.config;

import com.example.note.dto.CoffeeDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static com.example.note.constant.KafkaConst.TOPIC_TEST;

@Configuration
public class KafkaProducerConfig {

    public static final String DEFAULT_SERVER = "127.0.0.1:9091";

    @Bean
    public ProducerFactory<String, CoffeeDto> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        // 設定Apache Kafka Server 位址
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_SERVER);

        // 設定Key 和Value 序列化方式
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, CoffeeDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * 透過注入一個NewTopic 類別的Bean 來建立Topic，若Topic 已存在則會忽略。
     */
    @Bean
    public NewTopic defaultTopic() {
        return TopicBuilder.name(TOPIC_TEST)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
