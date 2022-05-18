package vn.hangdinh.bohoshop.views;

import java.util.Scanner;

public class OrderViewLauncher {
    public static void run(){
        System.out.println("*------------ Order Product  ----------*");
        System.out.println("*       1.Create Order                 *");
        System.out.println("*       2.Order List                   *");
        System.out.println("*       3.Exit                         *");
        System.out.println("*--------------------------------------*");
        Scanner input = new Scanner(System.in);
        OrderView orderView = new OrderView();
        System.out.println("Input your option :");
        int option = input.nextInt();
        switch (option){
            case 1 :
                orderView.addOrder();
                break;
            case 2 :
                orderView.showOrderList();
                break;
            case 3 :
                MenuView.exit();
                break;
            default:
                System.out.println("Wrong input. Try again please!");
                run();
        }
    }
}
