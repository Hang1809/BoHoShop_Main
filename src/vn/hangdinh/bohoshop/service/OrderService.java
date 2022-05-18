package vn.hangdinh.bohoshop.service;

import vn.hangdinh.bohoshop.model.Order;
import vn.hangdinh.bohoshop.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    List<Order> newOrders = new ArrayList<>();
    public static String path = "data/order.csv";

    @Override
    public List<Order> getOrders() {
        List<Order> newOrders = new ArrayList<>();
        List<String> records = CSVUtils.readData(path);
        for (String record : records) {
            newOrders.add(new Order(record));
        }
        return newOrders;
    }



    @Override
    public void add(Order newOrder) {
        newOrders = getOrders();
        newOrders.add(newOrder);
        CSVUtils.writeData(path,newOrders);

    }

    @Override
    public void update() {
        List<Order> orderList = getOrders();
        CSVUtils.writeData(path, orderList);
    }



    @Override
    public Order getOrderById(int id) {
        List<Order> newOrders = getOrders();
        for (Order order : newOrders) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public boolean exist(int id) {
        return getOrderById(id) != null;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        List<Order> newOrders = getOrders();
        for (Order order: newOrders){
            if (order.getName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateId(int id) {
        List <Order> newOrders = getOrders();
        for (Order order : newOrders){
            if (order.getId() == id)
                return true;
        }
        return false;
    }
}
