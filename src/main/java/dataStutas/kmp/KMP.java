package dataStutas.kmp;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class KMP {
    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println(kmp("ababababca", "ababca"));

    }

    public static int kmp(String str, String math) {
        int[] next = getNext(math);//获取next数组
        char[] strarray = str.toCharArray();
        char[] matharray = math.toCharArray();
        int i = 0;//str的指针初值
        int j = 0;//math的指针初值
        while (i < strarray.length && j < matharray.length) {//如果还没有比对到字符串结尾
            if (j == -1 || strarray[i] == matharray[j]) {
                j++;//如果是第一次比对，或者比对的字符串相等，就比对下一个
                i++;
            } else {//如果不相等，根据next数组，从matharray的指定位置开始比对
                j = next[j];
            }
        } //如果匹配成功返回索引，否则返回-1
        if (j == matharray.length)
            return i - j;
        else
            return -1;
    }

    //求next数组的实现
    public static int[] getNext(String ps) {

        char[] p = ps.toCharArray();
        //建立数组，存放pmt值
        int[] next = new int[p.length];
        //第一个位置设置为-1，方便使用，因为字符串本身自己是不存在PMT值的
        //PMT中的值是字符串的前缀集合与后缀集合的交集中最长元素的长度
        next[0] = -1;
        //p的指针
        int j = 0;
        //比对的指针
        int k = -1;
        while (j < p.length - 1) {//如果没有比对到结尾就循环
            //如果是第一次匹配，或者匹配相等
            if (k == -1 || p[j] == p[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
