/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue (runs basic unit tests)
 *  Dependencies: None
 *
 *  Data structure implementation of a generic randomized queue.
 *
 *  Similar to a standard queue or stack, but the item removed (via dequeue)
 *  or sampled is chosen uniformly at random from the items currently in
 *  the queue. Implemented using a resizing array.
 *
 *  Features:
 *    - Generic type <Item> for storing any object type.
 *    - Supports enqueue(), dequeue() (removes random item), and
 *      sample() (returns random item without removal).
 *    - Includes isEmpty() and size() methods.
 *    - Implements Iterable<Item> to provide iterators that return items
 *      in a uniformly random order. Each iterator maintains its own
 *      independent random order.
 *    - Strict checks for null arguments and operations on empty queue.
 *
 *  Performance:
 *    - Enqueue, dequeue, sample, size, and isEmpty operations take
 *      constant amortized time.
 *    - Memory usage is proportional to the number of items (linear),
 *      typically ~ 48N + 192 bytes using a standard memory model.
 *    - Iterator construction takes linear time proportional to the number
 *      of items.
 *    - Iterator operations (hasNext, next) take constant worst-case time.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private static final int INIT_CAPACITY = 8;

    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        q = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return n;
    }

    // resize the underlying array
    private void resize(int capacity)
    {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
        {
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last = n;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        // double size of array if necessary and recopy to front of array
        if (n == q.length) resize(2 * q.length);   // double size of array if necessary
        q[last++] = item;                                  // add item
        if (last == q.length) last = 0;                    // wrap-around
        n++;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

        int random = StdRandom.uniformInt(n);
        int indexRemoved = (first + random) % q.length;

        // swapping removed and first to maintain compact array
        Item item = q[indexRemoved];
        q[indexRemoved] = q[first];

        q[first] = null;                            // to avoid loitering
        n--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int random = StdRandom.uniformInt(n);
        int index = (first + random) % q.length;
        return q[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        Item[] items = (Item[]) new Object[n];
        for (int i = 0; i < n; i++)
        {
            items[i] = q[(first + i) % q.length];
        }
        StdRandom.shuffle(items);
        return new Iterator<>()
        {
            private int i = 0;

            public boolean hasNext()
            {
                return i < n;
            }

            public void remove()
            {
                throw new UnsupportedOperationException();
            }

            public Item next()
            {
                if (!hasNext()) throw new NoSuchElementException();
                return items[i++];
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.enqueue(693);
        queue.enqueue(963);

        for (Integer i: queue) {
            System.out.println(i);
        }

        StdOut.println("sample:" + queue.sample());
        StdOut.println("size:" + queue.size());
        StdOut.println("dequeue:" + queue.dequeue());
        StdOut.println("sample:" + queue.sample());
    }
}