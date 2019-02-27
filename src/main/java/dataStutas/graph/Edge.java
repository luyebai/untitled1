package dataStutas.graph;

import dataStutas.linkedtable.Node;

/**
 * 图结构双链式存储结构中的边类实现
 */
public class Edge {
    public static final int NORMAL=0;
    public static final int MST=1; //MST边
    private static final int CRITICAL=2; //关键路径中的边
    private int weight; //边的权值
    private Object info; //边的信息
    private Node edgePosition; //边在边表中的位置
    private Node firstVexPosition; //边的第一顶点与第二顶点
    private Node secondVexPosition; //在顶点表中的位置
    private Node edgeFirstPosition; //边在第一（二）顶点的邻接（逆邻接）边表中的位置
    private Node edgeSecondPosition; //在无向图中就是在两个顶点的邻接边表中的位置
    private int type; //边的类型
    private int graphType; //所在图的类型（有向或无向）
    //构造方法：在图G中引入一条新边，其顶点为u、v
    public Edge(Graph g,Vertex u,Vertex v,Object info){
        this(g,u,v,info,1);
    }
    public Edge(Graph g,Vertex u,Vertex v,Object info,int weight){
        this.info=info;
        this.weight=weight;
        edgePosition=g.insert(this);//把当前对象添加到边表中
        firstVexPosition=u.getVexPosition();
        secondVexPosition=v.getVexPosition();
        type=Edge.NORMAL;
        graphType=g.getType();
        if(graphType==Graph.UNDIRECTEDGRAPH){
            //如果是无向图，边应当加入其两个顶点的邻接边表
            edgeFirstPosition=u.getAdjacentEdges().insertLast(this);
            edgeSecondPosition=v.getAdjacentEdges().insertLast(this);
        }else{
            //如果是有向图，边加入起始点的邻接边表，终止点的逆邻接边表
            edgeFirstPosition=u.getAdjacentEdges().insertLast(this);
            edgeSecondPosition=v.getAdjacentEdges().insertLast(this);
        }
    }
    //get&set  方法
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
    public Vertex getFirstVex(){return (Vertex) firstVexPosition.getDate();}
    public Vertex getSecondVex(){return (Vertex) secondVexPosition.getDate();}

    public Node getEdgePosition() {
        return edgePosition;
    }

    public Node getFirstVexPosition() {
        return firstVexPosition;
    }

    public Node getSecondVexPosition() {
        return secondVexPosition;
    }

    public Node getEdgeFirstPosition() {
        return edgeFirstPosition;
    }

    public Node getEdgeSecondPosition() {
        return edgeSecondPosition;
    }
    //与边类型相关的方法
    public void setToMST(){type=Edge.MST;}
    public void setToCritical(){type=Edge.CRITICAL;}
    public void resetType(){type=Edge.NORMAL;}
    public boolean isMSTEdge(){return type==Edge.MST;}
    public boolean isCritical(){return type==Edge.CRITICAL;}
}
