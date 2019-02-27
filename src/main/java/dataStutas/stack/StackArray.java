package dataStutas.stack;

public class StackArray implements Stack {
    private final int LEN = 8; //数组的默认大小
    private Object[] elements;//数据元素数组
    private int top;//栈顶指针

    public StackArray() {
        top = -1;
        elements = new Object[LEN];
    }

    @Override
    public int getSize() {
        return top + 1;//如果是空返回0，有元素是返回正确值。
    }

    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    //添加元素e入栈
    @Override
    public void push(Object e) {
        //扩容方法
        if (getSize() >= elements.length) expandSpace();
        //在top+1位置添加元素
        elements[++top] = e;
    }

    private void expandSpace() {
        Object[] obj = new Object[elements.length * 2];
        //循环栈数组中所有元素
        System.arraycopy(elements, 0, obj, 0, elements.length);
        elements = obj;
    }

    @Override//栈顶元素出栈
    public Object pop() throws StackEmptyException {
        if(getSize()<1)
            throw new RuntimeException("栈空");
        Object obj=elements[top];//将栈顶元素取出
        elements[top--]=null;//清空栈顶，并top-1
        return obj;
    }

    @Override//获取栈顶元素但不出栈
    public Object peek() throws StackEmptyException {
        if(getSize()<1)
            throw new RuntimeException("栈空");
        return elements[top];
    }
}
