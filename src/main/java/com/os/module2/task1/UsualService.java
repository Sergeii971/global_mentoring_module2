package com.os.module2.task1;

import java.math.BigInteger;
import java.util.stream.Stream;

public class UsualService {
    public BigInteger getFactorial(BigInteger f) {
        return Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE))
                .limit(f.intValue())
                .reduce(BigInteger.ONE, BigInteger::multiply);

    }
}
