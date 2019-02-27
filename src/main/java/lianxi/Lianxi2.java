package lianxi;


import java.nio.file.Files;
import java.util.Scanner;

/**
 * @author Vashon
 * date:20150410
 *
 * 题目：汉诺塔算法(本道程序结合了<<C语言程序教程>>的分析思路，通过重构完成。)
 *
 * 递归分析(有兴趣的可以去研究非递归的)：
 * 移动n个金片从a到c，必须先将n-1个金片从a经过c移动到b，
 * 移动n-1个问题相同，但规模变小.
 * 1.将n-1个金片从a经过c移动到b
 * 2.将第n个金片移动到c
 * 3.再将n-1个金片从b经过a移动到c
 */
public class Lianxi2 {

    public static void main(String args[]){
        System.out.println("请输入要移动的块数：");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        move(n,'a','b','c');
    }
    public static void move(int n,char a,char b,char c){
        if(n==1){ //当n只有1个的时候直接从a移动到c
            System.out.println(a+"-->"+c);
        }else{
            move(n-1,a,c,b);
            System.out.println(a+"-->"+c);
            move(n-1,b,a,c);//第n-1个移动过来之后b变开始盘，b通过a移动到c
        }
    }

}
