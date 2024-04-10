package com.hank.tank.Tankgame4;

public class bouns implements Runnable{
    int randnum;
    int randrange;
    Pointer bounsPointer;

    public bouns(Pointer bounsPointer) {
        this.bounsPointer = bounsPointer;
    }

    boolean isAlive=true;
    @Override
    public void run() {
        while(isAlive){
            randrange=(int)(Math.random()*100);
            if(randrange>=0&&randrange<=60)
                randnum=0;
            else if (randrange>=61&&randrange<=80) {
                randnum=1;
            }else
                randnum=2;
            if(randnum==0)
                System.out.println("加速");
            else if(randnum==1)
                System.out.println("HP+1");
            else if(randnum==2)
            {
                System.out.println("随机击毁一辆敌方坦克");
            }
            try {
                Thread.sleep(15000);
                isAlive=false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
