package dataStutas.linkedtable;

public class DLNode implements Node {
    private Object element;
    private DLNode pre;//指向前驱
    private DLNode next;//指向后继

    public DLNode() {
        this(null,null,null);
    }

    public DLNode getPre() {
        return pre;
    }

    public void setPre(DLNode pre) {
        this.pre = pre;
    }

    public DLNode getNext() {
        return next;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }

    public DLNode(Object element, DLNode pre, DLNode next) {
        this.element = element;
        this.pre = pre;
        this.next = next;
    }

    @Override
    public Object getDate() {
        return element;
    }

    @Override
    public void setDate(Object object) {
        element=object;
    }
}
