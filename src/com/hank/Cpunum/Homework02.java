package com.hank.Cpunum;

import java.util.ArrayList;

public class Homework02 {
    public static void main(String[] args) {
        Count count = new Count();
        Thread thread = new Thread(count);
        thread.setName("t1");

        Thread thread1 = new Thread(count);
        thread1.setName("t2");
        thread.start();
        thread1.start();
        ArrayList<Integer> list=new ArrayList<>();


    }
}
class Count implements Runnable{
    private int balance=10000;
    @Override
    public void run() {
        use();
    }
    public void use(){
        while (balance>0){
            synchronized (this) {
                int usemoney = (int) (Math.random() * 1000);
                if (usemoney > balance){
                    System.out.println(Thread.currentThread().getName()+"想要取"+usemoney);
                    System.out.println("余额不足");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "取走了" + usemoney + "余额为" + (balance - usemoney));
                balance -= usemoney;
            }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }
    }
}
