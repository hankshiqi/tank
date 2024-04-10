package com.hank.Cpunum;

public class instant_func02 {//yield和joifn

    public static void main(String[] args) throws InterruptedException {
        child child = new child();
        child.start();
        for(int i=0;i<20;i++){
            Thread.sleep(1000);
            System.out.println("hi"+i);
            if(i==4)
                child.join();
        }
    }
}
class child extends Thread{
    private int t=20;
    @Override
    public void run() {
        while(t-->0){
            System.out.println("hello"+t);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}