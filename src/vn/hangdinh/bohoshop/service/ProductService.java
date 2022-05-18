package vn.hangdinh.bohoshop.service;

import vn.hangdinh.bohoshop.model.Product;
import vn.hangdinh.bohoshop.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    public final static String path = "data/product.csv";

    public ProductService() {
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.readData(path);
        for (String record : records) {
            if (!record.trim().isEmpty())
                products.add(new Product(record));
        }
        return products;
    }

    @Override
    public void add(Product newProduct) {
        List<Product> products = getProducts();
        products.add(newProduct);
        CSVUtils.writeData(path, products);

    }

    @Override
    public Product getByID(int id) {
        List<Product> products = getProducts();
        for (Product product : products) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    @Override
    public boolean existById(int id) {
        return getByID(id) != null;
    }

    @Override
    public void update(Product newProduct) {
        List<Product> products = getProducts();
        for (Product product : products) {
            if (product.getId() == newProduct.getId()) {
                if (newProduct.getTitle() != null && !newProduct.getTitle().isEmpty()) {
                    product.setTitle(newProduct.getTitle());
                }

                if (product.getPrice() != newProduct.getPrice()) {
                    product.setPrice(newProduct.getPrice());
                }

                if (product.getQuantity() != newProduct.getQuantity()) {
                    product.setQuantity(newProduct.getQuantity());
                }

                if (product.getColor() != newProduct.getColor()) {
                    product.setColor(newProduct.getColor());
                }
                CSVUtils.writeData(path, products);
                break;
            }
        }
    }

    @Override
    public boolean checkDuplicateName(String title) {
        List<Product> products = getProducts();
        for (Product product : products) {
            if (product.getTitle().equals(title))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateId(int id) {
        List<Product> products = getProducts();
        for (Product product : products) {
            if (product.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public void removeProduct(Product product) {
        List<Product> products = this.getProducts();
        products.remove(product);
        CSVUtils.writeData(path,products);
    }
}
