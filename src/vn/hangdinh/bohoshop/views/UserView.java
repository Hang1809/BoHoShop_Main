package vn.hangdinh.bohoshop.views;

import vn.hangdinh.bohoshop.model.Role;
import vn.hangdinh.bohoshop.model.User;
import vn.hangdinh.bohoshop.service.UserService;
import vn.hangdinh.bohoshop.utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private static final UserService userService = new UserService();
    static Scanner input = new Scanner(System.in);


    public static void renderUser() {
        System.out.println("---------------------------------------------   USERS LIST   --------------------------------------------------------\n");
        System.out.printf("%-10s %-15s %-20s %-20s %-15s %-25s %-10s  \n", "ID", "User Name", "FullName", "Phone", "Address", "Email", "Role");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
//        List<User> users = userService.getUsers();
        for (User user : userService.getUsers()) {
            System.out.printf("%-10s %-15s %-20s %-20s %-15s %-25s %-10s \n", user.getId(), user.getUserName(),
                    user.getFullName(), user.getPhone(), user.getAddress(), user.getEmail(), user.getRole());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
    }

    public static void addUser() {
        do{
            try {
                Integer id = inputID(InputOption.ADD);
                String username = inputUsername();
                String password = inputPassword();
                String fullName = inputFullName(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail(InputOption.ADD);
                User user = new User(id,username,password,fullName,phone,address,email);
                setRole(user);
                userService.add(user);
                System.out.println("User added successfully.");

            }catch (Exception e){
                System.out.println("Wrong input. Please try again.");
            }

    }while (isRetry(InputOption.ADD));
    }

    public static void updateUser() {
        boolean isRetry = false;
        do{
            try {
                renderUser();
                Integer id = inputID(InputOption.UPDATE);
                System.out.println("-----------------------------");
                System.out.println("|   1. Edit Name            |");
                System.out.println("|   2. Edit phone number    |");
                System.out.println("|   3. Edit address         |");
                System.out.println("|   4. Edit email           |");
                System.out.println("|   5. Back to menu         |");
                System.out.println("-----------------------------");


                int option;
                do{
                    System.out.println("Input number to edit user");
                    option = Integer.parseInt(input.nextLine());
                    if (option>5 || option<1){
                        System.out.println("Wrong option. Try again.");
                        continue;
                    }
                    break;
                }while (true);
                User newUser = new User();
                newUser.setId(id);
                switch (option){
                    case 1:
                        String newName = inputFullName(InputOption.UPDATE);
                        newUser.setFullName(newName);
                        userService.update(newUser);
                        System.out.println("Successfully updated Full Name.");
                        break;
                    case 2 :
                        String phone = inputPhone(InputOption.UPDATE);
                        newUser.setPhone(phone);
                        userService.update(newUser);
                        System.out.println("Successfully updated phone number");
                        break;
                    case 3 :
                        String address = inputAddress(InputOption.UPDATE);
                        newUser.setAddress(address);
                        userService.update(newUser);
                        System.out.println("Successfully updated address");
                        break;
                    case 4 :
                        String email = inputEmail(InputOption.UPDATE);
                        newUser.setEmail(email);
                        userService.update(newUser);
                        System.out.println("Successfully updated email");
                        break;
                    case 5:
                        MenuView.menuAdmin();
                        break;
                }
                isRetry = option !=5 && isRetry(InputOption.UPDATE);

            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Wrong input. Please try again.");
            }

        }while(isRetry);

    }

    private static boolean isRetry(InputOption inputOption) {
        do {
            try {
                switch (inputOption) {
                    case ADD:
                        System.out.println("Input 'y' to continue add user\t|\t 'b' to back \t|\t 'e' to exit ");
                        break;
                    case UPDATE:
                        System.out.println("Input 'y' to continue edit user\t|\t 'b' to back \t|\t 'e' to exit ");
                        break;
                    default:
                        throw new IllegalAccessException("Unexpected value: " + inputOption);
                }
                System.out.println("->");
                String option = input.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "b":
                        return false;
                    case "e":
                        MenuView.exit();
                        break;
                    default:
                        System.out.println("Wrong function! Try again please.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Wrong input. Please try again.");
            }
        } while (true);
    }

    private static Integer inputID(InputOption option) {
        Integer id = null;
        switch (option) {
            case ADD:
                System.out.println("Input ID:");
                while(userService.existById(id = Integer.parseInt(input.nextLine()))){
                System.out.println("This ID is already being used.Try another one");
                System.out.println("");
                }
               break;
            case UPDATE:
                System.out.println("Input ID that you want to edit: ");
                while (!userService.existById(id = Integer.parseInt(input.nextLine()))){
                    System.out.println("This ID is not found. Please try again.");
                    System.out.println("");
                }
                break;
        }
        return id;
    }

    private static String inputPassword() {
        System.out.println("Input password (> 8 characters )");
        String password;
        while (!ValidateUtils.isPasswordValid(password = input.nextLine())) {
            System.out.println("The password is not strong. Try again. ");
            System.out.print("");
        }
        return password;
    }

    public static String inputUsername() {
        System.out.println("Input Username");
        System.out.print(" ");
        String username;
        while (userService.checkDuplicateUserName(username = input.nextLine())) {
            System.out.println("This UserName is already being used.Try another one");
        }
        return username;
    }

    private static String inputFullName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Input full name: (ex: Dinh Hang) ");
                break;
            case UPDATE:
                System.out.println("Input full name that you want to edit:");
                break;
        }
        String fullName;
        while (!ValidateUtils.isNameValid(fullName = input.nextLine())) {
            System.out.println( fullName + " is not correctly format for name." + " Try again." +
                    " (Capitalize the first letter of name please )");
            System.out.println("Input full name - ex: Hang Dinh ");
            System.out.print("");
        }
        return fullName;
    }

    public static String inputPhone(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Input phone number (ex : 0123456789) ");
                break;
            case UPDATE:
                System.out.println("Input phone number that you want to edit");
                break;
        }
        String phone;
        do {
            phone = input.nextLine();
            if (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println( phone + " is not a phone number!!! Try again " +
                        "('Phone number' must have 10 number & start with 0)");
                continue;
            }
            if (userService.checkDuplicatePhone(phone)) {
                System.out.println("This phone is already being used.Try another one");
                continue;
            }
            break;
        } while (true);
        return phone;
    }


    private static String inputAddress(InputOption option){
        switch (option){
            case ADD :
                System.out.println("Input address - ex: Ha Noi/Hue/Sai Gon");
                break;
            case UPDATE :
                System.out.println("Input address that you want to edit:");
                break;
        }
        String address = input.nextLine();
        return address;
    }

    private static String inputEmail(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Input Email (Ex: hang123@gmail.com) ");
                break;
            case UPDATE:
                System.out.println("Input email that you want to edit");
                break;
        }
        String email;
            do {
                email = input.nextLine().trim();
                    if (!ValidateUtils.isEmailValid(email)) {
                        System.out.println(email + " is not correctly format for email.Please try again. ");
                        System.out.println("Input Email (Ex: hang123@gmail.com)");
                        continue;
                    }
                    if (userService.checkDuplicateEmail(email)) {
                        System.out.println("Email " + email + " is already being used.Try another one");
                        System.out.println("Input Email (Ex: hang123@gmail.com)");
                        continue;
                    }
                    break;
                } while (true);
        return email;
        }

    public static void setRole(User user){
        System.out.println("--- SET ROLE ---");
        System.out.println("|   1. USER    |");
        System.out.println("|   2. ADMIN   |");
        System.out.println("----------------");
        System.out.println("Set Role: ");
        int option = input.nextInt();
        input.nextLine();
        switch (option) {
            case 1:
                user.setRole(Role.USER);
                break;
            case 2:
                user.setRole(Role.ADMIN);
                break;
            default:
                System.out.println("Wrong input! Try again.");
                setRole(user);
        }
    }
}