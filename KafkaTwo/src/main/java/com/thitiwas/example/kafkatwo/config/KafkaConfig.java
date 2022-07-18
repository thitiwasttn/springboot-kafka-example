package com.thitiwas.example.kafkatwo.config;

import com.thitiwas.example.kafkaone.model.KafKaModelOne;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> map = new HashMap<>();

        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_DOC, JsonSerializer.class);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, "json");

        return map;
    }

    /**
     * config ให้รับ obj ได้
     * ต้องใช้ obj จาก class เดียวกันจากตัวส่ง คือต้องแชร์ class กัน ดูยุ่งยาก ส่งเป็น json string แล้ว pars ง่ายกว่า
     */
    @Bean
    public ConsumerFactory<String, KafKaModelOne> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(KafKaModelOne.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafKaModelOne> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafKaModelOne> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
