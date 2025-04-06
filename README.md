# Algorithms I: Queues Assignment (Princeton University)

<img src="images/logo.png" width="40%" alt="Assignment logo">

This repository contains the solution for the Queues programming assignment, part of Princeton University's Algorithms, Part I course available on Coursera. The assignment involves implementing generic Deque and Randomized Queue data structures using linked lists and resizing arrays, respectively, along with a client program.

## Assignment Goal

The core task is to implement two fundamental data structures:
1.  **`Deque.java`**: A double-ended queue supporting adding/removing items from both front and back in constant time (using a doubly-linked list).
2.  **`RandomizedQueue.java`**: A queue where removal and sampling operations select a random item, implemented with constant amortized time operations (using a resizing array).
Both structures must be generic and implement the `Iterable` interface.
A client program, **`Permutation.java`**, uses the `RandomizedQueue` to read strings from standard input and print exactly *k* of them uniformly at random.

For the full details, problem description, and API requirements, please see the [Official Queues Assignment Specification](<https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php>).

## Repository Structure

*   `/src`: Contains the Java source code:
    *   `Deque.java`: Implements the generic double-ended queue using a linked list.
    *   `RandomizedQueue.java`: Implements the generic randomized queue using a resizing array.
    *   `Permutation.java`: Client program to print k random items from input.
*   `/inputs`: Contains sample input text files for testing `Permutation.java` (e.g., `distinct.txt`, `duplicates.txt`).

## Core Concepts

*   **Deque:** Implemented using a **doubly-linked list** to achieve constant worst-case time for all operations.
*   **RandomizedQueue:** Implemented using a **resizing array** to achieve constant amortized time for queue operations. Randomness is achieved using `StdRandom`.
*   **Generics:** Both data structures use `<Item>` to allow storing objects of any type.
*   **Iterators:** Both data structures provide `Iterator` implementations. The `RandomizedQueue` iterator returns items in a uniformly random, independent order.
*   **Reservoir Sampling:** The `Permutation.java` client implements this technique to select *k* items uniformly at random from an input stream of unknown size, using space proportional only to *k*.

## How to Compile and Run the `Permutation` Client

These commands assume you are running them from the **root directory** of this repository.

1.  **Compile:**
    *   Download [algs4.jar](<https://algs4.cs.princeton.edu/code/algs4.jar>) to a location on your system (e.g., `~/algs4/` on Linux/macOS or `C:\Users\username\algs4\` on Windows).
    *   Compile the source files located in the `src` directory:
        ```bash
        # On Linux/macOS (replace ~/algs4/algs4.jar with your actual path)
        javac -cp ~/algs4/algs4.jar:src src/Deque.java src/RandomizedQueue.java src/Permutation.java

        # On Windows (replace C:\Users\username\algs4\algs4.jar with your actual path)
        javac -cp C:\Users\username\algs4\algs4.jar;src src/Deque.java src/RandomizedQueue.java src/Permutation.java
        ```
        *This command tells `javac` where to find the `algs4.jar` library and the source files (`src`), then specifies which source files within the `src` directory to compile. The compiled `.class` files will likely appear within the `src` directory.*

2.  **Run `Permutation`:**
    *   Execute the `Permutation` client, providing the number *k* as a command-line argument and piping input from a file.
    ```bash
    # On Linux/macOS (replace ~/algs4/algs4.jar with your actual path)
    java -cp ~/algs4/algs4.jar:src Permutation <k> < inputs/<input-file.txt>

    # On Windows (replace C:\Users\username\algs4\algs4.jar with your actual path)
    java -cp C:\Users\username\algs4\algs4.jar;src Permutation <k> < inputs/<input-file.txt>
    ```
    *   Replace `<k>` with the desired number of items to print (e.g., `3`).
    *   Replace `<input-file.txt>` with an actual filename from the `/inputs` directory (e.g., `distinct.txt`).
    *   *This command tells `java` where to find the compiled classes (`src`) and the library (`algs4.jar`), which program to run (`Permutation`), passes `k` as a command-line argument (`args[0]`), and redirects the content of the input file to standard input.*

## Extra Credit Requirement Met

The `Permutation.java` client successfully implements the **Reservoir Sampling** algorithm using a `RandomizedQueue`. This fulfills the assignment's extra credit challenge by ensuring that the client uses memory proportional only to *k* (the number of items to print), regardless of the total number of items *N* in the input stream.

* Correctness:  49/49 tests passed
* Memory:       134/133 tests passed
* Timing:       193/193 tests passed
* Aggregate score: 100.08%