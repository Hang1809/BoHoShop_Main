package vn.hangdinh.bohoshop.views;

import vn.hangdinh.bohoshop.model.Order;
import vn.hangdinh.bohoshop.model.OrderItem;
import vn.hangdinh.bohoshop.model.Product;
import vn.hangdinh.bohoshop.model.User;
import vn.hangdinh.bohoshop.service.*;
import vn.hangdinh.bohoshop.utils.DateUtils;
import vn.hangdinh.bohoshop.utils.ValidateUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderView {

    private IProductService productService;
    private IOrderService orderService;
    private IOrderItemService orderItemService;
    private final Scanner input = new Scanner(System.in);

    public OrderView() {
        productService = new ProductService();
        orderService = new OrderService();
        orderItemService = new OrderItemService();
    }

    private Integer inputIDProduct() {
        System.out.println("Input ID_Product:");
        Integer productID = Integer.parseInt(input.nextLine());
//        Product product = productService.getByID(productID);
        while (!productService.checkDuplicateId(productID)) {
            System.out.println("This ID_Product is not exist.");
            System.out.println(" Input ID_Product:");
            productID = input.nextInt();
        }
        return productID;
    }



    public Integer inputQuantity(Product product) {
        System.out.println("Input Quantity:");
        Integer quantity = Integer.parseInt(input.nextLine());
        while (!checkQuantity(product, quantity)) {
            System.out.println("The number is over ");
            System.out.println("Try again. Input Quantity.");
            quantity = input.nextInt();
        }
        return quantity;
    }


    public boolean checkQuantity(Product product, int quantity) {
        return quantity <= product.getQuantity();
    }

    public String inputNameCustomer() {
        System.out.println("Input your name: ");
        String name = input.nextLine();
        while (!ValidateUtils.isNameValid(name)) {
            System.out.println("This name " + name + " is not right. Try again." +
                    "(Capitalize the first letter please.)");
            name = input.nextLine();
        }
        return name;
    }



    public String inputAddress() {
        System.out.println("Input your address: ");
        String address = input.nextLine();
        return address;
    }



    public String inputPhone() {
        System.out.println("Input phone number:");
        String phone = input.nextLine();
        while (!ValidateUtils.isPhoneValid(phone)) {
            System.out.println("This is not a phone number. Try again.");
            System.out.println("Input phone number - ex:0912345675");
            phone = input.nextLine();
        }
        return phone;
    }

    public Date inputDate() {
        Date date = new Date();
        do {
            try {
                System.out.println("Input created date: ex:08-05-2022");
                String createdDate = input.nextLine();
                date = DateUtils.stringToDate(createdDate);
                LocalDate localDate = LocalDate.now();
                Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                if (date.after(today)) {
                    System.out.println("This day is not coming. Please try again.");
                    continue;
                }
                break;
        } catch (Exception ex) {
            System.out.println("This is not follow format Date.Try again.");
        }
        } while (true);
        return date;
    }


    public OrderItem addOrderItems(long orderId) {
        orderItemService.getOrderItems();
        ProductView productView = new ProductView();
        productView.renderProduct();
        long id = System.currentTimeMillis() / 1000;
        Integer productID = inputIDProduct();
        Product product = productService.getByID(productID);
        int quantity = inputQuantity(product);
        String productName = product.getTitle();
        int oldQuantity = product.getQuantity();
        int currentQuantity = oldQuantity - quantity;
        double price = product.getPrice();
        double total = quantity * price;
        product.setQuantity(currentQuantity);
        productService.update(product);
        OrderItem orderItem = new OrderItem(id, price, quantity, orderId, productID, productName, total);
        return orderItem;
    }

    public void addOrder() {
        try {
            orderService.getOrders();
            long orderId = System.currentTimeMillis() / 1000;
            String name = inputNameCustomer();
            String phone = inputPhone();
            String address = inputAddress();
            Date date = inputDate();
            OrderItem orderItem = addOrderItems(orderId);
            Order order = new Order(orderId, name, phone, address, date);
            orderItemService.add(orderItem);
            orderService.add(order);
            System.out.println("Successfully created the order !!!");
            do {
                System.out.println("--------------------------------------------------");
                System.out.println("|   1.Input 'y' to continue created order.       |");
                System.out.println("|   2.Input 'b' to back menu order.              |");
                System.out.println("|   3.Input 'p' to print the invoice.            |");
                System.out.println("|   4.Input 'e' to exit.                         |");
                System.out.println("--------------------------------------------------");
                String option = input.nextLine();
                switch (option) {
                    case "y":
                        addOrder();
                        break;
                    case "b":
                        OrderViewLauncher.run();
                        break;
                    case "p":
                        showPaymentInfo(orderItem, order);
                        break;
                    case "t":
                        MenuView.exit();
                        break;
                    default:
                        System.out.println("Wrong input. Try again please!");

                }
            } while (true);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Wrong input. Try again please!");
        }
    }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("------------------------------------------------------ INVOICE ----------------------------------------------------------");
            System.out.printf("%-15s %-20s %-15s %-15s %-15s %-15s %-15s\n|", "ID", "Customer Name", "Phone", "Address", "Product Name", "Quantity", "Price");
            System.out.printf("%-15d %-20s %-15s %-15s %-15s %-15d %-15f \n|", order.getId(), order.getName(), order.getPhone(),
                    order.getAddress(), orderItem.getProductName(), orderItem.getQuantity(), orderItem.getPrice());
            System.out.println("");
            System.out.println("| ******************************************************************       Total Price:" + orderItem.getTotal());
            System.out.println("| ******************************************************************       Created Date:" + order.getCreatedDate());
            System.out.println("|------------------------------------------------------------------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Input 'b' to comeback \t|\t 'e' to exit");
                String choice = input.nextLine();
                switch (choice) {
                    case "b":
                        OrderViewLauncher.run();
                        break;
                    case "e":
                        MenuView.exit();
                        break;
                    default:
                        System.out.println("Wrong Input. Try again please!");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    public void showOrderList() {
        List<Order> orders = orderService.getOrders();
        List<OrderItem> orderItems = orderItemService.getOrderItems();
        OrderItem newOrderItem = new OrderItem();
        double totalList = 0;


        try {
            System.out.println("---------------------------------------------------------  ORDER LIST  ------------------------------------------------------------------");

            System.out.printf("%-13s %-15s %-15s %-13s %-15s %-10s %-10s %-30s \n", "Id", "CustomerName", "Phone", "Address","ProductName","Quantity","Total", "Created Date");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        System.out.printf("%-13s %-15s %-15s %-13s %-15s %-10s %-10s %-30s \n",
                                order.getId(), order.getName(), order.getPhone(), order.getAddress(),
                                newOrderItem.getProductName(),newOrderItem.getQuantity(),newOrderItem.getTotal(), order.getCreatedDate());
                        break;
                    }
                }

                totalList = newOrderItem.getTotal() + totalList;
            }
            System.out.println("");
            System.out.println("-----------------**********-------------------*********------------------*********       " + "Total :" + totalList + "$");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Wrong input. Try again.");
        }
    }
}
