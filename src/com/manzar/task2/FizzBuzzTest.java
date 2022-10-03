package com.manzar.task2;

public class FizzBuzzTest {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(100);
        FizzBuzzThreadStarter.startThreads(fizzBuzz);
    }
}
