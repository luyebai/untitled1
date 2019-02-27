package dataStutas.bintreenode;

import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.ArrayList;

/**
 * 哈夫曼树的结点类实现
 * 以及通过一个节点数组构建一棵哈夫曼树的算法实现
 */
public class HuffmanTreeNode extends  BinTreeNode{
    private int weight; //权值
    private String coding=""; //编码
    public HuffmanTreeNode(int weight){this(weight,null);}

    public HuffmanTreeNode(int weight, Object e) {
        super(e);
        this.weight=weight;
    }

    //改写父类方法,直接调用父类的方法，返回类型不同了
    public HuffmanTreeNode getParent(){
        return (HuffmanTreeNode) super.getParent();
    }
    public HuffmanTreeNode getLChild() {
        return (HuffmanTreeNode)super.getLChild();
    }
    public HuffmanTreeNode getRChild() {
        return (HuffmanTreeNode)super.getRChild();
    }
    //get&set方法

    public int getWeight() {
        return weight;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    //构造一棵Huffman树的算法
    private static HuffmanTreeNode buildHuffmanTree(HuffmanTreeNode[] nodes){
        int n=nodes.length;//获取数组长度
        if(n<2) return nodes[0]; //如果数组中只有一个值，则返回数组中的第一个元素
        ArrayList<HuffmanTreeNode> list = new ArrayList<>();
        for (int i = 0; i <n ; i++) {//将数组的所有结点插入到集合中。
            insertToList(list,nodes[i]);//插入方法按weight大小进行了排序
        }
        for (int i = 1; i <n ; i++) {//选择weight最小的两棵树合并，循环n-1次
            HuffmanTreeNode min1 = list.remove(list.size()-1);
            HuffmanTreeNode min2 = list.remove(list.size() - 2);
            //将取出的两个元素作为左右子节点放入一个新节点
            HuffmanTreeNode newRoot = new HuffmanTreeNode(min1.getWeight() + min2.getWeight());
            newRoot.setLChild(min1);
            newRoot.setRChild(min2);
            insertToList(list,newRoot);//新节点插入到线性表中
        }
        return list.get(0);
    }
        //将节点按照weight从大到小的顺序插入线性表
    private static void insertToList(ArrayList<HuffmanTreeNode> list, HuffmanTreeNode node) {
        for (int i = 0; i <list.size() ; i++) {
            //如果它比其中的一个元素大，则插入到这个元素的位置。其他的顺位后移
            if(node.getWeight()>list.get(i).getWeight()){
                list.add(i,node);
                return;
            }
        }
        //否则添加到最后一个位置。
        list.add(node);
    }

    /**
     *递归生成Huffman编码
     */
       private static void generateHuffmanCode(HuffmanTreeNode root){
           if(root==null)return;
           if(root.hasParent()){
               if(root.isLChild())
                   root.setCoding(root.getParent().getCoding()+"0");//向左为叠加0
               else
                   root.setCoding(root.getParent().getCoding()+"1");//向右为叠加1
           }
           generateHuffmanCode(root.getLChild());//对左孩子进行递归编码赋值
           generateHuffmanCode(root.getRChild());//对右孩子进行递归编码赋值
       }
}
