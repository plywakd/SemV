package com.company;

import java.math.BigInteger;

import static com.company.Main.factorialRange;

public class Threader implements Runnable {
    int start, stop;
    BigInteger partRes;

    public Threader(int start, int stop) {
        this.start = start;
        this.stop = stop;
    }

    @Override
    public void run() {
        partRes = factorialRange(start, stop);
    }

    public BigInteger getPartRes() {
        return partRes;
    }
}
