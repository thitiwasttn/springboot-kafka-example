package com.thitiwas.example.kafkaone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafKaModelOne {
    private String messageOne;
    private String messageTwo;
    private List<KafKaModelTwo> kafKaModelTwo;
}
