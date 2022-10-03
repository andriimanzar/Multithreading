package com.manzar.task2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class FizzBuzz {

    private final int lastNumber;
    private int currentNumber = 1;
    private final Lock lock = new ReentrantLock();


    public FizzBuzz(int lastNumber) {
        this.lastNumber = lastNumber;
    }

    public void fizz(Runnable printFizz) {
        while (currentNumber <= lastNumber) {
            try {
                lock.lock();
                if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                    printFizz.run();
                    currentNumber++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void buzz(Runnable printBuzz) {
        while (currentNumber <= lastNumber) {
            try {
                lock.lock();
                if (currentNumber % 3 != 0 && currentNumber % 5 == 0) {
                    printBuzz.run();
                    currentNumber++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void fizzBuzz(Runnable printFizzBuzz) {
        while (currentNumber <= lastNumber) {
            try {
                lock.lock();
                if (currentNumber % 3 == 0 && currentNumber % 5 == 0) {
                    printFizzBuzz.run();
                    currentNumber++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void printNumber(IntConsumer intConsumer) {
        while (currentNumber <= lastNumber) {
            try {
                lock.lock();
                if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                    intConsumer.accept(currentNumber);
                    currentNumber++;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
