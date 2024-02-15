package com.example.assignment.kafka;

import com.example.Message;
import com.example.QueueService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class KafkaQueueService implements QueueService {

    private final UpstashKafkaProducer producer;
    private final UpstashKafkaConsumer consumer;

    public KafkaQueueService() {
        this.producer = new UpstashKafkaProducer();
        this.consumer = new UpstashKafkaConsumer();
    }

    @Override
    public void push(String topic, String messageBody) throws IOException {
        producer.produceMessage(topic, messageBody);
    }

    @Override
    public Message pull(String topic) throws IOException {
        var responseBody = consumer.consume("GROUP_NAME",
                "GROUP_INSTANCE_NAME", topic).getBody();
        ObjectMapper mapper = new ObjectMapper();
        List<QueueResponse> queueResponseList = mapper.readValue(responseBody, new TypeReference<List<QueueResponse>>() {});
        return new Message(queueResponseList.stream().map(QueueResponse::getValue).collect(Collectors.joining(",")));
    }

    @Override
    public void delete(String queueUrl, String receiptId) {
        // For Kafka, the offset is automatically managed by consumer groups.
        // We don't need to explicitly delete messages as they are consumed by the consumer group.
        // In Kafka, offsets are committed based on the consumption progress of consumer groups.
    }
}
