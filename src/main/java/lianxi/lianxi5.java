package lianxi;


import java.util.Arrays;

public class lianxi5 {


    public static void main(String[] args) {
        int[] a={7,8,5,4,2,4454,1212,42};
        int[] b=new int[a.length*2];
        System.arraycopy(a,0,b,0,a.length);

        /*int[] b={555,22,35,6,4,89521,324};
        int[] marge = marge(a, b);*/
        System.out.println(Arrays.toString(b));
    }
    public static int[] marge ( int[] a, int[] b){
        int pa = 0, pb = 0, pc = 0;
        int m = a.length;
        int n = b.length;
        int[] c = new int[m + n];
        while (pa < m && pb < n) {
            if (a[pa] < b[pb])
                c[pc++] = a[pa++];
            else
                c[pc++] = b[pb++];
        }
        if (pa < m)
            while (pa < m) c[pc++] = a[pa++];
        else
            while (pb < n) c[pc++] = b[pb++];
        return c;
    }
}