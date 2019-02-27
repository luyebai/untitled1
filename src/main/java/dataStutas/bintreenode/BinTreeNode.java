package dataStutas.bintreenode;

import dataStutas.lineTable.Strategy;
import dataStutas.linkedtable.Iterator;
import dataStutas.linkedtable.LinkedList;
import dataStutas.linkedtable.LinkedListDLNode;
import dataStutas.linkedtable.Node;
import dataStutas.queue.Queue;
import dataStutas.queue.QueueArray;
import dataStutas.stack.StackSLinked;

/**
 * 三叉链表来实现二叉树的存储
 */
public class BinTreeNode implements Node {
    private Object data;//数据域
    private BinTreeNode parent;//父节点
    private BinTreeNode lChild;//左孩子
    private BinTreeNode rChild;//右孩子
    private int height;//以该节点为根的子树的高度
    private int size;//该节点的子孙数（包括节点本身）

    public BinTreeNode() {
        this(null);
    }

    public BinTreeNode(Object e) {
        data = e;
        height = 0;
        size = 1;
        parent = lChild = rChild = null;
    }

    //接口方法
    @Override
    public Object getDate() {
        return data;
    }

    @Override
    public void setDate(Object obj) {
        data = obj;
    }

    /******辅助方法,判断当前结点位置情况******/
    //判断是否有父亲
    public boolean hasParent() {
        return parent != null;
    }

    //判断是否有左孩子
    public boolean hasLChild() {
        return lChild != null;
    }

    //判断是否有右孩子
    public boolean hasRChild() {
        return rChild != null;
    }

    //判断是否为叶子结点
    public boolean isLeaf() {
        return !hasLChild() && !hasRChild();
    }

    //判断是否为某结点的左孩子
    public boolean isLChild() {
        return (hasParent() && this == parent.lChild);
    }

    //判断是否为某结点的右孩子
    public boolean isRChild() {
        return (hasParent() && this == parent.rChild);
    }

    /******与 height 相关的方法******/
    //取结点的高度,即以该结点为根的树的高度
    public int getHeight() {
        return height;
    }

    //更新当前结点及其祖先的高度
    public void updateHeight() {
        int newH = 0;//新高度初始化为 0,高度等于左右子树高度加 1 中的大者
        if (hasLChild()) newH = Math.max(newH, 1 + getLChild().getHeight());
        if (hasRChild()) newH = Math.max(newH, 1 + getRChild().getHeight());
        if (newH == height) return;  //高度没有发生变化则直接返回
        height = newH;  //否则更新高度
        if (hasParent()) getParent().updateHeight(); //递归更新祖先的高度
    }

    /******与 size 相关的方法******/
    //取以该结点为根的树的结点数
    public int getSize() {
        return size;
    }

    //更新当前结点及其祖先的子孙数
    public void updateSize() {
        size = 1;//初始化为1，节点本身
        if (hasLChild()) size += getLChild().getSize();  //加上左子树规模
        if (hasRChild()) size += getRChild().getSize();  //加上右子树规模
        if (hasParent()) getParent().updateSize();
    }


    /******与 parent 相关的方法******/
    //取父结点
    public BinTreeNode getParent() {
        return parent;
    }

    //断开与父节点的关系
    public void sever() {
        if (!hasParent()) return;
        //将父节点的对应子节点设置为null
        if (isLChild()) parent.lChild = null;
        else parent.rChild = null;
        //更新父节点的高度与大小
        parent.updateHeight();
        parent.updateSize();
        parent = null;
    }

    /******与 lChild 相关的方法******/
    //取左孩子
    public BinTreeNode getLChild() {
        return lChild;
    }

    //设置当前结点的左孩子,返回原左孩子
    public BinTreeNode setLChild(BinTreeNode lc) {
        BinTreeNode oldLC = this.lChild;
        if (hasLChild()) lChild.sever();//断开当前左孩子与节点的关系
        if (lc != null) {
            lc.sever();//断开lc与其父节点的关系
            this.lChild = lc;//确定父子关系
            lc.parent = this;
            //更新高度和规模
            this.updateSize();
            this.updateHeight();
        }
        return oldLC;
    }

    /******与 rChild 相关的方法******/
    //取右孩子
    public BinTreeNode getRChild() {
        return rChild;
    }

    //设置当前结点的右孩子,返回原右孩子
    public BinTreeNode setRChild(BinTreeNode rc) {
        BinTreeNode oldRC = this.rChild;
        if (hasRChild()) rChild.sever();
        if(rc!=null){
            rc.sever();
            this.rChild=rc;
            rc.parent=this;
            this.updateHeight();
            this.updateSize();
        }
        return oldRC;
    }

    //递归先序遍历
    public LinkedListDLNode preOrder(){
        LinkedListDLNode dlNode = new LinkedListDLNode();
        preOrderRecursion(this,dlNode);
        return  dlNode;
    }
    private void preOrderRecursion(BinTreeNode rt, LinkedList list){
        if(rt==null)return;
        list.insertLast(rt);//访问根节点
        preOrderRecursion(rt.getLChild(),list);//遍历左子树
        preOrderRecursion(rt.getRChild(),list);//遍历有子树
    }

    //非递归先序遍历
    public Iterator fdgpreOrder(){
        LinkedListDLNode list = new LinkedListDLNode();
        preOrderTraverse(this,list);
        return list.elements();
    }
    private void preOrderTraverse(BinTreeNode rt, LinkedList list){
        if(rt==null)return;
        BinTreeNode p=rt;
        StackSLinked s = new StackSLinked();
        while(p!=null){//这一层循环的目的是为了遍历右结点栈
            while(p!=null){//这一层整个循环都在访问根节点，然后找左节点
                list.insertLast(p);//访问根节点
                if(p.hasRChild()) s.push(p.getRChild());//右子结点入栈
                p=p.getLChild();//循环p

            }//到这里所有根和左几点已经完毕
            if(!s.isEmpty())p=(BinTreeNode)s.pop();//一次访问栈中的右结点
        }
    }
    //*******中序遍历
    public Iterator inOrder(){
        LinkedList list = new LinkedListDLNode();
        inOrderTraverse (this,list);
        return list.elements();
    }
    //中序遍历的非递归算法
    private void inOrderTraverse(BinTreeNode rt, LinkedList list){
        if(rt==null)return;
        BinTreeNode p=rt;
        StackSLinked s = new StackSLinked();
        while (p!=null||!s.isEmpty()){
            while(p!=null){//一直向左走
                s.push(p);
                p=p.getRChild();
            }
            if(!s.isEmpty()){
                p=(BinTreeNode)s.pop();//取出栈顶根节点访问
                list.insertLast(p);
                p=p.getRChild();//转向根的由子树进行遍历
            }
        }
    }

    //***********后序遍历**********
    public Iterator postOrder(){
        LinkedList list = new LinkedListDLNode();
        postOrderTraverse (this,list);
        return list.elements();
    }
    //后序遍历的非递归算法
    private void postOrderTraverse(BinTreeNode rt, LinkedList list) {
        if(rt==null)return;
        BinTreeNode p=rt;
        StackSLinked s = new StackSLinked();
        while(p!=null||!s.isEmpty()){
            while (p!=null){//先左后右不断深入
                s.push(p);//根节点入栈
                if(p.hasLChild())p=p.getLChild();
                else p=p.getRChild();
            }
            if(!s.isEmpty()){
                p=(BinTreeNode)s.pop();//取出栈顶节点并访问
                list.insertLast(p);
            }
            //满足条件时，说明栈顶根节点有子树已访问，应出栈并访问
            while (!s.isEmpty()&&((BinTreeNode)s.peek()).getRChild()==p){
                p=(BinTreeNode)s.pop();
                list.insertLast(p);
            }
            //转向栈顶根节点的右子树继续后续遍历
            if(!s.isEmpty())p=((BinTreeNode)s.peek()).getRChild();
            else p=null;
        }

    }
    //层次遍历，一层一层从左至右的遍历
        public Iterator levelOrder() {
            LinkedListDLNode list = new LinkedListDLNode();
            levelOrderTraverse(this, list);
            return list.elements();
        }
    //使用队列完成二叉树的按层遍历
    private void levelOrderTraverse(BinTreeNode rt, LinkedListDLNode list) {
        if(rt==null) return;
        Queue q=new QueueArray();
        q.enqueue(rt);//根节点入队

        while(!q.isEmpty()){
            BinTreeNode p = (BinTreeNode) q.dequeue();//取出多列首节点p进行访问
            list.insertLast(p);
            if(p.hasLChild())q.enqueue(p.getLChild());//将p的非空左右子节点入列
            if(p.hasRChild())q.enqueue(p.getRChild());
        }
    }
//在树中查找元素e，返回其所在结点
    public BinTreeNode find(Object e){
        return searchE(this,e);
    }
    //递归查找元素e
    private BinTreeNode searchE(BinTreeNode rt, Object e) {
        if(rt==null) return null;
        if(parent==null) return null;
        BinTreeNode v=searchE(rt.getRChild(),e);//在左子树中查找，
        if(v==null)v=searchE(rt.getRChild(),e);//在右子树中查找。
        return v;
    }


    @Override
    public String toString() {
        return "BinTreeNode{" +
                "data=" + data +
                ", height=" + height +
                ", size=" + size +
                '}';
    }
}
