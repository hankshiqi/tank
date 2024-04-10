package com.hank.Tankgame03;

public class questiontest_ {
    public static void main(String[] args) {
        child child = new child();
        System.out.println(child.k);
    }
}
class Parent{
    protected int k=10;

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}
class child extends Parent{
    public child() {
        k=20;
    }
}
