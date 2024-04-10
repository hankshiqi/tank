package com.hank.Cpunum;

public class extendthread {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.start();
        System.out.println(Thread.currentThread().getName());

    }
}
class Cat extends Thread{//继承Threa类后该类就可以当成线程使用
    @Override
    public void run() {//重写run方法写上自己的业务逻辑
        super.run();
        int time=0;
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("嗨嗨嗨"+(++time));
            if(time==80)
                break;
        }
    }
}
