package dailymixes;

import queue.EmptyQueueException;
import student.TestCase;

/**
 * A test class for ArrayQueue called ArrayQueue.
 * 
 * @author Sicheng Fan
 * @version 03/30/2023
 *
 */
public class ArrayQueueTest extends TestCase {

    private ArrayQueue<String> queue;
    private ArrayQueue<Integer> queue2;
    private ArrayQueue<Integer> queue3;
    private ArrayQueue<Integer> queue4;

    /**
     * Set up for testCase.
     */
    public void setUp() {
        queue = new ArrayQueue<>();
        queue2 = new ArrayQueue<>();
        queue3 = new ArrayQueue<>();
        queue4 = new ArrayQueue<>(4);

    }


    /**
     * test enqueue method.
     */
    public void testEnqueue() {
        queue.enqueue("One");
        queue.enqueue("Two");
        queue.enqueue("Three");
        assertEquals("[One, Two, Three]", queue.toString());
    }


    /**
     * test dequeue method.
     */
    public void testDequeue() {
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        assertEquals(1, (int)queue2.dequeue());
        assertEquals(2, (int)queue2.dequeue());
        assertEquals(3, (int)queue2.dequeue());
        assertTrue(queue2.isEmpty());
    }


    /**
     * test dequeueEmpty method.
     */
    public void testDequeueEmpty() {
        Exception thrown = null;
        try {
            queue.dequeue();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyQueueException);

    }


    /**
     * test isEmpty method.
     */
    public void testIsEmpty() {
        assertTrue(queue2.isEmpty());
        queue2.enqueue(1);
        assertFalse(queue2.isEmpty());
    }


    /**
     * test equals method.
     */
    public void testEquals() {
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        queue3.enqueue(1);
        queue3.enqueue(2);
        queue3.enqueue(3);
        assertTrue(queue2.equals(queue3));
    }


    /**
     * test getFront method.
     */
    public void testGetFront() {
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        assertEquals("First", queue.getFront());
    }


    /**
     * test getFrontEmpty method.
     */
    public void testGetFrontEmpty() {
        Exception thrown = null;
        try {
            queue2.getFront();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyQueueException);

    }


    /**
     * test isFull method.
     */
    public void testIsFull() {
        assertFalse(queue4.isFull());
        for (int i = 1; i <= 4; i++) {
            queue4.enqueue(i);
        }
        assertTrue(queue4.isFull());
    }


    /**
     * test toArray method.
     */
    public void testToArray() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        Object[] expected = new Object[] { "A", "B", "C" };
        Object[] actual = queue.toArray();
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }

    }


    /**
     * test toArrayEmpty method.
     */
    public void testToArrayEmpty() {
        Exception thrown = null;
        try {
            queue2.toArray();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyQueueException);

    }


    /**
     * test clear method.
     */
    public void testClear() {
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        queue2.clear();
        assertTrue(queue.isEmpty());
    }


    /**
     * test toString method
     */
    public void testToString() {
        assertEquals("[]", queue.toString());
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals("[A, B, C]", queue.toString());
    }


    /**
     * test getLengthOfUnderlyingArray method.
     */
    public void testGetLengthOfUnderlyingArray() {
        assertEquals(5, queue4.getLengthOfUnderlyingArray());
    }


    /**
     * test size method.
     */
    public void testSize() {
        assertEquals(0, queue.getSize());
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals(3, queue.getSize());
    }
}
