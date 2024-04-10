package com.hank.tank.Tankgame05;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class recorder implements Serializable {
    public static FileWriter fr=null;
    public static BufferedWriter bfr=null;
    public static BufferedReader bfi=null;
    public static Vector<node> nodeVector=new Vector<>();
    public static String recordfile="src\\myrecord.txt";
    public static int enemyhited=0;
    public static Vector<EnemyTank> enemyTanks;
    public static int getEnemyhited() {
        return enemyhited;
    }

    public static void setEnemyTanks(Vector<EnemyTank>  enemyTanks) {
        recorder.enemyTanks = enemyTanks;
    }
    public static void getnodeVectorAndenemyhited() throws IOException{
        bfi=new BufferedReader(new FileReader("src\\myrecord.txt"));
        enemyhited=Integer.parseInt(bfi.readLine());
        String line="";
        while((line=bfi.readLine())!=null){
            String[] xyd=line.split(" ");
            nodeVector.add(new node(Integer.parseInt(xyd[0]),Integer.parseInt(xyd[1]),Integer.parseInt(xyd[2])));
        }
    }
    public static void keeprecord()throws IOException {
        fr=new FileWriter(recordfile);
        bfr=new BufferedWriter(fr);
        bfr.write(String.valueOf(enemyhited));
        bfr.newLine();
        for(int i=0;i<enemyTanks.size();i++){
            EnemyTank enemyTank=enemyTanks.get(i);
            bfr.write(enemyTank.x+" "+enemyTank.y+" "+enemyTank.getDirect());
            bfr.newLine();
        }
        bfr.close();
    }
    public static void setEnemyhited(int enemyhited) {
        recorder.enemyhited = enemyhited;
    }
    public static void hitenemy(){
        enemyhited++;
    }
}
