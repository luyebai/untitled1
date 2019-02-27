package dataStutas.linkedtable;

/**
 * 因为没有写比较规则，所以这个线性表类可能有一点问题。
 */
public class ListSLinked {
    private SLNode head;//单链表首节点引用
    private int size;

    //初始化这个线性表
    public ListSLinked() {
        head = new SLNode();
        size = 0;
    }

    //获取数据元素e所在节点的前驱
    private SLNode getPreNode(SLNode e) {
        SLNode p = head;
        while (p.getNext() != null) {//如果不是最后一个节点
            //如果当前节点的下一个节点的数据域等于传进来节点的数据域
            if (p.getNext().getDate() == e.getDate()) {
                return p;
            } else p = p.getNext();
        }
        return null;
    }

    //获取序号为0<=i<size的元素所在结点的前驱结点
    private SLNode getPreNode(int i) {//获取第I个结点的前驱
        SLNode p = head;//从头结点开始，找第10个结点的话，就循环10次
        for (; i > 0; i--) p = p.getNext();
        return p;
    }

    //获取序号为0<=i<size的元素所在节点
    private SLNode getNode(int i) {//获取第I个结点
        SLNode p = head.getNext();//同理
        for (; i > 0; i--) p = p.getNext();
        return p;
    }

    //返回线性表的大小，即数据元素的个数
    public int getSize() {
        return size;
    }

    //判断线性表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //判断线性表是否包含数据元素e
    public boolean contains(SLNode e) {
        SLNode p = head.getNext();
        while (p != null)
            if (p.getDate() == e.getDate()) return true;
            else p = p.getNext();
        return false;
    }

    //返回数据元素e在线性表中的序号
    public int indexOf(SLNode e) {
        SLNode p = head.getNext();
        int index = 0;
        while (p != null)
            if (p.getDate() == e.getDate()) return index;
            else {
                index++;
                p = p.getNext();
            }
        return -1;
    }

    //将数据元素e插入到线性表中i号位置
    public void insert(int i, SLNode e) {
        if (i < 0 || i > size) {
            throw new ArrayIndexOutOfBoundsException();
        }//获取第i个元素的前驱
        SLNode p = getPreNode(i);
        SLNode q = new SLNode(e, p.getNext());
        p.setNext(q);
        size++;
    }

    //将数据元素e插入到元素obj之前
    public boolean insertBefore(SLNode obj, SLNode e) {
        SLNode p = getPreNode(obj);//获取此节点的前驱
        if (p != null) {
            SLNode q = new SLNode(e, p.getNext());
            p.setNext(q);
            size++;
            return true;
        }
        return false;
    }

    //将数据元素e插入到元素obj之后
    public boolean insertAfter(SLNode obj, SLNode e) {
        SLNode p = getPreNode(obj);
        while(p!=null)
        if (p.getDate()==obj.getDate()) {
            SLNode q = new SLNode(e, p.getNext());
            p.setNext(q);
            size++;
        } else p = p.getNext();
        return false;
    }
    //删除线性表中序号为i的元素，并返回之
    public Object remove(int i){
        if(i<0||i>size){
            throw new IndexOutOfBoundsException();
        }
        SLNode p = getPreNode(i);//获取被删除元素的前驱
        SLNode next = p.getNext();
            if(p!=null){
            p.setNext(p.getNext().getNext());
        }
        size--;
        return next;
    }
    //删除线性表中第一个与e相同的元素
    public boolean remove(SLNode e){
        SLNode p = getPreNode(e);
        if(p!=null){
            p.setNext(p.getNext().getNext());
            size--;
            return true;
        }
        return false;
    }
    //替换线性表中的序号为i的数据元素e，返回原数据元素
    public Object replace(int i,SLNode e){
        if(i<0||i>size){
            throw new IndexOutOfBoundsException();
        }
        SLNode p = getNode(i);
        Object date = p.getDate();
        p.setDate(e);
        return date;
    }
    //返回线性表中序号为i的数据元素
    public Object get(int i){
        if(i<0||i>size){
            throw new IndexOutOfBoundsException();
        }
       return getNode(i).getDate();
    }
}
