package btree;

public class BTreePractice {
    public static void main(String[] args) {
        //素材准备
        TreeP<String> treeP = new TreeP<>();
        treeP.setData("根节点");
        TreeP root = treeP.createRoot();
        TreeP<String> treeP1 = new TreeP<>();
        treeP1.setData("2节点");
        TreeP<String> treeP2 = new TreeP<>();
        treeP2.setData("3节点");
        root.addChildnode(treeP1);
        root.addChildnode(treeP2);
        TreeP<String> treeP11 = new TreeP<>();
        treeP11.setData("4节点");
        treeP1.addChildnode(treeP11);
        TreeP<String> treeP12 = new TreeP<>();
        treeP12.setData("5节点");
        treeP1.addChildnode(treeP12);
        TreeP<String> treeP21 = new TreeP<>();
        treeP21.setData("6节点");
        treeP2.addChildnode(treeP21);
        TreeP<String> treeP22 = new TreeP<>();
        treeP22.setData("7节点");
        treeP2.addChildnode(treeP22);
        traverseData(root);
    }
    //遍历数据的方法
    public static void traverseData(TreeP root){
        System.out.println(root);//打印root
        if (root.getlChildNode() != null) {
            traverseData(root.getlChildNode());
        }
        if (root.getrChildNode() != null) {
            traverseData(root.getrChildNode());
        }
    }
    //遍历叶子节点的方法
    public static void traverse(TreeP root){
    if (root.getlChildNode()==null&&root.getrChildNode()==null){
        System.out.println(root);
        return;
    }else {
        if (root.getlChildNode() != null) {
            traverse(root.getlChildNode());
        }
        if (root.getrChildNode() != null) {
            traverse(root.getrChildNode());
        }
    }
    }
}




//实现树结构的类
class TreeP<T>{
    private T data;
    private TreeP parent;
    private TreeP lChildNode;
    private TreeP rChildNode;
    //创建一个根节点
    public TreeP createRoot(){
        this.parent=null;
        return this;
    }
    //
   public boolean addChildnode(TreeP Child){
        //将子节点的父节点设为调用方法的对象
        Child.setParent(this);
        //按照从左至右的方法来添加子节点
       if(lChildNode==null){
           lChildNode=Child;
           return true;
        }else if(rChildNode==null){
           rChildNode=Child;
           return true;
       }else {
           return false;
       }
    }
//选择性的setget和toString
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeP getParent() {
        return parent;
    }

    private void setParent(TreeP parent) {
        this.parent = parent;
    }

    public TreeP getlChildNode() {
        return lChildNode;
    }

    public TreeP getrChildNode() {
        return rChildNode;
    }

    @Override
    public String toString() {
        return "TreeP{" +
                "data=" + data +
                ", parent=" + parent +
                '}';
    }
}
