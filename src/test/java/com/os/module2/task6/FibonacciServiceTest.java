package com.os.module2.task6;

import com.os.module2.task6.part1.FibonacciService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;


class FibonacciServiceTest {
    @Test
    public void test() {
        assertEquals(1134903170L, new ForkJoinPool().invoke(new FibonacciService(45)).longValue());
    }
}