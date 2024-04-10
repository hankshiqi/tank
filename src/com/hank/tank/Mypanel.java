package com.hank.tank;

import javax.swing.*;
import java.awt.*;

public class Mypanel extends JPanel {
    Hero hero=null;
    Mypanel(){
        hero= new Hero(100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        drwaTank(hero.getX(),hero.getY(),g,0,1);

    }
    //编写方法画出tank

    /**
     *
     * @param x//坦克的左上角x
     * @param y//坦克的左上角y
     * @param g//画笔
     * @param direct//方向，上下左右
     * @param type//类型
     */
    public void drwaTank(int x,int y,Graphics g,int direct,int type){
        switch (type){
            case 0://自己的tank
                g.setColor(Color.CYAN);

                break;
            case 1://敌方坦克
                g.setColor(Color.yellow);
                break;
        }
        switch (direct){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.drawOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }
}
