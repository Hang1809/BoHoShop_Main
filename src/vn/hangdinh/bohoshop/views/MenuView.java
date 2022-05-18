package vn.hangdinh.bohoshop.views;

import threading_in_out.ThreadExit;

import java.util.Scanner;

import static vn.hangdinh.bohoshop.views.UserView.*;

public class MenuView {
    public static void main(String[] args) {
//        showMainMenu();
        //showSelection();
    }
    public static void exit() {
        ThreadExit threadExit = new ThreadExit();
        Thread thread1 = new Thread(threadExit);
        thread1.start();
        try{
            thread1.join();
        }catch(Exception e){
            e.printStackTrace();
        }

//        System.out.println("End Program");
//        System.out.println("Goodbye !!! Have a nice day !!! ");
//        System.exit(0);
    }
    public static void showMainMenu() {
        System.out.println("*-----------------Menu-----------------*");
        System.out.println("    1.Menu Admin");
        System.out.println("    2.Menu User");
        System.out.println("    3.Menu Product");
        System.out.println("    4.Order Product");
        System.out.println("    0. Exit");
        System.out.println("*--------------------------------------*");
        System.out.println("Enter your choice: ");
        //      showSelection();

    }

    public static void menuAdmin() {

        System.out.println("*------------  Menu Admin  ------------*");
        System.out.println("*       1.User Management              *");
        System.out.println("*       2.Product Management           *");
        System.out.println("*       3.Order Management             *");
        System.out.println("*       4.Exit                         *");
        System.out.println("*--------------------------------------*");
        System.out.println();
        System.out.println("Enter your choice: ");
        System.out.println(" *** ");
        Scanner input = new Scanner(System.in);
        int choice1 = -1;
        while (choice1 != 0) {
            choice1 = input.nextInt();
            input.nextLine();
            switch (choice1) {
                case 1:
                    System.out.println("User Management");
                    UserManagerView.run();
                    break;
                case 2:
                    System.out.println("Product Management");
                    ManagerProductView.run();
                    break;
                case 3:
                    System.out.println("Order Management");
                    OrderViewLauncher.run();
                    break;
                case 4:
                    MenuView.exit();
                    break;
                default:
                    System.out.println("Wrong Input! Please enter a number !!! ");
                    menuAdmin();
            }
        }
    }

    public static void menuUser() {
        //User Management

        System.out.println("*-------------  Menu User  ------------*");
        System.out.println("*       1.Create Order                 *");
        System.out.println("*       2.Exit                         *");
        System.out.println("*--------------------------------------*");
        System.out.println();

        System.out.println("Enter your choice: ");
        System.out.println(" *** ");
        Scanner input = new Scanner(System.in);
        int choice2 = -1;
        while (choice2 != 0) {
            choice2 = input.nextInt();
            input.nextLine();
            switch (choice2) {
                case 1:
                    System.out.println("Oder Product");
                    OrderViewLauncher.run();
                    break;
                case 2:
                    MenuView.exit();
                    break;
                default:
                    System.out.println("Wrong Input! Please enter a number !!! ");
                    menuUser();

            }
        }
    }


    public static void menuProduct() {
        //Product Management (Product List + OrderProduct)
        System.out.println(" Product Management");
        System.out.println();


        System.out.println("*------------  Menu Product  ----------*");
        System.out.println("*       1.Add Product                  *");
        System.out.println("*       2.Edit Product                 *");
        System.out.println("*       3.Remove Product               *");
        System.out.println("*       4.Product List                 *");
        System.out.println("*       5.Exit                         *");
        System.out.println("*--------------------------------------*");

        System.out.println("Enter your choice: ");
        System.out.println(" *** ");
        Scanner input = new Scanner(System.in);
        int choice3 = -1;
        while (choice3 != 0) {
            choice3 = input.nextInt();
            input.nextLine();
            switch (choice3) {
                case 1:
                    System.out.println("Add Product");

                    break;
                case 2:
                    System.out.println("Edit Product");
                    break;
                case 3:
                    System.out.println("Remove Product");
                    break;
                case 4:
                    System.out.println("Product List");
                    break;
                case 5:
                    MenuView.exit();
                    break;
                default:
                    System.out.println("Wrong Input! Please enter a number !!! ");
                    menuProduct();

            }
        }
    }

    public static void orderProduct() {
        System.out.println("*--------------------------------------*");
        System.out.println("*------------ Order Product  ----------*");
        System.out.println("*       1.Create Order                 *");
        System.out.println("*       2.Order List                   *");
        System.out.println("*       3.Exit                         *");
        System.out.println("*--------------------------------------*");

        System.out.println("Enter your choice: ");
        System.out.println(" *** ");
        Scanner input = new Scanner(System.in);
        int choice4 = -1;
        while (choice4 != 0) {
            choice4 = input.nextInt();
            input.nextLine();
            switch (choice4) {
                case 1:
                    System.out.println("Add Order");
                    break;
                case 2:
                    System.out.println("Order List");
                    break;
                case 3:
                    MenuView.exit();
                default:
                    System.out.println("Wrong Input! Please enter a number !!! ");
                    orderProduct();

            }
        }
    }


    public static void showSelection() {
        int choice = -1;
        while (choice != 0) {
            showMainMenu();
            Scanner scanner = new Scanner(System.in);
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        menuAdmin();
                        break;
                    case 2:
                        menuUser();
                        break;
                    case 3:
                        menuProduct();
                        break;
                    case 4:
                        orderProduct();
                        break;
                    case 0:
                        MenuView.exit();
                        break;
                    default:
                        System.out.println("No choice! Please enter a number from 1 to 4!!!");
                }
            } catch (Exception e) {
                System.out.println(" Wrong input!!! Please enter a number !!!");
                //   showMainMenu();
            }
        }
    }
}



