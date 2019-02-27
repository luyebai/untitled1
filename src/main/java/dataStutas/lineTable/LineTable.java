package dataStutas.lineTable;

import java.util.Arrays;

/**
 * 线性表的数组实现
 */
public class LineTable {
    private final int LEN=8;//数组的大小为8
    private int size;//线性表中数据元素的个数
    private Object[] elements;//数据元素数组

    //初始化
    public LineTable() {
        elements=new Object[LEN];
        size=0;
    }
    //返回线性表大小
    public int getSize(){
        return  size;
    }
    //如果线性表为空返回true，否则返回false
    public boolean isEmpty(){
        return size==0;
    }
    //判断线性表中事发欧包含元素e
    public boolean contains(Object e){
        for (int i = 0; i <size ; i++) {
            if(elements[i]==e){
                return true;
            }
        }
        return false;
    }
    //返回数据元素e在线性表中的序号
    public int indexOf(Object e){
        for (int i = 0; i <elements.length ; i++) {
            if(elements[i]==e){
                return i;
            }
        }
        return -1;
    }
    //将元素e插入到线性表中i号位置
    public void insert(int i,Object e){
        if(size<0||i>size){
            throw new ArrayIndexOutOfBoundsException("指定的索引不存在");
        }
        if(size>=elements.length){
            expandSpace();
        }
        for (int j = size; j >i ; j--) {
            elements[j]=elements[j-1];
        }
        elements[i]=e;
        size++;
    }
    //添加一个数组进来
    public void insertAll(Object[] obj){
        if(size<0){
            throw new ArrayIndexOutOfBoundsException();
        }
        for (Object o : obj) {//遍历这个数组
                if(size>=elements.length){
                    expandSpace();//扩容方法
                }
                elements[size]=o;//将元素添加
                size++;//大小加1
        }
    }

    //扩容
    public void expandSpace(){
        Object[] a=new Object[elements.length*2];
        for (int i = 0; i <elements.length ; i++) {
            a[i]=elements[i];
        }
        elements=a;
    }
    //将元素e插入到元素obj之前
    public boolean insertBefore(Object object,Object e){
        int i=indexOf(object);//调用现有方法
        if(i<0) return false;
        insert(i,e);
        return true;
    }
    //将元素e插入到元素obj之后
    public boolean insertAfter(Object object,Object e){
        int i=indexOf(object);//调用现有方法
        if(i<0) return false;
        insert(i+1,e);
        return true;
    }
    //删除线性表中序号为i的元素，并返回此元素
    public Object remove(int i){
        if(i<0||i>=size)throw new ArrayIndexOutOfBoundsException("索引出界");
        Object obj=elements[i];
        for (int j = i; j <size-1 ; j++) {
            elements[j]=elements[j+1];//所有元素前移一位
        }
        elements[--size]=null;//减小size并将最后一位设置为null
        return obj;
    }
    //删除线性表中第一个与e相同的元素
    public boolean remove(Object e){
        int i=indexOf(e);
        if(i<0)return false;
        remove(i);//调用根据索引删除。
        return true;
    }
    //替换线性表中序号为i的数据元素为e，返回被替换的数据元素
    public Object replace(int i,Object e){
        if(i<0||i>=size)throw new ArrayIndexOutOfBoundsException("索引出界");
        Object obj=elements[i];
        elements[i]=e;
        return obj;
    }
    //返回线性表中序号为i的数据元素
    public Object get(int i){
        if(i<0||i>=size)throw new ArrayIndexOutOfBoundsException("索引出界");
        return elements[i];
    }

    @Override
    public String toString() {
        return "LineTable{"+ Arrays.toString(elements)+"}";
    }
}
