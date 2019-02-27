package dataStutas.linkedtable;

public class SLNode implements Node {
    private Object element;//当前元素
    private SLNode next;//下一个节点

    public SLNode() {
        this(null,null);
    }

    public SLNode(Object element, SLNode next) {
        this.element = element;
        this.next = next;
    }

    public SLNode getNext() {
        return next;
    }

    public void setNext(SLNode next) {
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
