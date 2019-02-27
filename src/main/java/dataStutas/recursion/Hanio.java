package dataStutas.recursion;

public class Hanio {
    public static void main(String[] args) {
        hanio(5,'a','b','c');
    }
    //汉诺塔实现，通过不断改变目标柱和来实现，每次都将整个汉诺塔视为只有两块
    public static void hanio (int n, char x, char y, char z){
        if (n==1) move ( x, n, z);
        else {
            hanio (n-1, x, z, y);
            move (x, n, z);
            hanio(n-1, y, x, z);
        }
    }
    private static void move(char x, int n, char y) {
        System.out.println ("Move " + n + " from " + x + " to " + y);
    }
}
