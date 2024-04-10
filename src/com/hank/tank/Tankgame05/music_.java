package com.hank.tank.Tankgame05;

import javax.sound.sampled.*;
import java.io.File;

public class music_ {
    public static String musicFile = "src\\111.wav";

    public static void playMusic() {
        try {
            // 创建AudioInputStream对象
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFile));

            // 获取音频格式
            AudioFormat format = audioInputStream.getFormat();

            // 创建数据行信息对象
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // 获取音频数据行对象
            Clip clip = (Clip) AudioSystem.getLine(info);

            // 打开音频流
            clip.open(audioInputStream);

            // 循环播放音频
            clip.loop(Clip.LOOP_CONTINUOUSLY);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
