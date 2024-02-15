package com.example;

import java.io.IOException;

public interface QueueService {
  /** push a message onto a queue. */
  public void push(String queueUrl, String messageBody) throws IOException;

  /** retrieves a single message from a queue. */
  public Message pull(String queueUrl) throws IOException;

  /** deletes a message from the queue that was received by pull(). */
  public void delete(String queueUrl, String receiptId);
}
