package com.hank.Cpunum;

public class SellTicket {
    public static void main(String[] args) {
        TicketTable ticketTable = new TicketTable();
        TicketTable ticketTable2 = new TicketTable();
        TicketTable ticketTable3 = new TicketTable();

        ticketTable.start();
        ticketTable2.start();
        ticketTable3.start();

    }
}
class TicketTable extends Thread{
    private static int ticketnums=100;//让多个线程共享这一个整数
    Object object=new Object();
    @Override
    public void run() {
        sell();
    }
    public/* sy_*/ void sell(){//这里加锁是一个同步方法，这个锁是在这个this对象，可以在代码块加锁s
        synchronized (object){while(ticketnums>0){//用代码块，同样加在this上如果操作的是同一个对象还可以加载这个类内部的对象上；尽量使用这种方法提高效率
            System.out.println(Thread.currentThread().getName()+"卖出一张票"+"剩余票数"+(--ticketnums));
            if(ticketnums==0) System.out.println("票已售完");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        }
    }
    public synchronized static void m1(){
        //这里是加在类本身上不是this上
    }
    public static void m2(){
        synchronized (TicketTable.class){
            //在静态方法内使用锁要加类本身
        }
    }
}
