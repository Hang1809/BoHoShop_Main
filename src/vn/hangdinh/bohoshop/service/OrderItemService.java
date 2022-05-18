package vn.hangdinh.bohoshop.service;

import vn.hangdinh.bohoshop.model.OrderItem;
import vn.hangdinh.bohoshop.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOrderItemService {
    List <OrderItem> orderItems = new ArrayList<>();
    public String path = "data/orderItem.csv";
    @Override
    public List<OrderItem> getOrderItems() {
        List <OrderItem> newOrderItems = new ArrayList<>();
        List <String> records = CSVUtils.readData(path);
        for (String record : records){
            newOrderItems.add(new OrderItem(record));
        }
        return newOrderItems;

    }


    @Override
    public void add(OrderItem newOrderItem) {
        orderItems = getOrderItems();
        orderItems.add(newOrderItem);
        CSVUtils.writeData(path,orderItems);
    }



    @Override
    public void update() {
        CSVUtils.writeData(path,orderItems);
    }


    @Override
    public OrderItem getOrderItemById(int id) {
        List <OrderItem> orderItems = getOrderItems();
        for (OrderItem orderItem : orderItems){
            if(orderItem.getId() == id)
                return orderItem ;
        }
        return null;
    }
}
