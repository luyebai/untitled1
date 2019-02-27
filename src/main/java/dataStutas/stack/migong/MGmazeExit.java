package dataStutas.stack.migong;


import dataStutas.stack.Stack;
import dataStutas.stack.StackSLinked;

public class MGmazeExit {
    public static void main(String[] args) {
        //构建数组
        char[][] chars = {{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '1', '1', '0', '0', '1', '1'}, {'1', '0', '0', '1', '1', '0', '0', '1', '0', '1'},
                {'1', '0', '0', '0', '0', '0', '0', '1', '0', '1'}, {'1', '0', '0', '0', '0', '1', '1', '0', '0', '1'}, {'1', '0', '0', '1', '1', '1', '0', '0', '0', '1'}, {'1', '0', '0', '0', '0', '1', '0', '1', '0', '1'},
                {'1', '0', '1', '1', '0', '0', '0', '1', '0', '1'}, {'1', '1', '0', '0', '0', '0', '1', '0', '0', '1'}, {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}
        };
        //从8，8开始到1,7结束
        MGmazeExit exit = new MGmazeExit();
        exit.mazeExit(chars, 8, 8, 1, 7);
    }

    public void mazeExit(char[][] maze,int sx,int sy, int ex,int ey){
        Cell[][] cells = createMaze(maze); //创建化迷宫
        printMaze(cells);  //打印迷宫
        Stack s = new StackSLinked();  //构造堆栈
        Cell startCell = cells[sx][sy]; //起点
        Cell endCell = cells[ex][ey]; //终点
        s.push(startCell);  //起点入栈
        startCell.visited = true; //标记起点已被访问
        while (!s.isEmpty()){
            Cell current = (Cell)s.peek();
            if (current==endCell){ //如果是目标位置，路径找到
                while(!s.isEmpty()){
                    Cell cell = (Cell)s.pop();//沿路返回将路径上的单元设为*
                    cell.c = '*';//将堆栈中的所有元素的c设置为‘*’
                    //堆栈中与 cell 相邻的单元才是路径的组成部分，除此之外，
                    //堆栈中还有记录下来但是未继续向下探索的单元，
                    //这些单元直接出栈
                    while(!s.isEmpty()&&!isAdjoinCell((Cell)s.peek(),cell)) {
                        s.pop();
                    }
                }
                System.out.println("找到从起点到终点的路径。");
                printMaze(cells);
                return;
            } else { //如果当前位置不是终点
                int x = current.x;//当前坐标x
                int y = current.y;//当前坐标y
                int count = 0;//计数
                //如果下面的一个元素是没有访问过的，并且是0
                if(isValidWayCell(cells[x+1][y])){ //向上
                    //把这个节点入栈，设置访问过，然后计数加1
                    s.push(cells[x+1][y]); cells[x+1][y].visited = true; count++;}
                if(isValidWayCell(cells[x][y+1])){ //向左
                    s.push(cells[x][y+1]); cells[x][y+1].visited = true; count++;}
                if(isValidWayCell(cells[x-1][y])){ //向下
                    s.push(cells[x-1][y]); cells[x-1][y].visited = true; count++;}
                if(isValidWayCell(cells[x][y-1])){ //向右
                    s.push(cells[x][y-1]); cells[x][y-1].visited = true; count++;}
                if (count==0) s.pop();//如果是死点，出栈
                      /*1111111111找到终点时栈中的元素标为x，其实所有0它都入过栈，只是那些死胡同的点提前出栈了
                        100111xx11
                        10011xx101
                        1xxxxxx101
                        1xxxx11xx1
                        1xx111xxx1
                        1xxxx1x1x1
                        1x11xxx1x1
                        1100xx10x1
                        1111111111*/
            }//end of if
        }//end of while
        System.out.println("没有从起点到终点的路径。");
    }
    //打印迷宫
    private void printMaze(Cell[][] cells){
        for (int x=0;x<cells.length;x++){
            for (int y=0;y<cells[x].length;y++)
                System.out.print(cells[x][y].c);
            System.out.println();
        }
    }
    //判断是否是邻接点
    private boolean isAdjoinCell(Cell cell1, Cell cell2){
        //如果x坐标相等并且y坐标相减的绝对值小于2，说明是上下邻接点
        if (cell1.x==cell2.x&&Math.abs(cell1.y-cell2.y)<2) return true;
        //同理，说明是左右邻接点
        if (cell1.y==cell2.y&&Math.abs(cell1.x-cell2.x)<2) return true;
        return false;
    }
    //判断是否是合法路径
    private boolean isValidWayCell(Cell cell){
        return cell.c=='0'&&!cell.visited;
    }
    //将数组转换为迷宫
    private Cell[][] createMaze(char[][] maze){
        Cell[][] cells = new Cell[maze.length][];
        for (int x=0;x<maze.length;x++){
            char[] row = maze[x];
            cells[x] = new Cell[row.length];
            for (int y=0; y<row.length;y++)
                cells[x][y] = new Cell(x,y,maze[x][y],false);
        }
        return cells;
    }
}
