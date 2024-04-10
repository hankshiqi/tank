package com.hank.Tankgame03;

import java.util.ArrayList;

public class Hero extends Tank {
    public Hero(int x, int y) {
        super(x, y);
    }
    public ArrayList<Bullet> b=new ArrayList<>();

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
