package mynode;

public class NodeUtils {
    //定义一个深度初始值
    private  Integer depth=0;
    //对传入的节点求深度,一个递归计算
    public  Integer getDepth(MyNode node){
        if(node.getParent()==null){
            return depth;
        }else{
            depth++;
            return getDepth(node.getParent());
        }
    }

}
