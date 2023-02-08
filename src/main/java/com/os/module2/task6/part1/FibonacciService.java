package com.os.module2.task6.part1;

import java.util.concurrent.RecursiveTask;

//watch junit tests to run
public class FibonacciService extends RecursiveTask<Integer> {
    private final int n;

    FibonacciService(int n) {
        this.n = n;
    }

    protected Integer compute() {
        if (n <= 10) {
            return calculateIfLess10();
        } else {
            FibonacciService f1 = new FibonacciService(n - 1);
            f1.fork();
            FibonacciService f2 = new FibonacciService(n - 2);
            return f2.compute() + f1.join();
        }
    }

    private Integer calculateIfLess10() {
        int previousNumber = 0;
        int nextNumber = 1;

        for (int i = 1; i < n; ++i)
        {
            int sum = previousNumber + nextNumber;
            previousNumber = nextNumber;
            nextNumber = sum;
        }
        return nextNumber;
    }
}
