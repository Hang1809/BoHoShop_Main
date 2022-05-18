package vn.hangdinh.bohoshop.comparator;

import vn.hangdinh.bohoshop.model.Product;

import java.util.Comparator;

public class ComparatorNameDESC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getTitle().compareTo(o1.getTitle());
    }
}
