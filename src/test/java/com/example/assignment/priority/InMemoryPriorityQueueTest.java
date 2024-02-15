package com.example.assignment.priority;

import junit.framework.TestCase;
import org.junit.Assert;

public class InMemoryPriorityQueueTest extends TestCase {
    private InMemoryPriorityQueue inMemoryPriorityQueue;

    @Override
    public void setUp() throws Exception {
        inMemoryPriorityQueue = new InMemoryPriorityQueue(20);
    }

    public void testEnqueue() {
        inMemoryPriorityQueue.enqueue(100,1);
        inMemoryPriorityQueue.enqueue(80,2);
        var x  =  inMemoryPriorityQueue.peek().data;
        Assert.assertEquals(x,100);

    }

    public void testDequeue() {
        inMemoryPriorityQueue.enqueue(100,1);
        inMemoryPriorityQueue.enqueue(80,2);
        inMemoryPriorityQueue.dequeue();
        var x  =  inMemoryPriorityQueue.peek().data;
        Assert.assertEquals(x,80);
    }

    public void testPeek() {
        inMemoryPriorityQueue.enqueue(100,2);
        inMemoryPriorityQueue.enqueue(80,1);
       var x  =  inMemoryPriorityQueue.peek().data;
       Assert.assertEquals(80,x);
    }
}