package com.hank.tank.Tankgame05;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Hero extends Tank implements Serializable {
    public Hero(int x, int y) {
        super(x, y);
    }
    public ArrayList<Bullet> b=new ArrayList<>();
    public int HP=5;
    @Override
    public int getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setSpeed(int speed) {
        super.setSpeed(speed);
    }

    public void shut(){
        Bullet nb = null;
        switch (this.getDirect()){
            case 0:
                nb=new Bullet(this.getX()+20,this.getY(),this.getDirect());
                b.add(nb);
                break;

            case 1:
                nb=new Bullet(this.getX()+60,this.getY()+20,this.getDirect());
                b.add(nb);
                break;
            case 2:
                nb=new Bullet(this.getX()+20,this.getY()+60,this.getDirect());
                b.add(nb);
                break;
            case 3:
                nb=new Bullet( this.getX(),this.getY()+20,this.getDirect());
                b.add(nb);
                break;
        }
        new Thread(nb).start();
    }
}
