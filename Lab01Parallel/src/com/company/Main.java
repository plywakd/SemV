package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static BigInteger factorial(int factor) {
        BigInteger result = BigInteger.valueOf(factor);
        for (int i = --factor; i > 0; i--) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static BigInteger factorialRange(int start, int stop) {
        BigInteger result = BigInteger.valueOf(start);
        for (int i = --start; i >= stop; i--) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static BigInteger factorialParallel(int factor) {
        return IntStream
                .range(1, factor + 1)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, (base, fact) -> base.multiply(fact));
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int cpus = Runtime.getRuntime().availableProcessors();
        System.out.println("CPUS available: " + cpus);

        int param = input.nextInt();
        long start = System.currentTimeMillis();
        BigInteger res = factorial(param);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Result is: " + res + "\nTime passed: " + timeElapsed);

        start = System.currentTimeMillis();
        res = factorialParallel(param);
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("Result is: " + res + "\nTime passed: " + timeElapsed);

        start = System.currentTimeMillis();
        res = BigInteger.ONE;
        List<Thread> threads = new ArrayList<>();
        List<Threader> factorialThreads = new ArrayList<>();
        int[] intervals = new int[cpus + 1];
        intervals[0] = 0;
        for (int i = 1; i <= cpus; i++) {
            intervals[i] = (param / cpus) * i;
            Threader thrd = new Threader(intervals[i], intervals[i - 1] + 1);
            factorialThreads.add(thrd);
            Thread t = new Thread(thrd);
            threads.add(t);
            t.run();
        }
        for (Thread t : threads) {
            t.join();
        }
        for (Threader thrd : factorialThreads) {
            res = res.multiply(thrd.getPartRes());
        }
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("Result parallel is: " + res + "\nTime passed: " + timeElapsed);
    }
}
