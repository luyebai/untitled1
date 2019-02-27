package dataStutas.bintreenode;

import dataStutas.linkedtable.Iterator;
import dataStutas.linkedtable.LinkedList;
import dataStutas.linkedtable.LinkedListDLNode;

public class PreOrder {
    public static void main(String[] args) {
        BinTreeNode node = new BinTreeNode();

        BinTreeNode node1 = new BinTreeNode();
        BinTreeNode node2 = new BinTreeNode();
        node.setLChild(node1);
        node.setRChild(node2);

        Iterator iterator = node.postOrder();
       // Iterator elements = listDLNode.elements();
        while(!iterator.isDone()){
            BinTreeNode node3 = (BinTreeNode) iterator.currentItem();
            System.out.println(node3);
            iterator.next();
        }

    }
}
