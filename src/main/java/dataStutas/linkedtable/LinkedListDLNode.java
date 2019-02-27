package dataStutas.linkedtable;

import java.util.Iterator;

/**
 * 基于双向链表实现的链接表
 */
public class LinkedListDLNode implements LinkedList {
    private int size;//规模
    private DLNode head;//头结点，哑元结点
    private DLNode tail;//尾结点，哑元结点

    public LinkedListDLNode() {
        size=0;
        //构建只有头尾节点的空链表
        head=new DLNode();
        tail=new DLNode();
        head.setNext(tail);
        tail.setPre(head);
    }

    //判断节点p是否合法，如果合法转换为DLNode
    protected  DLNode checkPosition(Node p){
        if(p==null||p==head||p==tail){
            throw new InvalidNodeException("节点错误");
        }
       DLNode node= (DLNode)p;
        return node;
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
    public Node first() {
        if(isEmpty()) throw new RuntimeException("空表");
        return head.getNext();
    }

    @Override
    public Node last() {
        if(isEmpty()) throw new RuntimeException("空表");
        return tail.getPre();
    }

    @Override
    public Node getNext(Node p) {
        DLNode node = checkPosition(p);
        DLNode next = node.getNext();
        if(next==tail)
            throw new RuntimeException("最后一个了");
        return next;
    }

    @Override
    public Node getPre(Node p) throws InvalidNodeException {
        DLNode node = checkPosition(p);
        DLNode pre = node.getPre();
        if(pre==head)
            throw new RuntimeException("第一个了");
        return pre;
    }

    @Override
    public Node insertFirst(Object e) {
        DLNode node = new DLNode(e, head, head.getNext());
        head.getNext().setPre(node);
        head.setNext(node);
        size++;
        return node;
    }

    @Override
    public Node insertLast(Object e) {
        DLNode node = new DLNode(e, tail.getPre(), tail);
        tail.getPre().setNext(node);
        tail.setPre(node);
        size++;
        return node;
    }

    @Override
    public Node insertAfter(Node p, Object e) throws InvalidNodeException {
        DLNode node = checkPosition(p);
        DLNode newNode = new DLNode(e,node,node.getNext());
        node.getNext().setPre(newNode);
        node.setNext(newNode);
        size++;
        return newNode;
    }

    @Override
    public Node insertBefore(Node p, Object e) throws InvalidNodeException {
        DLNode node = checkPosition(p);
        DLNode newNode = new DLNode(e,node.getPre(),node);
        node.getPre().setNext(newNode);
        node.setPre(newNode);
        size++;
        return newNode;
    }

    @Override
    public Object remove(Node p) {
        DLNode node = checkPosition(p);
        Object obj = node.getDate();
        node.getPre().setNext(node.getNext());
        node.getNext().setPre(node.getPre());
        size--;
        return obj;
    }

    @Override
    public Object removeFirst() {
        return remove(head.getNext());
    }

    @Override
    public Object removeLast() {
        return remove(tail.getPre());
    }

    @Override
    public Object replace(Node p, Object e) throws InvalidNodeException {
        DLNode node = checkPosition(p);
        Object obj = node.getDate();
        node.setDate(e);
        return obj;
    }

    @Override
    public dataStutas.linkedtable.Iterator elements() {
        return  new LinkedListIterator(this);
    }
}
