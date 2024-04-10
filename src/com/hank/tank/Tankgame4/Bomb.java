package com.hank.tank.Tankgame4;

public class Bomb {
    int x;//炸弹横纵坐标
    int y;
    int life=9;
    boolean isLive=true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void lifedis(){
        if(life>0)
            life--;
        else
            isLive=false;
    }
}
