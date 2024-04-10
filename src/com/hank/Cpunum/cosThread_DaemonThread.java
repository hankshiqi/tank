package com.hank.Cpunum;

import java.util.TreeSet;

public class cosThread_DaemonThread {
        //当希望main结束时子线程作为守护线程自己结束
        public static void main(String[] args) throws InterruptedException {
            testDaemon testDaemon = new testDaemon();
            testDaemon.setDaemon(true);//true开启守护，false关闭守护
            testDaemon.start();
            for(int i=0;i<5;i++){
                System.out.println("tt");
                Thread.sleep(1000);
            }
        }
}
class testDaemon extends Thread{
    @Override
    public void run() {
        while(true)
        {
            System.out.println("ii");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
