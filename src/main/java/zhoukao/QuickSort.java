package zhoukao;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 实现一个快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] intVoid = new int[] { 11, 66, 22, 0, 55, 22, 0, 32 };
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(intVoid, 0, intVoid.length - 1);
        System.out.println(Arrays.toString(intVoid));
    }

    /**
     * 快速排序
     * @param r   待排序的队列
     * @param low 排序区间[low,high]
     * @param high
     */
    public void quickSort(int[] r, int low, int high){
        if (low<high){
            int pa = partition(r,low,high);
            quickSort(r,low,pa-1);
            quickSort(r,pa+1,high);
        }
    }
    private int partition(int[] r, int low, int high){
        int pivot = r[low];  //使用 r[low]作为枢轴元素
        while (low<high){ //从两端交替向内扫描
            while(low<high&&r[high]>=pivot) high--;
            r[low] = r[high];  //将比 pivot 小的元素移向低端
            while(low<high&&r[low]<=pivot) low++;
            r[high] = r[low];  //将比 pivot 大的元素移向高端
        }
        r[low] = pivot; //设置枢轴
        return low;  //返回枢轴元素位置
    }

}
