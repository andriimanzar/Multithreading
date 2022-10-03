package com.manzar.task1;

public class TimePrinterTest {
    public static void main(String[] args) {
        TimePrinter.firstThread().start();
        TimePrinter.secondThread().start();
    }
}
