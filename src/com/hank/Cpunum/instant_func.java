package com.hank.Cpunum;

public class instant_func {
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
        threadDemo.setPriority(Thread.MIN_PRIORITY);
        for(int i=0;i<5;i++){
            Thread.sleep(1000);
            System.out.println("hi");
        }
        threadDemo.interrupt();
    }
}
class ThreadDemo extends Thread{
    @Override
    public void run() {
        while(true){
            for(int i=0;i<100;i++){
                System.out.println(Thread.currentThread().getName()+"吃包子"+i);
            }
            try {
                System.out.println(Thread.currentThread().getName()+"休眠中~");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"被interupt了");
                //捕获到一个中断异常
            }
        }
    }
}
