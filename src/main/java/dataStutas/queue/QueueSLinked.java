package dataStutas.queue;

import dataStutas.linkedtable.SLNode;

public class QueueSLinked implements Queue {
    private SLNode front;//队首
    private  SLNode rear;//队尾
    private int size;    //队列元素个数

    public QueueSLinked() {
        front=new SLNode();
        rear=front;//初始状态，队首和队尾相同。
        size=0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void enqueue(Object e) {
        SLNode slNode = new SLNode(e, null);
        //设置原队尾元素的下一个指针是添加的元素
        //设置队尾指针指向新队尾。
        rear.setNext(slNode);
        rear=slNode;
        size++;
    }

    @Override
    public Object dequeue() throws QueueEmptyException {
        if(size<1)throw new QueueEmptyException("空队");
        SLNode p = front.getNext();//取出当前头结点的元素
        front.setNext(p.getNext());//将队头指针指向当前元素的下一个元素。
        size--;
        if(size<1)rear=front;//如果队列空了，rear指向头结点
        return p;
    }

    @Override
    public Object peek() throws QueueEmptyException {
        if(size<1)
            throw new QueueEmptyException("空队列");
        return front.getNext().getDate();//队头指针的
    }
}
