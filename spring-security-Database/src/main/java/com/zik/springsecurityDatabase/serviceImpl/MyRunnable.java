package com.zik.springsecurityDatabase.serviceImpl;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello from MyRunnable!");
    }

    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();
    }
}
