package lianxi;
import java.util.Scanner;

/**
 * 等待与唤醒的简单实现
 */
class Damo{//一个数据类，用来在线程之间传递数据和输出数据
    public static String tes;//存放数据的字符串
    public boolean flag= false;//标记状态

    public Damo(String tes, boolean flag) {
        this.tes = tes;
        this.flag = flag;
    }
}
class MyThread extends Thread{
    private static Damo demo;//线程之间传递数据，以及充当锁对象
    public MyThread(Damo demo) {
        this.demo = demo;
    }
    @Override
    public void run() {
        new Thread(){
            @Override
            public void run() {
                add();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                add2();
            }
        }.start();

    }
    public static synchronized void add(){
        while (true) {
                if(demo.flag){//如果是true就输入
                    Scanner scanner = new Scanner(System.in);
                    demo.tes = scanner.nextLine();//将输入的值赋值给数据字符串
                    demo.flag=false;//标志转换为false
                    MyThread.class.notify();//唤醒另一个线程，必须用同一个对象来调用唤醒和等待才能实现
                }else{//如果是false就等待
                    try {
                        MyThread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
    public static synchronized void add2(){
        while (true) {
            if(!demo.flag){//如果是flag是false就输出
                System.out.println(demo.tes.toUpperCase());
                demo.flag=true;//修改flag的状态
                MyThread.class.notify();//唤醒另一个线程
            }else{//如果是true就等待
                try {
                    MyThread.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class Lisnxi4 {
    public static void main(String[] args) {
        Damo damo = new Damo("", true);//初始数据状态
        MyThread thread = new MyThread(damo);
        thread.start();
    }
}
