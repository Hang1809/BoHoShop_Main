package vn.hangdinh.bohoshop.model;

import java.util.Objects;

public class Product {
    private int id;
    private String title;
    private double price;
    private int quantity;
    private String color;


    public Product(int id, String title, double price, int quantity, String color) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
    }
    public Product (int quantity){
        this.quantity= quantity;
    }
    public Product(String title, double price, int quantity, String color) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
    }

    public Product(String row) {
        String[] productInformation = row.split(",");
        this.id = Integer.parseInt(productInformation[0]);
        this.title = productInformation[1];
        this.price = Double.parseDouble(productInformation[2]);
        this.quantity = Integer.parseInt(productInformation[3]);
        this.color = productInformation[4];
    }

    public Product(Integer quantity) {
        this.quantity= quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return id+"," +title+"," +price+"," +quantity+"," +color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && quantity == product.quantity && Objects.equals(title, product.title) && Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, quantity, color);
    }

}


