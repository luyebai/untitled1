package lianxi;


import java.util.Scanner;

public class Hannoi {
    public static void main(String[] args) {
        System.out.println("输入要挪的块数：");
        Scanner scanner = new Scanner(System.in);
        String str=scanner.next();
        int i = Integer.parseInt(str);
        move(i,"a","b","c");
    }

    private static void move(int n,String a,String b,String c){
        if (n==1){
            System.out.println(a+"--->"+c);
        }else {
            move(n-1,"a","c","b");//将除最大的块以外的所有处理掉
            System.out.println(a+"--->"+c);//将最大的块移动到c
            move(n-1,"b","a","c");//
        }
    }

}
