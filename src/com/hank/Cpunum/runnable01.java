package com.hank.Cpunum;

public class runnable01 {
    public static void main(String[] args) {
        T2 t2 = new T2();
        T3 t3=new T3();
        Thread thread=new Thread(t3);//创建静态代理
        thread.start();
        Thread th2=new Thread(t3);
        th2.start();
    }

}
class T2 implements Runnable{
    int time=0;
    @Override
    public void run() {
        while(true)
        {
            System.out.println("Hi"+(++time));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(time==60)
                break;
        }
    }
}
class T3 implements Runnable{
    int time=0;
    @Override
    public void run() {
        while(true){
            System.out.println("hello,world");
            time++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(time==50)
                break;
        }
    }
}
