package com.hank.Cpunum;

public class cpunum01 {
    public static void main(String[] args) {
        Runtime runtime=Runtime.getRuntime();
        int cpunums= runtime.availableProcessors();
        System.out.println(cpunums);
    }
}
