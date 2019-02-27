package dataStutas.queue;

/**
 * 以少使用一个存储单元（用于区别队满和队空的状态）的方案实现循环队列，
 * 另一种方案是使用size来标识队满
 *
 * 循环队列的实现，队首指针和队尾指针的重置依赖模运算来达到循环的目的。
 */
public class QueueArray implements Queue {
    private static final int CAP=7;//队列默认大小
    private Object[] elements;//数据元素数组
    private int capacity;//数组的大小elements.length
    private int front;//队首指针
    private int rear;//队尾指针，指向队尾后一个位置。

    public QueueArray() {
        this(CAP);
    }
    public QueueArray(int cap){
        capacity=cap+1;
        elements=new Object[capacity];
        front=rear=0;
    }

    @Override
    public int getSize() {
        /**
         * 加capacity再%capacity的原因是：
         * 答：如果队尾一直比队首大当然可以
         *     但是，循环队列中，队尾有时会比
         *     队首小，如果不加上capacity会出现负数。
         *     负数是不合法的。
         */
        return (rear-front+capacity)%capacity;
    }

    @Override//队首和队尾在同一位置时，队空
    public boolean isEmpty() {
        return front==rear;
    }

    @Override//入队
    public void enqueue(Object e) {
        if(getSize()==capacity-1)expandSpace();
        elements[rear]=e;//队尾位置是空的所以直接插入队尾
        //重置队尾，%capacity的原因跟求大小一样
        rear=(rear+1)%capacity;
    }
    //扩容方法
    private void expandSpace() {
        Object[] a = new Object[elements.length * 2];
        int i=front;//i=队首
        int j=0;
        while (i!=rear){//将从front开始到rear前一个存储单元的元素复制到新数组。
            a[j++]=elements[i];
            i=(i+1)%capacity;
        }
        elements=a;
        capacity=elements.length;
        front=0;rear=j;//设置新的队首、队尾指针。
    }

    @Override//队首出队
    public Object dequeue() throws QueueEmptyException {
        if(isEmpty()) throw new QueueEmptyException("队列为空");
        Object obj = elements[front];
        elements[front]=null;//取出后清空原来的位置
        front=(front+1)%capacity;//重置队首指针
        return obj;
    }

    @Override//获取但不出队
    public Object peek() throws QueueEmptyException {
        if (isEmpty())
            throw new QueueEmptyException("错误：队列为空");
        return elements[front];
    }
}
