package com.os.module2.task1;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("введите число для вычесления факториала: ");
            BigInteger end = scanner.nextBigInteger();
            long start = System.currentTimeMillis();
            BigInteger result = forkJoinPool.invoke(new FactorialService(BigInteger.ONE, end));
            long endd = System.currentTimeMillis();
            System.out.println("result: " + result);
            System.out.println("number of symbols: " + String.valueOf(result).length());
            System.out.println((endd - start) + " millisecond for FJP");
        }
    }
}