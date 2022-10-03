package com.manzar.task2;

import java.util.List;
import java.util.function.IntConsumer;

public class FizzBuzzThreadStarter {
    private static final String FIZZ = "fizz, ";
    private static final String BUZZ = "buzz, ";
    private static final String FIZZ_BUZZ = "fizzbuzz, ";
    private static final IntConsumer consumer = x -> System.out.print(x + ", ");

    private FizzBuzzThreadStarter(){}

    public static void startThreads(FizzBuzz fizzBuzz) {
        List<Thread> threads = createThreads(fizzBuzz);
        threads.forEach(Thread::start);
    }

    private static List<Thread> createThreads(FizzBuzz fizzBuzz) {
        Runnable fizz = () -> System.out.print(FIZZ);
        Runnable buzz = () -> System.out.print(BUZZ);
        Runnable fBuzz = () -> System.out.print(FIZZ_BUZZ);

        Thread threadA = new Thread(() -> fizzBuzz.fizz(fizz));
        Thread threadB = new Thread(() -> fizzBuzz.buzz(buzz));
        Thread threadC = new Thread(() -> fizzBuzz.fizzBuzz(fBuzz));
        Thread threadD = new Thread(() -> fizzBuzz.printNumber(consumer));

        return List.of(threadA, threadB, threadC, threadD);

    }
}
