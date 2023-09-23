package dailymixes;

import queue.EmptyQueueException;
import queue.QueueInterface;

/**
 * A class called ArrayQueue<T> that implements QueueInterface<T>.
 * 
 * @author Sicheng Fan
 * @version 03/30/2023
 * @param <T>
 *            the generic type.
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    /**
     * the DEAFAULT_CAPACITY cannot change.
     */
    public static final int DEFAULT_CAPACITY = 20;
    private T[] queue;
    private int dequeueIndex;
    private int size;
    private int enqueueIndex;

    /**
     * Constructs an ArrayQueue with default capacity.
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
        this.enqueueIndex = 0;
        this.dequeueIndex = 0;
    }


    /**
     * Constructs an ArrayQueue with a specific capacity.
     *
     * @param size
     *            the capacity of the queue
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int size) {
        queue = (T[])new Object[size + 1];
        this.enqueueIndex = 0;
        this.dequeueIndex = 0;
    }


    /**
     * Clears the queue, removing all elements.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        size = 0;
        dequeueIndex = 0;
        enqueueIndex = 0;
        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
    }


    /**
     * Ensures the queue has enough capacity to add a new element. If the queue
     * is
     * full, the size of the queue is doubled and the elements are copied to the
     * new
     * array.
     */
    private void ensureCapacity() {
        if (size == queue.length - 1) {
            @SuppressWarnings("unchecked")
            T[] newQueue = (T[])new Object[2 * (queue.length - 1) + 1];
            for (int i = 0; i < size; i++) {
                newQueue[i] = queue[(dequeueIndex + i) % queue.length];
            }
            queue = newQueue;
            dequeueIndex = 0;
            enqueueIndex = size;
        }
    }


    /**
     * Increments an index in the circular queue.
     *
     * @param index
     *            the index to increment
     * @return the incremented index
     */
    private int incrementIndex(int index) {
        return (queue.length + index + 1) % queue.length;
    }


    /**
     * Returns a string representation of the queue.
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int index = dequeueIndex;

        for (int i = 0; i < size; i++) {
            sb.append(queue[index].toString());
            if (i < size - 1) {
                sb.append(", ");
            }
            index = incrementIndex(index);
        }
        sb.append("]");

        return sb.toString();
    }


    /**
     * Returns whether the queue is empty or not.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Returns whether two ArrayQueue objects are equal to each other.
     *
     * @param obj
     *            the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArrayQueue)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        ArrayQueue<T> other = (ArrayQueue<T>)obj;
        if (size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            T element = queue[(dequeueIndex + i) % queue.length];
            Object otherElement = other.queue[(other.dequeueIndex + i)
                % other.queue.length];
            if (!element.equals(otherElement)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Add the given element to the queue
     */
    @Override
    public void enqueue(T t) {
        ensureCapacity();
        queue[enqueueIndex] = t;
        enqueueIndex = incrementIndex(enqueueIndex);
        size++;
    }


    /**
     * Removes element at the front of the queue
     * 
     * @throws EmptyQueueException
     *             if the queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        T t = queue[dequeueIndex];
        dequeueIndex = incrementIndex(dequeueIndex);
        size--;
        return t;
    }


    /**
     * 
     * Returns the length of the underlying array that stores the elements of
     * the
     * queue.
     * 
     * @return the length of the underlying array
     */
    public int getLengthOfUnderlyingArray() {
        return queue.length;
    }


    /**
     * 
     * Retrieves the element at the front of the queue without removing it.
     * 
     * @return the element at the front of the queue
     * @throws EmptyQueueException
     *             if the queue is empty
     */
    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return queue[dequeueIndex];
    }


    /**
     * 
     * Returns the number of elements in the queue.
     * 
     * @return the number of elements in the queue
     */
    public int getSize() {
        return size;
    }


    /**
     * 
     * Returns a boolean indicating whether the queue is full or not.
     * 
     * @return a boolean indicating whether the queue is full or not
     */
    public boolean isFull() {
        return size == getLengthOfUnderlyingArray() - 1;
    }


    /**
     * 
     * Returns an array containing all elements in the queue in the order they
     * would
     * be dequeued.
     * 
     * @return an array containing all elements in the queue
     * @throws EmptyQueueException
     *             if the queue is empty
     */
    public Object[] toArray() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        Object[] array = new Object[size];
        for (int i = 0, j = dequeueIndex; j != enqueueIndex; i++, j =
            incrementIndex(j)) {
            array[i] = queue[j];
        }
        return array;
    }

}
