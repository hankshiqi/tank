package com.hank.Cpunum;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        childThread childThread = new childThread();
        Thread th01=new Thread(childThread);
        for(int i=1;i<=10;i++){
            System.out.println("hi"+i);
            Thread.sleep(1000);
            if(i==5){
                th01.start();
                th01.join();
            }
        }
        System.out.println("主线程结束");
    }
}
class childThread implements Runnable{
    int time=10;
    @Override
    public void run() {
        while(time-->0){
            System.out.println("hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("子线程结束");
    }
}