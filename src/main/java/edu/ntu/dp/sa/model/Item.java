package edu.ntu.dp.sa.model;
import java.util.Objects;

public class Item {

    private String id;
    private String name;
    private float price;
    private String currency;

    public Item(String id, String name, float price, String currency) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public float getPrice() {return price;}
    public void setPrice(float price) {this.price = price;}

    public String getCurrency() {return currency;}
    public void setCurrency(String currency) {this.currency = currency;}


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return Float.compare(item.price, price) == 0 && Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(currency, item.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, currency);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }

}
