package com.hank.tank.Tankgame4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class Mypanel extends JPanel implements KeyListener,Runnable{
    Hero hero=null;
    Vector<EnemyTank> enemyVector;
    Vector<bouns> bounsVector;
    int bounsnum=1;
    Image image1=null;
    Image image2=null;
    Image image3=null;
    Image image4=null;
    Image image5=null;
    Image image6=null;

    int enemynum=6;
    Mypanel(){
        hero= new Hero(300, 300);
        bounsVector=new Vector<>();
        enemyVector=new Vector<EnemyTank>();
        for(int i=0;i<bounsnum;i++){
            int x=(int)(Math.random()*1000);
            int y=(int)(Math.random()*750);
            bouns b=new bouns(new Pointer(x,y));
            bounsVector.add(b);
            new Thread(b).start();
        }
        if(enemyVector.size()<enemynum){
            for(int i=0;i<enemynum;i++){
                EnemyTank enemyTank=new EnemyTank(100*(i+1),0);
                enemyTank.setSpeed(3);
                enemyTank.setDirect(2);
                Thread enThrea=new Thread(enemyTank);
                enThrea.start();
                enemyVector.add(enemyTank);
            }
        }
        hero.setSpeed(3);


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        drwaTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);
        for(Bullet b:hero.b){
            if(b.isAlive!=false){
                g.setColor(Color.yellow);
                g.fill3DRect(b.x,b.y,5,5,false);
            }
        }
        try {
            // 加载图片
            image1 = ImageIO.read(new File("C:\\Users\\Administrator\\IdeaProjects\\tank\\out\\production\\tank\\bomb_1.gif"));
            image2 = ImageIO.read(new File("C:\\Users\\Administrator\\IdeaProjects\\tank\\out\\production\\tank\\bomb_2.gif"));
            image3 = ImageIO.read(new File("C:\\Users\\Administrator\\IdeaProjects\\tank\\out\\production\\tank\\bomb_3.gif"));
            image4=ImageIO.read(new File("E:\\picture\\加速.jfif"));
            image5=ImageIO.read(new File("E:\\picture\\子弹.jfif"));
            image6=ImageIO.read(new File("E:\\picture\\坦克爆炸.jfif"));
            // 处理加载成功的图片，例如在GUI中显示
        } catch (IOException e) {
            // 处理加载失败的情况
            e.printStackTrace();
        }
        /*敌人随机发射子弹
        * */
        for(int i=0;i<enemyVector.size();i++){
            EnemyTank enemyTank=enemyVector.get(i);
            drwaTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),0);
            int shunum=(int)(Math.random()*10);
            if(shunum==5)
                enemyTank.enemyshut();
        }
        checkpenemy(g);
        checkmylife(g);
        checkcrush();
        getmorebouns();
        Traversalbouns(g);
        if(checkgetbouns()==2){
            int enemynum=(int)(Math.random()*enemyVector.size());
            EnemyTank deletetank=enemyVector.get(enemynum);
            deletetank.isAlive=false;
            enemyVector.remove(deletetank);
            printcrush(g,deletetank.x,deletetank.y);
        }
        hero.getbounseffect(checkgetbouns());
        if(enemyVector.isEmpty()){
            System.out.println("恭喜你，游戏胜利");
            System.exit(0);
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
    public void getmorebouns(){
        if(bounsVector.size()<bounsnum){
            bouns b=new bouns(new Pointer((int)(Math.random()*1000),(int)(Math.random()*750)));
            bounsVector.add(b);
            new Thread(b).start();
        }
    }
    public void Traversalbouns(Graphics g){
        Iterator<bouns> iterator=bounsVector.iterator();
        while (iterator.hasNext()){
            bouns b=iterator.next();
            if(!b.isAlive) {
                iterator.remove();
            }
            else drawbouns(b.bounsPointer.x,b.bounsPointer.y,g,b.randnum);
        }
    }
    public void drawbouns(int x,int y,Graphics g,int type){
        if(type==0){
            g.drawImage(image4,x,y,50,50,this);
        }else if(type==1){
            g.drawImage(image5,x,y,50,50,this);
        } else{
            g.drawImage(image6,x,y,50,50,this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void checkpenemy(Graphics g){
        //check是否命中
        int sizedel=enemyVector.size();
        for(int i=0;i<sizedel;i++){
            EnemyTank enemyTank=enemyVector.get(i);
            int anX=enemyTank.getX();
            int anY=enemyTank.getY();
            int direc=enemyTank.getDirect();
            for(Bullet b:enemyTank.anblist){
                if(b.isAlive!=false){
                    g.setColor(Color.CYAN);
                    g.fill3DRect(b.x,b.y,5,5,false);
                }
            }
            for(Bullet b:hero.b){
                if(b.isAlive){
                    switch (direc){
                        case 0:
                        case 2:
                            if(b.x>=anX&&b.x<=anX+40&&b.y>=anY&&b.y<=anY+60){
                                enemyVector.remove(enemyTank);
                                sizedel--;
                                b.isAlive=false;
                                printcrush(g,anX,anY);
                            }
                            if(enemyVector.isEmpty()){
                                System.out.println("恭喜你，游戏胜利!");
                                System.exit(0);
                            }
                            break;
                        case 1:
                        case 3:
                            if(b.x>=anX&&b.x<=anX+60&&b.y>=anY&&b.y<=anY+40){
                                enemyVector.remove(enemyTank);
                                sizedel--;
                                b.isAlive=false;
                                int count=90;
                                printcrush(g,anX,anY);
                            }
                            if(enemyVector.isEmpty()){
                                System.out.println("恭喜你，游戏胜利!");
                                System.exit(0);
                            }
                            break;
                    }

                }
            }
        }
    }
    public void printcrush(Graphics g,int x,int y){
        int count=9000;
        while(count>0){
            if(count>6000){
                g.drawImage(image3,x,y,60,60,this);
            } else if (count>3000) {
                g.drawImage(image2,x,y,60,60,this);
            }else {
                g.drawImage(image2,x,y,60,60,this);
            }
            count--;
        }
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
        }
        if(e.getKeyCode()==KeyEvent.VK_J){
            hero.shut();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public synchronized int checkgetbouns(){
        int get=-1;
        Iterator iterator=bounsVector.iterator();
        while (iterator.hasNext()){
            bouns b= (bouns) iterator.next();
            int heroright=hero.x+60;
            int herobottom=hero.y+60;
            int bright=b.bounsPointer.x+50;
            int bbottom=b.bounsPointer.y+50;
            if(!(heroright<b.bounsPointer.x||bright<hero.x||herobottom<b.bounsPointer.y||bbottom<hero.y)){
                b.isAlive=false;
                iterator.remove();
                get=b.randnum;
            }
        }
        return get;
    }
    @Override
    public void run() {
        while (true)
        {
            this.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void checkcrush(){
        boolean check=true;
        for(int i=0;i<enemyVector.size();i++){
            EnemyTank en=enemyVector.get(i);
            if(!(hero.x+60<=en.x||en.x+60<=hero.x||hero.y+60<=en.y||en.y+60<=hero.y)){
                check=false;
            }
        }
        if(check==false){
            System.out.println("Crush!");
            System.exit(-1);
        }
    }
    public synchronized void checkmylife(Graphics g){
        for(EnemyTank each : enemyVector){
            ArrayList<Bullet> anblist = each.anblist;
            Iterator<Bullet> iterator = anblist.iterator();
            while(iterator.hasNext()){
                Bullet bullet = iterator.next();
                if(bullet.isAlive){
                    switch (hero.getDirect()){
                        case 0:
                        case 2:
                            if(bullet.x >= hero.x && bullet.x <= hero.x + 40 && bullet.y >= hero.y && bullet.y <= hero.y + 60){
                                bullet.isAlive = false;
                                iterator.remove(); // 使用迭代器的 remove() 方法删除元素
                                hero.HP--;
                                if(hero.HP == 0) {
                                    printcrush(g, hero.x, hero.y);
                                    System.out.println("很遗憾你被击毁了，游戏结束");
                                    System.exit(0);
                                } else {
                                    System.out.println("你被击中了当前的血量为:" + hero.HP);
                                }
                            }
                            break;
                        case 1:
                        case 3:
                            if(bullet.x >= hero.x && bullet.x <= hero.x + 60 && bullet.y >= hero.y && bullet.y <= hero.y + 40){
                                bullet.isAlive = false;
                                iterator.remove(); // 使用迭代器的 remove() 方法删除元素
                                hero.HP--;
                                if(hero.HP == 0) {
                                    printcrush(g, hero.x, hero.y);
                                    System.out.println("很遗憾你被击毁了，游戏结束");
                                    System.exit(0);
                                } else {
                                    System.out.println("你被击中了当前的血量为:" + hero.HP);
                                }
                            }
                            break;
                    }
                }
            }
        }
    }
    public Pointer printrandomPlace(){
        int x=(int)Math.random()*1000;
        int y=(int)Math.random()*750;
        return new Pointer(x, y);
    }
}
