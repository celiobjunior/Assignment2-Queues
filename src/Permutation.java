/******************************************************************************
 *  Compilation:  javac Permutation.java
 *  Execution:    java Permutation k < input.txt
 *  Dependencies: RandomizedQueue.java
 *
 *  Client program for RandomizedQueue.
 *
 *  Takes an integer k as a command-line argument, reads a sequence of strings
 *  from standard input, and prints exactly k of them uniformly at random.
 *  Each item from the input sequence is printed at most once.
 *
 *  Algorithm:
 *  Uses a RandomizedQueue to maintain a sample of the items read so far.
 *  Implements a form of Reservoir Sampling:
 *    - Fills the queue with the first k items.
 *    - For subsequent items read (item i > k), it has a probability k/i of
 *      replacing a randomly chosen item currently in the queue.
 *  This ensures that after reading all input, the queue contains a uniformly
 *  random sample of size k from the input sequence.
 *
 *  Input:
 *    - Command-line argument: Integer k (0 <= k <= number of input strings).
 *    - Standard Input: A sequence of strings separated by whitespace.
 *
 *  Output:
 *    - Standard Output: Exactly k strings from the input, chosen uniformly
 *      at random, each printed on a new line.
 *
 *  Performance & Extra Credit:
 *    - Running time is linear in the number of items read from standard input.
 *    - Memory Usage meets the extra credit requirement: Uses memory
 *      proportional only to k (due to storing at most k items in the
 *      RandomizedQueue) plus a constant amount of extra space, regardless
 *      of the total number of input items N.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation
{
    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<>();

        int itemCount = 0;
        int p = 0;
        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            if (k == 0) continue;
            if (itemCount < k)
            {
                rq.enqueue(item);
                itemCount++;
            } else
            {
                int random = StdRandom.uniformInt(k + p + 1);
                if (random < k)
                {
                    rq.dequeue();
                    rq.enqueue(item);
                }
                p++;
            }
        }

        if (itemCount != 0)
        {
            for (String item : rq)
            {
                StdOut.println(item);
            }
        }
    }
}