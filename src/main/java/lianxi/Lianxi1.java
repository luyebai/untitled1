package lianxi;
import java.util.LinkedList;
import java.util.List;
public class Lianxi1 {

    /**
     * 功能：把一个数组的值存入二叉树中，然后进行3种方式的遍历
     *
     * 参考资料0:数据结构(C语言版)严蔚敏
     *
     */
        private int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };//准备数组
        private static List<Node> nodeList = null;//初始化节点集合

        /**
         * 内部类：节点
         */
        private static class Node {
            Node leftChild;//左子节点
            Node rightChild;//右子结点
            int data;       //节点数据

            Node(int newData) {//构造一个节点时，将左右子节点都初始化为空，只传入数据
                leftChild = null;
                rightChild = null;
                data = newData;
            }
        }

        public void createBinTree() {//节点方法，创建二叉树
            nodeList = new LinkedList<Node>();//创建节点集合
            // 将一个数组的值依次转换为Node节点
            for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {
                nodeList.add(new Node(array[nodeIndex]));//将构建的所有节点添加入节点集合
            }
            // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
            for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
                // 左孩子
                nodeList.get(parentIndex).leftChild = nodeList
                        .get(parentIndex * 2 + 1);
                // 右孩子
                nodeList.get(parentIndex).rightChild = nodeList
                        .get(parentIndex * 2 + 2);
            }
            // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
            int lastParentIndex = array.length / 2 - 1;
            // 左孩子
            nodeList.get(lastParentIndex).leftChild = nodeList
                    .get(lastParentIndex * 2 + 1);
            // 右孩子,如果数组的长度为奇数才建立右孩子
            if (array.length % 2 == 1) {
                nodeList.get(lastParentIndex).rightChild = nodeList
                        .get(lastParentIndex * 2 + 2);
            }
        }

        /**
         * 先序遍历
         *
         * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
         *
         * @param node
         *            遍历的节点
         */
        public static void preOrderTraverse(Node node) {
            if (node == null)
                return;
            System.out.print(node.data + " ");
            preOrderTraverse(node.leftChild);
            preOrderTraverse(node.rightChild);
        }

        /**
         * 中序遍历
         *
         * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
         *
         * @param node
         *            遍历的节点
         */
        public static void inOrderTraverse(Node node) {
            if (node == null)
                return;
            inOrderTraverse(node.leftChild);
            System.out.print(node.data + " ");
            inOrderTraverse(node.rightChild);
        }

        /**
         * 后序遍历
         *
         * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
         *
         * @param node
         *            遍历的节点
         */
        public static void postOrderTraverse(Node node) {
            if (node == null)
                return;
            postOrderTraverse(node.leftChild);
            postOrderTraverse(node.rightChild);
            System.out.print(node.data + " ");
        }

        public static void main(String[] args) {
            Lianxi1 binTree = new Lianxi1();
            binTree.createBinTree();
            // nodeList中第0个索引处的值即为根节点
            Node root = nodeList.get(0);

            System.out.println("先序遍历：");
            preOrderTraverse(root);
            System.out.println();

            System.out.println("中序遍历：");
            inOrderTraverse(root);
            System.out.println();

            System.out.println("后序遍历：");
            postOrderTraverse(root);
        }
}
