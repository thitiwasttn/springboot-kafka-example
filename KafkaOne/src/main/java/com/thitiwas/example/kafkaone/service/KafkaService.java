package com.thitiwas.example.kafkaone.service;

import com.thitiwas.example.kafkaone.model.KafKaModelOne;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class KafkaService {
    private final KafkaTemplate<String, KafKaModelOne> kafkaTemplate;
    private final KafkaTemplate<String, String> kafkaTemplateString;

    @Autowired
    public KafkaService(KafkaTemplate<String, KafKaModelOne> kafkaTemplate, KafkaTemplate<String, String> kafkaTemplateString) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateString = kafkaTemplateString;
    }

    public void sendMessage(String topic, KafKaModelOne kafKaModelOne) {
        ListenableFuture<SendResult<String, KafKaModelOne>> sendResult = kafkaTemplate.send(topic, kafKaModelOne);
        sendResult.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("send fail :{}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, KafKaModelOne> result) {
                log.info("send success :{}", result);
            }
        });
    }

    public void sendMessage(String topic, String message) {
        ListenableFuture<SendResult<String, String>> sendResult = kafkaTemplateString.send(topic, message);
        sendResult.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("send fail :{}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("send success :{}", result);
            }
        });
    }
}
