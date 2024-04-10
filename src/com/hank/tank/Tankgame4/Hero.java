package com.hank.tank.Tankgame4;

import java.util.*;

public class Hero extends Tank {
    public Hero(int x, int y) {
        super(x, y);
    }
    public ArrayList<Bullet> b=new ArrayList<>();
    public int HP=5;
    public int bulletinch=0;
    @Override
    public int getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setSpeed(int speed) {
        super.setSpeed(speed);
    }

    public void getbounseffect(int efnum){
        if(efnum!=-1){
            Timer timer=new Timer();
            if(efnum==0){
                setSpeed(getSpeed()+3);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setSpeed(getSpeed()-3);
                    }
                },5000);
            }else if(efnum==1){
                HP++;
                System.out.println("当前生命值:"+HP);
            }
        }
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
