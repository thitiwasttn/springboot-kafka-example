package com.thitiwas.example.kafkaone;

import com.thitiwas.example.kafkaone.model.KafKaModelOne;
import com.thitiwas.example.kafkaone.model.KafKaModelTwo;
import com.thitiwas.example.kafkaone.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = KafkaOneApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class KafkaTest {

    @Autowired
    private KafkaService kafkaService;

    @Test
    public void testSendMessageToTopic() {
        KafKaModelOne kafKaModelOne = KafKaModelOne.builder()
                .messageOne("hello message one")
                .messageTwo("hello message two")
                .kafKaModelTwo(List.of(KafKaModelTwo.builder().messageOne("message one one").build(),
                        KafKaModelTwo.builder().messageOne("message one two").build()))
                .build();
        kafkaService.sendMessage("topicA", kafKaModelOne);
    }

    @Test
    public void testSendMessageString() {
        kafkaService.sendMessage("topicB", "hi there !!");
    }
}
