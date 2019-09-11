package com.test.thread;

/**
 * 类TestSynchronized的实现描述：TODO 类实现描述 
 * @author Administrator 2018/6/26 17:28
 */
public class TestSychronized {
    public void method1() throws InterruptedException {
        synchronized(TestSychronized.class){
            System.out.println("method1 begin at:" + System.currentTimeMillis());
            Thread.sleep(6000);
            System.out.println("method1 end at:" + System.currentTimeMillis());
        }
    }
    public synchronized void method2() throws InterruptedException {
        while(true) {
            System.out.println("method2 running");
            Thread.sleep(200);
        }
    }
    static TestSychronized instance = new TestSychronized();
    static TestSychronized instance1 = new TestSychronized();
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    instance.method1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i=1; i<4; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread1 still alive");
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    instance1.method1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
