package dataStutas.stack.migong;

public class Cell {
    int x = 0; //单元所在行
    int y = 0; //单元所在列
    boolean visited = false; //是否访问过
    char c = ' ';  //是墙('1')、可通路('0')或起点到终点的路径('*')
    public Cell(int x, int y, char c, boolean visited){
        this.x = x;  this.y = y;
        this.c = c;
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", visited=" + visited +
                ", c=" + c +
                '}';
    }
}
