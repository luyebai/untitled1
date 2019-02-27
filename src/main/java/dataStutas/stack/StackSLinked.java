package dataStutas.stack;

import dataStutas.linkedtable.SLNode;

/**
 * 所有操作都是在O(1)时间内完成的。
 */
public class StackSLinked implements Stack {
    private SLNode top;//链表的首节点引用
    private int size;//栈大小

    public StackSLinked() {
        top=null;
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

    @Override//数据元素e入栈
    public void push(Object e) {
        //创建一个新节点，元素数据是e，下一个指针是当前top
        SLNode q = new SLNode(e, top);
        top=q;//top指针上移
        size++;
    }

    @Override
    public Object pop() throws StackEmptyException {
        if(size<1)throw new RuntimeException("堆栈为空");
        Object obj = top.getDate();//获取top的当前数据
        top=top.getNext();//栈顶指针下移
        size--;
        return obj;
    }

    @Override
    public Object peek() throws StackEmptyException {
        if(size<1)throw new RuntimeException("堆栈为空");
        return top.getDate();
    }
}
