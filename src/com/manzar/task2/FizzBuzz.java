package com.manzar.task2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntPredicate;

public class FizzBuzz {
    private static final IntPredicate FIZZ_NUMBER = x -> x % 3 == 0 && x % 5 != 0;
    private static final IntPredicate BUZZ_NUMBER = x -> x % 3 != 0 && x % 5 == 0;
    private static final IntPredicate FIZZ_BUZZ_NUMBER = x -> x % 3 == 0 && x % 5 == 0;
    private static final IntPredicate SIMPLE_NUMBER = x -> x % 3 != 0 && x % 5 != 0;
    private final int lastNumber;
    private int currentNumber = 1;
    private final Lock lock = new ReentrantLock();


    public FizzBuzz(int lastNumber) {
        this.lastNumber = lastNumber;
    }

    public void fizz(Runnable printFizz) {
        checkConditionAndRun(FIZZ_NUMBER, printFizz);
    }

    public void buzz(Runnable printBuzz) {
        checkConditionAndRun(BUZZ_NUMBER, printBuzz);
    }

    public void fizzBuzz(Runnable printFizzBuzz) {
        checkConditionAndRun(FIZZ_BUZZ_NUMBER, printFizzBuzz);
    }

    public void printNumber(String printNumber) {
        Runnable printSimpleNumber = () -> System.out.printf(printNumber, currentNumber);
        checkConditionAndRun(SIMPLE_NUMBER, printSimpleNumber);
    }

    private void checkConditionAndRun(IntPredicate integerPredicate, Runnable runnable) {
        while (currentNumber <= lastNumber) {
            try {
                lock.lock();
                if (integerPredicate.test(currentNumber)) {
                    runnable.run();
                    currentNumber++;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
