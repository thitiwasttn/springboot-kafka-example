package com.thitiwas.example.kafkatwo.service;

import com.thitiwas.example.kafkaone.model.KafKaModelOne;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {
    private final KafkaTemplate<String, KafKaModelOne> kafkaTemplate;

    @Autowired
    public KafkaService(KafkaTemplate<String, KafKaModelOne> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
    * ต้องใช้ obj จาก class เดียวกันจากตัวส่ง คือต้องแชร์ class กัน ดูยุ่งยาก ส่งเป็น json string แล้ว pars ง่ายกว่า
    * */
    @KafkaListener(topics = "topicA")
    public void messageListenerTopicA(KafKaModelOne kafKaModelOne) {
        log.debug("kafka message : {}", kafKaModelOne);
    }

    @KafkaListener(topics = "topicB")
    public void messageListenerTopicB(String message) {
        log.debug("messageListenerTopicB message : {}", message);
    }
}
