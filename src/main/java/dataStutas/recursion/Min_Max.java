package dataStutas.recursion;

public class Min_Max {
    private static class IntPair{
        int x;
        int y;
    }
    public static void main(String[] args) {
        int[] arr={3,5,4,7,8,62,1,4,-5,99,22};
        IntPair pair = min_max(arr, 0,10);
        System.out.println(pair.x+" : "+pair.y);
    }
    //数组，数组的起始索引，数组的结束索引。
    //此方法比较数组中指定位置的最大与最小值
    public static IntPair min_max(int[] a, int low, int high){
        IntPair pair = new IntPair();
        if (low>high-2){//如果条件满足则说明low与high已经相邻
            if (a[low]<a[high]){//从两个索引所对应的值中选出最大值
                pair.x = a[high]; pair.y = a[low]; }
            else{
                pair.y = a[high]; pair.x = a[low]; }
        }
        else{
            int mid = (low + high)/2;//索引二分
            IntPair p1 = min_max(a,low,mid);//重复此过程
            IntPair p2 = min_max(a,mid+1,high);//重复此过程
            //比较从两个值中选出最大值，这两步也是在递归过程当中
            pair.x = p1.x>p2.x ? p1.x : p2.x;
            pair.y = p1.y<p2.y ? p1.y : p2.y;
        }
        return pair;
    }
}
