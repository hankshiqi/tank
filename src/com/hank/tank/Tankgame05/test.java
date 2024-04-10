package com.hank.tank.Tankgame05;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
    @Test
    public void test01()throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream("src\\myrecord.txt"),"utf-8");
        System.out.println(reader.read());

    }
}
