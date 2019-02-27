package dataStutas.recursion;

public class Power {
    public static void main(String[] args) {
        System.out.println(power(2,4));
    }

    /**
     * x为底，n为幂
     */
    public static int power (int x, int n) {
        int y;//最终结果初始化
        if (n == 0)
            y = 1;
        else {
            y = power (x, n/2);//如果n是偶数，x的n次幂=x的二分之n的平方。
            y = y * y;//平方操作
            if (n%2 == 1) y = y * x;//如果是奇数，则再乘一次x
        }
        return y;
    }
}
