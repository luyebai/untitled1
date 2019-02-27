package dataStutas.linkedtable;

public interface LinkedList {
    //查询链接表当前的规模
    public int getSize();
    //判断是否为空
    public boolean isEmpty();
    //返回第一个节点
    public Node first();
    //返回最后一个节点
    public Node last();
    //返回p之后的结点
    public Node getNext(Node p);
    //返回p之前的结点
    public Node getPre(Node p) throws InvalidNodeException;
    //将 e 作为第一个元素插入链接表,并返回 e 所在结点
    public Node insertFirst(Object e);
    //将 e 作为最后一个元素插入列表,并返回 e 所在结点
    public Node insertLast(Object e);
    //将 e 插入至 p 之后的位置,并返回 e 所在结点
    public Node insertAfter(Node p, Object e) throws InvalidNodeException;
    //将 e 插入至 p 之前的位置,并返回 e 所在结点
    public Node insertBefore(Node p, Object e) throws InvalidNodeException;
    //删除给定位置处的元素，并返回之
    public Object remove(Node p);
    //删除首元素，并返回之
    public Object removeFirst();
    //删除末元素，并返回之
    public Object removeLast();
    //将处于给定位置的元素替换为新元素，并返回被替换的元素
    public Object replace(Node p, Object e) throws InvalidNodeException;
    //元素迭代器
    public dataStutas.linkedtable.Iterator elements();
}
