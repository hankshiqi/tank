package com.hank.tank.Tankgame05;

import java.io.Serializable;

public class Bullet implements Runnable, Serializable {
    int x;
    int y;
    int direct;
    int speed=9;
    boolean isAlive=true;

    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct){
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;
            }
            if(!(x>=0&&x<=1000&&y<=750&&y>=0)){
                this.isAlive=false;
                break;
            }
        }
    }
}
