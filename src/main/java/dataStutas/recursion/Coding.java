package dataStutas.recursion;

/**
 * 求布尔值的n中组合
 */

public class Coding {
    public static void main(String[] args) {
        int[] b=new int[5];
        coding(b,4);
    }
    public static void coding (int[] b, int n) {
        if (n==0) {//n=0时只有两种情况
            b[n] = 0;outBn(b);
            b[n] = 1;outBn(b);
        }
        else {//n>0时，输出本位的两种情况，并调用n-1的情况
            b[n] = 0; coding(b,n-1);
            b[n] = 1; coding(b,n-1);
        }
    }
    private static void outBn (int[] b) {
        for (int i=0;i<b.length;i++)
            System.out.print(b[i]);
        System.out.println();
    }
}
