package com.manzar.task1;

public class TimePrinter {

    public static final long PROGRAM_START_TIME = System.currentTimeMillis();
    private static boolean timeIsDividableByFive = false;
    public static final Object lock = new Object();

    private TimePrinter() {
    }

    public static Thread firstThread() {
        return new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    long workingTime = (System.currentTimeMillis() - PROGRAM_START_TIME) / 1000;
                    System.out.println(workingTime);
                    if (workingTime != 0 && workingTime % 5 == 0) {
                        timeIsDividableByFive = true;
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public static Thread secondThread() {
        return new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (timeIsDividableByFive) {
                        System.out.println("Пройшло 5 секунд");
                        timeIsDividableByFive = false;
                    }
                    lock.notifyAll();
                }
            }
        });
    }
}
