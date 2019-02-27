package dataStutas.sorter;

import sun.applet.Main;

import java.util.Arrays;

/**
 * 排序的实现
 */
public class Sorter {

    public static void main(String[] args) {
        int[] arr={43,83,94,52,22,12,42,63};
        //int[] arr2={5,3,1};
       // binInsertSort(arr,0,arr.length-1);
        //shellSort(arr,0,arr.length-1,arr2);
        Sorter sorter = new Sorter();
        sorter.radixSort(arr,10,2);
        System.out.println(Arrays.toString(arr));
       // quickSort(arr,0,arr.length-1);
    }

    /**
     * 基数排序
     * @param data  待排数组
     * @param radix  数组中数字的进制比如十进制是10
     * @param d  几位数比如100以下是2位数，写2
     */
    public static void radixSort(int[] data, int radix, int d) {
        // 缓存数组
        int[] tmp = new int[data.length];
        // buckets用于记录待排序元素的信息
        // buckets数组定义了max-min个桶
        int[] buckets = new int[radix];

        for (int i = 0, rate = 1; i < d; i++) {

            // 重置count数组，开始统计下一个关键字
            Arrays.fill(buckets, 0);
            // 将data中的元素完全复制到tmp数组中
            System.arraycopy(data, 0, tmp, 0, data.length);

            // 计算每个待排序数据的子关键字
            for (int j = 0; j < data.length; j++) {
                int subKey = (tmp[j] / rate) % radix;
                buckets[subKey]++;
            }

            for (int j = 1; j < radix; j++) {
                buckets[j] = buckets[j] + buckets[j - 1];
            }

            // 按子关键字对指定的数据进行排序
            for (int m = data.length - 1; m >= 0; m--) {
                int subKey = (tmp[m] / rate) % radix;
                data[--buckets[subKey]] = tmp[m];
            }
            rate *= radix;
        }

    }


    /**
     * 计数排序
     * @param a
     * @return
     */
    public  int[] countSort(int[]a){
        int b[] = new int[a.length];
        int max = a[0],min = a[0];
        for(int i:a){//通过循环得到数组中的最大值和最小值
            if(i>max){
                max=i;
            }
            if(i<min){
                min=i;
            }
        }//这里k的大小是要排序的数组中，元素大小的极值差+1
        int k=max-min+1;
        int c[]=new int[k];
        for(int i=0;i<a.length;++i){
            c[a[i]-min]+=1;//优化过的地方，减小了数组c的大小
        }
        for(int i=1;i<c.length;++i){
            c[i]=c[i]+c[i-1];
        }
        for(int i=a.length-1;i>=0;--i){
            b[--c[a[i]-min]]=a[i];//按存取的方式取出c的元素
        }
        return b;
    }

    /**
     * 直接插入排序的实现
     * @param r  待排数组，int为例
     * @param low  排序的区间[low，high]
     * @param high
     */
    public void insertSort(int[] r, int low, int high){
        for (int i=low+1; i<=high; i++)
            if (r[i]<r[i-1]){  //小于时，需将 r[i]插入有序表
                int temp = r[i];
                r[i] = r[i-1];
                int j=i-2;
                for (; j>=low&&temp<r[j]; j--)
                    r[j+1] = r[j]; //记录后移
                r[j+1] = temp; //插入到正确位置
            }
    }

    /**
     * 折半插入排序所需的辅助空间与直接插入排序相同，从时间上比
     * 较，折半插入排序仅减少了元素的比较次数，但是并没有减少元素的移动次数，
     * 因此折半插入排序的时间复杂度仍为O(n^2 )
     * 折半插入排序，对插入排序的改进
     * @param r  待排数组，int为例
     * @param low  排序的区间[low，high]
     * @param high
     */
    public static void binInsertSort(int[] r, int low, int high){
        for (int i=low+1; i<=high; i++){
            int temp = r[i]; //保存待插入元素
            int hi = i-1; int lo = low;  //设置初始区间
            while (lo<=hi){  //折半确定插入位置每次在有序表中折半查找插入位置
                int mid = (lo+hi)/2;
                if(temp<r[mid])//如果找到了位置，则使用hi提取索引
                    hi = mid - 1;
                else lo = mid + 1;
            }
            for (int j=i-1;j>hi;j--) r[j+1] = r[j]; //移动元素
            r[hi+1] = temp;  //插入元素
        }//for
    }

    /**
     * 希尔排序的实现
     * @param r   待排数组
     * @param low  排序区间[low,high]
     * @param high
     * @param delta  步长序列,一般使用数组长度除二的序列例如:如果数组长度是10，
     *               则步长序列使用5,2,1  但此步长序列并不一定是最好的
     *               在选择步长序列时应当注意：应使步长序列中的步长值
     * 互质，并且最后一个步长值必须等于 1。
     */
    public static void shellSort(int[] r, int low, int high, int[] delta){
        for (int k=0;k<delta.length;k++)//循环次数为步长序列的长度
            shellInsert(r, low, high, delta[k]); //一趟步长为 delta[k]的直接插入排序
        System.out.println(Arrays.toString(r));
    }
    private static void shellInsert(int[] r, int low, int high, int deltaK){
        for (int i=low+deltaK; i<=high; i++)
            if (r[i]<r[i-deltaK]){ //小于时，需将 r[i] 插入有序表
                int temp = r[i];//if中就是一个直接插入排序
                int j = i-deltaK;
                for(; j>=low&&temp<r[j]; j=j-deltaK)
                r[j+deltaK] = r[j]; //记录后移  [j];
                r[j+deltaK] = temp; //插入到正确位置
            }
    }

    /**
     * 快速排序
     * @param r   待排序的队列
     * @param low 排序区间[low,high]
     * @param high
     */
    public static void quickSort(int[] r, int low, int high){
        if (low<high){
            int pa = partition(r,low,high);
            quickSort(r,low,pa-1);
            quickSort(r,pa+1,high);
        }
    }
    private static int partition(int[] r, int low, int high){
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

    /**
     * 堆排序
     * @param r
     */
    public void heapSort(int[] r){
        int n = r.length - 1;
        for (int i=n/2; i>=0; i--) //初始化建堆
            heapAdjust(r,i,n);
        for (int i=n; i>0; i--){  //不断输出堆顶元素并调整 r[1..i-1]为新堆
            int temp = r[0]; //交换堆顶与堆底元素
            r[0] = r[i];
            r[i] = temp;
            heapAdjust(r,0,i-1); //调整
        }
        System.out.println(Arrays.toString(r));
    }
    //构建堆
    private void heapAdjust(int[] r, int low, int high){
        int temp = r[low];
        for (int j=2*low; j<=high; j=j*2){ //沿关键字较大的元素向下进行筛选
            //j 指向关键之较大的元素
            if (j<high&&r[j]<r[j+1]) j++;
//若 temp 比其孩子都大，则插入到 low 所指位置
            if (temp>=r[j]) break;
            r[low] = r[j]; low = j; //向下筛选
        }
        r[low] = temp;
    }

    /**
     * 归并排序
     * 对指定的区间进行排序
     * @param r
     * @param low
     * @param high
     */
    public void mergeSort(int[] r, int low, int high){
        if (low<high){
            mergeSort(r,low,(high+low)/2);
            mergeSort(r,(high+low)/2+1,high);
            merge(r,low,(high+low)/2,high);
        }
    }

    /**
     *
     * 待合并的两个有序区间[p..q]以及[q+1..r]
     * @param a
     * @param p
     * @param q
     * @param r
     */
    private void merge(int[] a, int p, int q, int r){
        int[] b = new int[r-p+1];
        int s = p;
        int t = q+1;
        int k = 0;
        while (s<=q&&t<=r)
            if (a[s]<a[t])
                b[k++] = a[s++];
            else
                b[k++] = a[t++];
        while (s<=q) b[k++] = a[s++];
        while (t<=r) b[k++] = a[t++];
        for (int i=0; i<b.length; i++)
            a[p+i] = b[i];
    }
}
