package com.hank.Tankgame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Mypanel extends JPanel implements KeyListener {
    Hero hero=null;
    Vector<EnemyTank> enemyVector;
    int enemynum=3;
    Mypanel(){
        hero= new Hero(300, 300);
        enemyVector=new Vector<EnemyTank>();
        for(int i=0;i<enemynum;i++){
            enemyVector.add(new EnemyTank(100*(i+1),0));
        }
        hero.setSpeed(3);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        drwaTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);
        for(int i=0;i<enemyVector.size();i++){
            EnemyTank enemyTank=enemyVector.get(i);
            drwaTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),0);
        }

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
            case 0://敌人的tank
                g.setColor(Color.CYAN);
                break;
            case 1://我方坦克
                g.setColor(Color.yellow);
                break;
        }
        //0向上1向右2向下3向左
        switch (direct){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.drawOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.drawOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://向下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.drawOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.drawOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                hero.setDirect(0);
                hero.moveup();
                break;
            case KeyEvent.VK_D:
                hero.setDirect(1);
                hero.moveright();
                break;
            case KeyEvent.VK_S:
                hero.setDirect(2);
                hero.moveDown();
                break;
            case KeyEvent.VK_A:
                hero.setDirect(3);
                hero.moveLeft();
                break;

        }
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
