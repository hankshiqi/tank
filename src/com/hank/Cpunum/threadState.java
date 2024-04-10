package com.hank.Cpunum;

public class threadState {
    public static void main(String[] args) throws InterruptedException {
        TT t=new TT();
        System.out.println(t.getName()+"状态"+t.getState());
        t.start();
        while(Thread.State.TERMINATED!=t.getState()){
            System.out.println(t.getName()+"状态"+t.getState());
            Thread.sleep(1000);
        }
        System.out.println(t.getName()+"状态"+t.getState());
    }
}
class TT extends Thread{
    @Override
    public void run() {
            for(int i=0;i<10;i++){
                System.out.println("hi"+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
