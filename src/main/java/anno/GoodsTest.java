package anno;

public class GoodsTest {
    public static void main(String[] args) throws IllegalAccessException {
        Goods goods = new Goods(1, "三国", 300.00, "10");
        String sql = SQLUtils.insterSql(goods);
        System.out.println(sql);
    }
}
