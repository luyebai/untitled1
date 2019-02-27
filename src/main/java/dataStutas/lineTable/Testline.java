package dataStutas.lineTable;


public class Testline {
    public static void main(String[] args) {
        LineTable table = new LineTable();
        table.insert(0,15);
        Object[] arr={1,2,3,4,5,6,78,9,78,5,64,2,31,256,48,52,13,21};
        table.insertAll(arr);
       table.remove(6);
        System.out.println(table.toString());
        System.out.println(table.getSize());

    }
}
