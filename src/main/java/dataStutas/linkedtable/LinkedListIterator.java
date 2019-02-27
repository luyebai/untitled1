package dataStutas.linkedtable;

import java.util.Stack;

public class LinkedListIterator implements Iterator {
    private LinkedList list;//链接表
    private Node current;//当前节点

    public LinkedListIterator(LinkedList list) {
        this.list = list;
        //如果是空表则当前节点为空，否则当前节点是第一个元素
        if(list.isEmpty()) current=null;
        else current=list.first();
    }
    //移动到第一个元素
    @Override
    public void first() {
        if(list.isEmpty()) current=null;
        else current=list.first();
    }

    @Override
    public void next() {
        if(isDone()) throw new RuntimeException("没有元素了");
        if(current==list.last()) current=null;//如果到达最后一个则置为null
        else current=list.getNext(current);//否则去取下一个
    }

    @Override
    public boolean isDone() {
        return current==null;
    }

    @Override
    public Object currentItem() {
        if(isDone()) throw new RuntimeException("没有元素了");
        return current.getDate();
    }
}
