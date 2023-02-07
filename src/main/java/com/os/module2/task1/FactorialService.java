package com.os.module2.task1;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FactorialService extends RecursiveTask<BigInteger> {
    private BigInteger start;
    private BigInteger end;

    private static final BigInteger THRESHOLD = BigInteger.valueOf(10);

    public FactorialService(BigInteger start, BigInteger end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected BigInteger compute() {
        if (end.subtract(start).compareTo(THRESHOLD) <= 0) {
           return computeDirectly();
        } else {
            BigInteger mid = start.add(end.subtract(start).divide(BigInteger.valueOf(2)));
            FactorialService left = new FactorialService(start, mid);
            left.fork();
            FactorialService right = new FactorialService(mid.add(BigInteger.ONE), end);
            return right.compute().multiply(left.join());
        }
    }

    private BigInteger computeDirectly() {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = start; i.compareTo(end) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }

}

