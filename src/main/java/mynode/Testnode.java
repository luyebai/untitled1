package mynode;

import java.util.List;

public class Testnode {
    public static void main(String[] args) {
        //节点的创建和添加
        MyNode<String> node = new MyNode<>("根节点", null);
        MyNode<String> node1 = new MyNode<>("第一层节点1", node);
        MyNode<String> node2 = new MyNode<>("第一层节点2", node);
        node.addChild(node1);node.addChild(node2);

        MyNode<String> node11 = new MyNode<>("第二层节点11", node1);
        MyNode<String> node12 = new MyNode<>("第二层节点12", node1);
        MyNode<String> node21 = new MyNode<>("第二层节点21", node2);
        MyNode<String> node22 = new MyNode<>("第二层节点22", node2);
        node1.addChild(node11);node1.addChild(node12);
        node2.addChild(node21);node2.addChild(node22);

        trace(node);

    }

    //遍历指定节点
    private static void trace(MyNode<String> myNode){
        System.out.print("第"+myNode.getDepth()+"层");
        System.out.println(myNode.getData());
        for(MyNode mn:myNode.getChildList()){
            if(mn.getChildList()!=null){
                trace(mn);
            }
        }
    }
}
