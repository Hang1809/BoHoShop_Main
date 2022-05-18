package vn.hangdinh.bohoshop.views;

import vn.hangdinh.bohoshop.comparator.ComparatorNameASC;
import vn.hangdinh.bohoshop.comparator.ComparatorNameDESC;
import vn.hangdinh.bohoshop.comparator.ComparatorPriceASC;
import vn.hangdinh.bohoshop.comparator.ComparatorPriceDESC;
import vn.hangdinh.bohoshop.model.Product;
import vn.hangdinh.bohoshop.service.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class ProductView {
    private final ProductService productService = new ProductService();
    Scanner input = new Scanner(System.in);

    public void renderProduct() {
        System.out.println("---------------------------------- PRODUCT LIST---------------------------------- \n");
        System.out.printf("%-15s %-20s %-12s %-12s %-15s \n", "ID", "Product Name", "Price", "Quantity", "Color");
        System.out.println("---------------------------------------------------------------------------------");
        for (Product product : productService.getProducts()) {
            System.out.printf("%-15s %-20s %-12s %-12s %-15s \n", product.getId(), product.getTitle(),
                    product.getPrice(), product.getQuantity(), product.getColor());
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();
    }


    public void addNewProduct() {
        productService.getProducts();
        try{
        System.out.println("Input ID product.(ID must be a number)");
        int id = Integer.parseInt(input.nextLine());
        if (productService.checkDuplicateId(id)) {
            System.out.println("This ID is currently available.Try another one.");
            addNewProduct();
        } else {
            System.out.println("input name product: ");
            String title = input.nextLine();
            if (productService.checkDuplicateName(title)) {
                System.out.println(" This name is currently available.Try another one.");
                addNewProduct();
            } else {
//                System.out.println();
                System.out.println("input price: ");
                double price = Double.parseDouble(input.nextLine());
                System.out.println("input quantity: ");
                int quantity = Integer.parseInt(input.nextLine());
                System.out.println("input color: ");
                String color = input.nextLine();
                productService.add(new Product(id, title, price, quantity, color));
                System.out.println("Item added successfully!");

            }
        }
    }catch (Exception e){
            System.out.println("Wrong Input! Try again.");
            e.printStackTrace();
        }
    }

    public void updateProduct() {
        //   renderProduct();

        productService.getProducts();
        System.out.println("Search by ID: ");
        int id = Integer.parseInt(input.nextLine());
        Product product = productService.getByID(id);
        if (productService.checkDuplicateId(id)) {
            System.out.println("-------------------------");
            System.out.println("|   1. Edit name        |");
            System.out.println("|   2. Edit price       |");
            System.out.println("|   3. Edit quantity    |");
            System.out.println("|   4. Edit color       |");
            System.out.println("|   5. Back to menu     |");
            System.out.println("------------------------");
            System.out.println("Input number to edit product");
            int choice = input.nextInt();
            input.nextLine();
            try{
            switch (choice) {
                case 1:
                    System.out.println("input new name product: ");
                    String title = input.nextLine();
                    product.setTitle(title);
                    productService.update(product);
                    System.out.println("Successfully updated name");
                    break;
                case 2:
                    System.out.println("input new price: ");
                    double price = Double.parseDouble(input.nextLine());
                    product.setPrice(price);
                    productService.update(product);
                    System.out.println("Successfully updated price");
                    break;
                case 3:
                    System.out.println("input new quantity: ");
                    int quantity = Integer.parseInt(input.nextLine());
                    product.setQuantity(quantity);
                    productService.update(product);
                    System.out.println("Successfully updated quantity ");
                    break;
                case 4:
                    System.out.println("input new color: ");
                    String color = input.nextLine();
                    product.setColor(color);
                    productService.update(product);
                    System.out.println("Successfully updated color ");
                    break;
                case 5:
                    ManagerProductView.run();
                default:
                    System.out.println("Wrong choice ! Try again .");
                    updateProduct();
            }
        }catch (Exception e){
                System.out.println("Wrong input! Try again.");
                e.printStackTrace();
            }
        }
    }
    public void removeProduct() {
        //  renderProduct();
        productService.getProducts();
        System.out.println("Input Id Product that you want to remove.");
        int id = input.nextInt();
        Product product = productService.getByID(id);
        if ( product == null ){
            System.out.println("This ID not exist");
        } else {
            boolean check = true;
            System.out.println("------------REMOVE CONFIRM-----------");
            System.out.println("        1. Input 1 to remove ");
            System.out.println("        2. Back to Menu");
            int choice1 = input.nextInt();
            input.nextLine();
            try {
            switch (choice1){
                case 1:
                    productService.removeProduct(product);
                    System.out.println("Item removed successfully");
                    do {
                        System.out.println("Input 'y' to comeback menu Product | 'n' to exit ");
                        String choice2 = input.nextLine();
                        switch (choice2){
                            case "y":
                                ManagerProductView.run();
                                break;
                            case "n":
                                MenuView.exit();
                                break;
                            default:
                                System.out.println("Wrong input. Try again.");
                                check = false;
                        }
                    } while (!check);
                    break;
                case 2 :
                    ManagerProductView.run();
                    break;
                default:
                    System.out.println("Wrong input. Try again.");

            }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public Product searchProduct() {
        productService.getProducts();
        System.out.println("Find product by name: ");
        String title = input.nextLine().toLowerCase();

        for (Product product : productService.getProducts()) {
            if (product.getTitle().toLowerCase().contains(title)) {
               return product;
            }
        }
            return null;
        }

    private List<Product> productList = new ArrayList<>();
    public ProductView(){
        productList.add(new Product(1,"Cardigan",8,25,"blue"));
        productList.add(new Product(2,"Headscarves",3,30,"yellow"));
        productList.add(new Product(3,"Vintage scarf",3,50,"aquamarine"));
        productList.add(new Product(4,"Scarf Hiwaga",5,20,"orange"));
        productList.add(new Product(5,"Jumpsuit",9,30,"back & white"));
    }
    public void showProduct(){
        System.out.println("---------------------------------- PRODUCT LIST---------------------------------- \n");
        System.out.printf("%-15s %-20s %-12s %-12s %-15s \n", "ID", "Product Name", "Price", "Quantity", "Color");
        System.out.println("---------------------------------------------------------------------------------");
        for (Product product : productList) {
            System.out.printf("%-15s %-20s %-12s %-12s %-15s \n", product.getId(), product.getTitle(),
                    product.getPrice(), product.getQuantity(), product.getColor());
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();
    }
    public void displayProduct() {
        int choice;
        do {
            showProduct();
            System.out.println("1.Sort by Name(Ascending)");
            System.out.println("2.Sort by Name(Descending)");
            System.out.println("3.Sort by Price(Ascending)");
            System.out.println("4.Sort by Price(Descending)");
            System.out.println("5.Return");
            choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1:
                    productList.sort(new ComparatorNameASC());
                    break;
                case 2:
                    productList.sort(new ComparatorNameDESC());
                    break;
                case 3:
                    productList.sort(new ComparatorPriceASC());
                    break;
                case 4:
                    productList.sort(new ComparatorPriceDESC());
                    break;
            }
        } while (choice != 5);
    }
}





