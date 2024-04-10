package com.hank.Cpunum;

import java.util.Scanner;

public class homework01 {
    public static void main(String[] args) {
        Thread th1 = new Thread(new Thread01());
        Thread th2 = new Thread(new Thread02());
        th1.start();
        th2.start();
    }
}
class Thread01 implements Runnable{
    @Override
    public void run() {
        char q=0;
        while(q!='Q'){
            System.out.println("请输入‘Q'来退出");
            Scanner scanner=new Scanner(System.in);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            q=scanner.next().charAt(0);
        }
        Thread02.setLoop(false);
    }
}
class Thread02 implements Runnable{
    static boolean loop=true;
    @Override
    public void run() {
        while (loop){
            System.out.println((int)(Math.random()*100));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void setLoop(boolean loop) {
        Thread02.loop = loop;
    }
}