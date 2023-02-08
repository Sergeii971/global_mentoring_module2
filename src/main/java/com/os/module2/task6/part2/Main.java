package com.os.module2.task6.part2;

import com.os.module2.task1.FactorialService;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int SIZE = 500_000_000;
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        double[] data1 = new double[SIZE];
        Random rand = new Random();

        for (int i = 0; i < data1.length; i++) {
            double randomNum = rand.nextDouble() + SIZE;
            data1[i] = randomNum;
        }

        long start = System.nanoTime();
        double result = sumOfSquares(forkJoinPool, data1);
        long endd = System.nanoTime();
        System.out.println("result: " + result);
        System.out.println((endd - start) + " nanoseconds for FJP");
        System.out.println("------------------------------------------------------");
        start = System.nanoTime();
        double sum = 0;
        for (double v : data1) {
            sum += v * v;
        }
        endd = System.nanoTime();
        System.out.println("result: " + sum);
        System.out.println((endd - start) + " nanoseconds for line");
    }

    private static double sumOfSquares(ForkJoinPool pool, double[] array) {
        int n = array.length;
        Applyer a = new Applyer(array, 0, n, null);
        pool.invoke(a);
        return a.result;
    }
}
