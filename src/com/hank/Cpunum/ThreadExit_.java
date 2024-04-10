package com.hank.Cpunum;

public class ThreadExit_ {
    public static void main(String[] args) throws InterruptedException {
        T t1 = new T();
        t1.start();
        //修改loop让t1退出run从而退出线程，叫做通知方式
        //让主线程先休眠5秒再退出
        Thread.sleep(5000);//alt+enter可以将报错返回给上级处理
        t1.setLoop(false);
    }
}
class T extends Thread{
    private boolean loop=true;
    private int count=0;
    @Override
    public void run() {
        super.run();
        while(loop){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("T运行中。。"+(count++));
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
