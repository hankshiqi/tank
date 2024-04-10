package com.hank.tank.Tankgame05;

import java.util.ArrayList;
import java.util.Map;

public class EnemyTank extends Tank implements Runnable{
    public ArrayList<Bullet> anblist=new ArrayList<>();
    public boolean isAlive=true;
    private int speed;
    public EnemyTank(int x, int y) {
        super(x, y);
        setDirect(2);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void enemyshut(){
        Bullet nb = null;
        switch (this.getDirect()){
            case 0:
                nb=new Bullet(this.getX()+20,this.getY(),this.getDirect());
                anblist.add(nb);
                break;

            case 1:
                nb=new Bullet(this.getX()+60,this.getY()+20,this.getDirect());
                anblist.add(nb);
                break;
            case 2:
                nb=new Bullet(this.getX()+20,this.getY()+60,this.getDirect());
                anblist.add(nb);
                break;
            case 3:
                nb=new Bullet( this.getX(),this.getY()+20,this.getDirect());
                anblist.add(nb);
                break;
        }
        new Thread(nb).start();
    }

    @Override
    public void run() {
        while (this.isAlive){
            setDirect((int)(Math.random()*4));
            switch (this.getDirect()){
                case 0:
                    for(int i=0;i<30;i++){
                        if(y<=0)
                            break;
                        this.y-=speed;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        };                    }
                    break;
                case 1:
                    for(int i=0;i<30;i++){
                        if(x>=1000)
                            break;
                        this.x+=speed;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        };
                    }
                    break;
                case 2:
                    for(int i=0;i<30;i++){
                        if(y>=750)
                            break;
                        this.y+=speed;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        };
                    }
                    break;
                case 3:
                    for(int i=0;i<30;i++){
                        if(x<=0)
                            break;
                        this.x-=speed;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        };
                    }
                    break;
            }
        }
    }
}
