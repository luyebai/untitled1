package dataStutas.SearchTable;

import dataStutas.linkedtable.Iterator;
import dataStutas.linkedtable.Node;

/**
 *查找表接口定义
 */
public interface SearchTable {
    //查询查找表当前的规模
    public int getSize();
    //判断查找表是否为空
    public boolean isEmpty();
    //返回查找表与元素ele关键字相同的元素位置；否则返回null
    public Node search(Object ele);
    //返回所有关键字与ele相同的元素位置；
    public Iterator searchAll(Object ele);
    //按关键字插入元素ele
    public void insert(Object ele);
    //若查找表中存在与元素ele关键字相同元素，则删除一个并返回；否则返回null
    public Object remove(Object ele);

}
