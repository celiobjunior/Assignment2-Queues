/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque (runs basic unit tests)
 *  Dependencies: None
 *
 *  Data structure implementation of a generic double-ended queue (deque).
 *
 *  Provides functionality to add or remove items from either the front
 *  or the back end of the queue. Implemented using a doubly-linked list
 *  to ensure constant time performance for all core operations.
 *
 *  Features:
 *    - Generic type <Item> for storing any object type.
 *    - Supports addFirst(), addLast(), removeFirst(), removeLast().
 *    - Includes isEmpty() and size() methods.
 *    - Implements Iterable<Item> for forward iteration (front to back).
 *    - Strict checks for null arguments and operations on empty deque.
 *
 *  Performance:
 *    - All constructor and instance methods (add/remove/size/isEmpty)
 *      operate in constant worst-case time.
 *    - Iterator operations (hasNext, next) also run in constant time.
 *    - Memory usage is linear, proportional to the number of items (N),
 *      typically ~ 48N + 192 bytes for a standard object overhead model.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private Node head; // first
    private Node tail;  // last
    private int size;

    // construct an empty deque
    public Deque()
    {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private class Node
    {
        Item item;
        Node next;
        Node prev;
        private Node(Item item)
        {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return size == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null) throw new IllegalArgumentException("Calling addFirst() with null argument.");

        if (isEmpty())
        {
            head = new Node(item);
            tail = head;
        } else
        {
            Node newFirst = new Node(item);
            Node oldFirst = head;

            newFirst.next = oldFirst;
            oldFirst.prev = newFirst;

            head = newFirst;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) throw new IllegalArgumentException("Calling addLast() with null argument.");

        if (isEmpty())
        {
            head = new Node(item);
            tail = head;
        } else
        {
            Node newLast = new Node(item);

            tail.next = newLast;
            newLast.prev = tail;

            tail = newLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty()) throw new NoSuchElementException("Calling removeFirst() on an empty Deque.");

        Item item = head.item;
        Node oldHead = head;
        head = head.next;
        if (size > 1) head.prev = null; // when size = 1, head.prev is already null
        size--;

        // to avoid loitering
        oldHead.item = null;
        oldHead.next = null;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty()) throw new NoSuchElementException("Calling removeLast() on an empty Deque.");

        Item item = tail.item;
        if (size == 1)
        {
            head = null;
            tail = null;
        } else
        {
            Node oldTail = tail;
            tail = tail.prev;
            tail.next = null;

            // to avoid loitering
            oldTail.prev = null;
            oldTail.item = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new Iterator<>()
        {
            private Node current = head;
            public boolean hasNext()
            {
                return current != null;
            }
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
            public Item next()
            {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(20);
        deque.addFirst(10);
        deque.addLast(30);

        for (Integer i : deque)
        {
            System.out.println(i);
        }

        StdOut.println("dequeue: " + deque.removeFirst());
        StdOut.println("dequeue: " + deque.removeLast());
        StdOut.println("dequeue: " + deque.removeLast());
    }
}