package dataStutas.graph;

import dataStutas.linkedtable.Iterator;
import dataStutas.linkedtable.LinkedList;
import dataStutas.linkedtable.LinkedListDLNode;
import dataStutas.linkedtable.Node;

/**
 * 双链式存储结构的顶点定义
 */
public class Vertex {
    private Object info;//顶点信息
    private LinkedList adjacentEdges; //顶点的邻接表（含有与当前顶点相关联的边，有向图时含有出度边）
    private LinkedList reAdjacentEdges;//顶点的逆邻接边表，无向图时为空（含有入度边）
    private boolean  visited; //visited 表示顶点的访问状态，在图的遍历、求最短路径等操作中使用；
    private Node vexPosition; //顶点在顶点表中的位置
    private int graphType; //graphType 用来表示顶点所在图的类型，在有向图和无向图中顶点的操作实现有一些差别
    private Object application; //应用application 也是在求最短路径等操作的实现中使用，如求最短路径时为Path，求关键路径时是Vtime
    //构造方法：在图G中引入一个新顶点
    public Vertex(Graph g,Object info){
        this.info=info;
        adjacentEdges=new LinkedListDLNode();
        reAdjacentEdges=new LinkedListDLNode();
        visited=false;
        graphType=g.getType();
        vexPosition=g.insert(this);
        application=null;
    }
    //辅助方法：判断顶点所在图的类型(有向图或无向图)
    private boolean isUnDiGraphNode(){return graphType==Graph.UNDIRECTEDGRAPH;}
    //获取或设置顶点信息
    public Object getInfo(){return info;}
    public void setInfo(Object info) {
        this.info = info;
    }
    //与顶点的度相关的方法
    //获取度大小
    public int getDeg(){
        if(isUnDiGraphNode())
            return adjacentEdges.getSize();//无向图顶点的（出、入）度为邻接表规模相同
        else
            return getOutDeg()+getInDeg();//有向图顶点的度为出度与入度之和。
    }
    //出度和入度是对有向图而言的
    //获取出度
    //有向图的邻接表的规模代表了出度大小，逆邻接表的规模代表了入度大小
    public int getOutDeg(){
        return adjacentEdges.getSize();//有向图的出度为邻接表规模
    }
    //获取入度
    public int getInDeg(){
            return reAdjacentEdges.getSize();//有向图顶点入度为逆邻接表的规模
    }

    //获取邻接表
    public LinkedList getAdjacentEdges(){return adjacentEdges;}
    //获取逆邻接表
    public LinkedList getReAdjacentEdges(){
        if (isUnDiGraphNode())
            return adjacentEdges;//无向图时返回它的邻接表
        else
            return reAdjacentEdges;
    }
    //取顶点在所属图顶点集’中的位置
    public Node getVexPosition(){return vexPosition;}
    //与顶点访问状态相关的方法
    //获取访问状态
    public boolean isVisited(){return visited;}
    //设置访问状态
    public void setToVisited(){visited=true;}
    public void setToUnvisited(){visited=false;}
    //取或设置顶点应用信息
    protected Object getApplication(){return application;}
    protected void setAppObj(Object app){application=app;}
    //重置顶点状态信息
    public void resetStatus(){
        visited=false;//访问状态
        application=null;
    }

}
