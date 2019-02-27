package mynode;

import java.util.ArrayList;
import java.util.List;

public class MyNode<T> {
    //存放数据
    private T data;
    //父节点
    private MyNode<T> parent;
    //子节点
    private List<MyNode> childList;


    public Integer getDepth() {
        return  new NodeUtils().getDepth(this);
    }

    //构造方法，传入数据和节点，然后这个节点就拥有一个子节点集合
    public MyNode(T data, MyNode<T> parent) {
        childList = new ArrayList<MyNode>();
        this.data = data;
        this.parent = parent;
    }

    //添加子节点的方法
    public boolean addChild(MyNode<T> childNode) {
        try {

            childList.add(childNode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MyNode<T> getParent() {
        return parent;
    }

    public List<MyNode> getChildList() {
        return childList;
    }

}
