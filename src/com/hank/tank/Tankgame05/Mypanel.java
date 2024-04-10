package com.hank.tank.Tankgame05;

import org.w3c.dom.Node;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;
import java.util.Timer;

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

    Mypanel(int key) throws IOException {
        music_.playMusic();
        hero= new Hero(300, 300);
        hero.setSpeed(3);
        bounsVector=new Vector<>();
        BufferedReader reader = new BufferedReader(new FileReader("src\\myrecord.txt"));
        String k;
        if((k=reader.readLine())==null)
        {
            System.out.println("文件不存在，只能开启新的游戏");
            key=2;
        }
        for(int i=0;i<bounsnum;i++){
            int x=(int)(Math.random()*1000);
            int y=(int)(Math.random()*750);
            bouns b=new bouns(new Pointer(x,y));
            bounsVector.add(b);
            new Thread(b).start();
        }
        if(key==1){
            recorder.getnodeVectorAndenemyhited();
            enemyVector=new Vector<>();
            for(int i=0;i<recorder.nodeVector.size();i++){
                node node=recorder.nodeVector.get(i);
                EnemyTank enemyTank=new EnemyTank(node.getX(), node.getY());
                enemyTank.setDirect(node.getDirection());
                enemyTank.setSpeed(3);
                Thread enThrea=new Thread(enemyTank);
                enThrea.start();
                enemyVector.add(enemyTank);
            }
        }else{
            enemyVector=new Vector<EnemyTank>();
            recorder.setEnemyTanks(enemyVector);
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
        }

        }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        showInfo(g);
        drwaTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);
        for(Bullet b:hero.b){
            if(b.isAlive!=false){
                g.setColor(Color.yellow);
                g.fill3DRect(b.x,b.y,5,5,false);
            }
        }
        try {
            // 加载图片
            image1 = ImageIO.read(new File("E:\\picture\\bomb_1.gif"));
            image2 = ImageIO.read(new File("E:\\picture\\bomb_2.gif"));
            image3 = ImageIO.read(new File("E:\\picture\\bomb_3.gif"));
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
        try {
            checkpenemy(g);
            checkmylife(g);
            checkcrush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getmorebouns();
        Traversalbouns(g);
        givebounseffect(checkgetbouns(),g);


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
    public void showInfo(Graphics g){
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克",1020,30);
        drwaTank(1020,60,g,0,0);
        g.setColor(Color.BLACK);//这里需要重新设计成黑色
        g.drawString(String.valueOf(recorder.getEnemyhited()),1080,100);
    }
    public void getmorebouns(){
        if(bounsVector.size()<bounsnum){
            bouns b=new bouns(new Pointer((int)(Math.random()*900),(int)(Math.random()*650)));
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

    public synchronized void checkpenemy(Graphics g) throws IOException {
        //check是否命中
        Iterator iterator=enemyVector.iterator();
        while(iterator.hasNext())
        {
            EnemyTank enemyTank=(EnemyTank) iterator.next();
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
                                iterator.remove();
                                b.isAlive=false;
                                recorder.hitenemy();
                                printcrush(g,anX,anY);
                            }
                            if(enemyVector.isEmpty()){
                                System.out.println("恭喜你，游戏胜利!");
                                checkcrush();
                                System.exit(0);
                            }
                            break;
                        case 1:
                        case 3:
                            if(b.x>=anX&&b.x<=anX+60&&b.y>=anY&&b.y<=anY+40){
                                iterator.remove();
                                b.isAlive=false;
                                recorder.hitenemy();
                                printcrush(g,anX,anY);
                            }
                            if(enemyVector.isEmpty()){
                                System.out.println("恭喜你，游戏胜利!");
                                checkcrush();
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
                if(!(hero.x>0&&hero.x<960&&hero.y>0&&hero.y<690))
                    hero.moveDown();
                break;
            case KeyEvent.VK_D:
                hero.setDirect(1);
                hero.moveright();
                if(!(hero.x>0&&hero.x<960&&hero.y>0&&hero.y<690))
                    hero.moveLeft();
                break;
            case KeyEvent.VK_S:
                hero.setDirect(2);
                hero.moveDown();
                if(!(hero.x>0&&hero.x<960&&hero.y>0&&hero.y<690))
                    hero.moveup();
                break;
            case KeyEvent.VK_A:
                hero.setDirect(3);
                hero.moveLeft();
                if(!(hero.x>0&&hero.x<960&&hero.y>0&&hero.y<690))
                    hero.moveright();
        }
        if(e.getKeyCode()==KeyEvent.VK_J){
            Timer t=new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    hero.shut();
                }
            },50);
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
        if(get==0)
            System.out.println(hero.getSpeed());
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
    public synchronized void givebounseffect(int efnum,Graphics g){
        java.util.Timer timer=new Timer();
        if(efnum==0){
            hero.setSpeed(hero.getSpeed()+3);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hero.setSpeed(hero.getSpeed()-3);
                }
            },5000);
        }else if(efnum==1){
            hero.HP++;
            System.out.println("当前生命值:"+hero.HP);
        } else if (efnum==2) {
            int enemynum=(int)(Math.random()*enemyVector.size());
            EnemyTank deletetank=enemyVector.get(enemynum);
            deletetank.isAlive=false;
            enemyVector.remove(deletetank);
            recorder.hitenemy();
            printcrush(g,deletetank.x,deletetank.y);
        }
    }
    public synchronized void checkcrush() throws IOException {
        boolean check=true;
        for(int i=0;i<enemyVector.size();i++){
            EnemyTank en=enemyVector.get(i);
            if(!(hero.x+60<=en.x||en.x+60<=hero.x||hero.y+60<=en.y||en.y+60<=hero.y)){
                check=false;
            }
        }
        if(check==false){
            if(hero.HP-1==0){
                System.out.println("你撞到了敌人坦克而损毁！");
                System.exit(0);
            }
            System.out.println("当前生命值为"+(--hero.HP));
        }
    }
    public synchronized void checkmylife(Graphics g) throws IOException {
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
