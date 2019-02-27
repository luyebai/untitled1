package anno;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.lang.annotation.Documented;
@Ignore
@TableName("book")
public class Goods {
    @PrimaryKey("gid")
    private Integer goods_id;
    @Column("gname")
    private String goods_name;
    @Column("price")
    private Double price;
    @Column("inventory")
    private String inventory;

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public Goods() {
    }

    public Goods(Integer goods_id, String goods_name, Double price, String inventory) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.price = price;
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goods_id=" + goods_id +
                ", goods_name='" + goods_name + '\'' +
                ", price=" + price +
                ", inventory='" + inventory + '\'' +
                '}';
    }
}
