package vn.hangdinh.bohoshop.views;

import threading_in_out.ThreadLogin;
import vn.hangdinh.bohoshop.model.Role;
import vn.hangdinh.bohoshop.model.User;
import vn.hangdinh.bohoshop.service.IUserService;
import vn.hangdinh.bohoshop.service.UserService;

import java.util.Scanner;


public class UserManagerView {
    static Scanner input = new Scanner(System.in);
    private static int userID;
    private static IUserService logInService = new UserService();

    public UserManagerView() {
    }

    public static int getUserID() {
        return userID;
    }


    public static void signIn() {
        ThreadLogin threadLogin = new ThreadLogin();
        Thread thread = new Thread(threadLogin);
        thread.start();
        try{
            thread.join();
        }catch (Exception e){
            System.out.println("");
        }

        Scanner input = new Scanner(System.in);
        boolean showAnotherChoice = false;
        System.out.println();
        System.out.println();
        System.out.println("*************    LOGIN    *************");
        System.out.println();
        do{
        System.out.print("1. Username: ");
        String username = input.next();
        System.out.print("2. Password: ");
        String password = input.next();
        User user = logInService.loginAdmin(username,password);
        if (user == null) {
            System.out.println("\nWrong username or password! Try again !!! ");
            showAnotherChoice();
        } else if (user.getRole() == Role.ADMIN) {
            System.out.println("Welcome Admin. You are in !!! ");
            userID = user.getId();
            System.out.println("");
            MenuView.menuAdmin();
        } else if (user.getRole() == Role.USER){
            System.out.println("Welcome User. You are successful sign-in.");
            userID = user.getId();
            System.out.println("");
            MenuView.menuUser();

        }
    }while (showAnotherChoice);
    }

    public static boolean showAnotherChoice() {
        do{
            try {
                System.out.println("    1.Type 'Yes': Sign up again ");
                System.out.println("    2.Type 'No' : Exit ");
                String choice = input.nextLine();
                switch (choice) {
                    case "Yes":
                        return true;
                    case "No":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong inpput. Try again. ");
                        break;
                }
            }catch (Exception e){
                System.out.println("Wrong input.Please try again.");
            }
    }while (true);
    }

    public static void showManagerUser(){

        System.out.println("------------  Manager User  -----------");
        System.out.println("|       1.Add User                    |");
        System.out.println("|       2.Edit User Info              |");
        System.out.println("|       3.User List                   |");
        System.out.println("|       4.Exit                        |");
        System.out.println("---------------------------------------");
        System.out.println();

    }
    public static void run() {
        do{
        Scanner input = new Scanner(System.in);
        showManagerUser();
        try {
            System.out.println("Enter your choice : ");
            int number = Integer.parseInt(input.nextLine());
            switch (number) {
                case 1:
                    System.out.println("Add User");
                    UserView.addUser();
                    break;
                case 2:
                    System.out.println("Update User");
                    UserView.updateUser();
                    break;
                case 3:
                    System.out.println("Show Users List");
                    UserView.renderUser();
                    break;
                case 4:
                    MenuView.exit();
                    break;
                default:
                    System.out.println("Wrong choice ! Try again !!!");
                    run();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Wrong input ! Try again !!!");
            run();
        }
    }while (true);
    }
}
