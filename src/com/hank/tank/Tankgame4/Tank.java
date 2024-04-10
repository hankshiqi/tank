package com.hank.tank.Tankgame4;

public class Tank {
    protected int x;
    protected int y;
    private int direct;
    private int speed;
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void moveup(){
        y-=speed;
    }
    public void moveDown(){
        y+=speed;
    }
    public void moveright(){
        x+=speed;
    }
    public void moveLeft(){
        x-=speed;
    }
    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
