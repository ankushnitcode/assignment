package com.example.assignment.kafka;

import com.example.QueueService;
import junit.framework.TestCase;
import org.junit.Assert;

import java.io.IOException;

public class KafkaQueueServiceTest extends TestCase {
    private final QueueService queueService = new KafkaQueueService();

    public void testPush() throws IOException {
        queueService.push("queueTest","hello");
    }

    public void testPull() throws IOException {
        queueService.push("queueTest","hello");
       var message=  queueService.pull("queueTest");
        Assert.assertEquals(message.getBody(),"hello");
    }

    public void testDelete() {
    }
}