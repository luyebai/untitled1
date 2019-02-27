package dataStutas.SearchTable;

import java.util.Arrays;

/**
 * 二分法查找实现
 */
public class BinSearch {
    public static void main(String[] args) {
        int[] arr={5,7,5,6,3,5,4,9,7,8,2};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int i = binSearch(arr, 0, arr.length, 4);
        System.out.println(i);
    }
    //二分法查找返回值的索引
    public static int binSearch(int[] s,int low,int high,int key){
        while(low<=high){
            int mid=(low+high)/2;//求得头尾指针的中间值索引
            if(s[mid]==key)return  mid; //与中间值进行比较 ，如果是就返回
            else if(s[mid]>key) high=mid-1; //如果中间值大于给的值则将最大值索引下调到mid-1
            else low=mid+1;//如果中间值小于给定值则将最小指针上调到mid+1
        }
        return -1;//否则返回-1
    }

    /**
     * 折半插入排序的实现，返回数组r以关键字有序
     * @param r
     * @param low
     * @param high
     * 折半插入排序所需的辅助空间与直接插入排序相同，从时间上比
     * 较，折半插入排序仅减少了元素的比较次数，但是并没有减少元素的移动次数，因此折半插
     * 入排序的时间复杂度仍为O(n 2 )。
     */
    public void binInsertSort(int[] r,int low,int high){
        for(int i=low+1;i<=high;i++){
            int temp=r[i];//获得待插入元素
            int hi=i-1; //设置初始区间
            int lo=low;
            while(lo<=hi){//折半确定插入位置
                int mid=(lo+hi)/2;
                if((temp-r[mid])<0){
                    hi=mid-1;
                }else{
                    lo=mid+1;
                }
            }
            for(int j=i-1;j>hi;j--) r[j+1]=r[j];//移动元素
            r[hi+1]=temp;  //插入元素

        }
    }
}
