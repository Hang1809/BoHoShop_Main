package vn.hangdinh.bohoshop.views;

import java.util.Scanner;

public class ManagerProductView {
    static Scanner input = new Scanner(System.in);
    static ProductView productView = new ProductView();

    public static void showMenu() {
        System.out.println("----------------- MENU PRODUCT ------------------");
        System.out.println("|   1. Add Product                              |");
        System.out.println("|   2. Edit Product by using ID                 |");
        System.out.println("|   3. Remove Product by using ID               |");
        System.out.println("|   4. Show Product List                        |");
        System.out.println("|   5. Find Product by using name               |");
        System.out.println("|   6. Display product                          |");
        System.out.println("|   0. Exit                                     |");
        System.out.println("------------------------------------------------");
        System.out.println(" ");
    }

    public static void run() {
        while (true) {
            try {
                showMenu();
                System.out.println("Enter your choice: ");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Add Product");
                        productView.addNewProduct();
                        break;
                    case 2:
                        System.out.println("Edit Product by using ID");
                        productView.updateProduct();
                        break;
                    case 3:
                        System.out.println("Remove Product by using ID");
                        productView.removeProduct();
                        break;
                    case 4:
                        System.out.println("Show Product List");
                        productView.renderProduct();
                        break;
                    case 5:
                        System.out.println("Find Product by using name");
                        System.out.println(productView.searchProduct());
                        ;
                        break;
                    case 6:
                        System.out.println("Display product");
                        productView.displayProduct();
                        break;
                    case 0:
                        MenuView.exit();
                        break;
                    default:
                        System.err.println("Wrong input. Try again.");
                }
            } catch (Exception e) {
                System.err.println("Wrong input. Try again.");
            }
        }
    }
}

