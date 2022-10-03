package com.manzar.task1;

public class TimePrinter {

    public static final String FIVE_SECONDS_IS_GONE = "Пройшло 5 секунд";
    private static final long PROGRAM_START_TIME = System.currentTimeMillis();
    private static boolean timeIsDividableByFive = false;
    private static final Object lock = new Object();

    private TimePrinter() {
    }

    public static Thread firstThread() {
        return new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    long workingTime = (System.currentTimeMillis() - PROGRAM_START_TIME) / 1000;
                    printWorkingTimeAndActivateSecondThreadIfNeeded(workingTime);
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
                    printSecondThreadInfoIfNeeded();
                    lock.notifyAll();
                }
            }
        });
    }

    private static void printWorkingTimeAndActivateSecondThreadIfNeeded(long workingTime) {
        System.out.println(workingTime);
        if (workingTime != 0 && workingTime % 5 == 0) {
            timeIsDividableByFive = true;
            waitLock();
        }
    }

    private static void waitLock() {
        try {
            lock.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printSecondThreadInfoIfNeeded() {
        if (timeIsDividableByFive) {
            System.out.println(FIVE_SECONDS_IS_GONE);
            timeIsDividableByFive = false;
        }
    }
}
