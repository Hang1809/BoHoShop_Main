package vn.hangdinh.bohoshop.service;

import vn.hangdinh.bohoshop.model.OrderItem;

import java.util.List;

public interface IOrderItemService {

    List<OrderItem> getOrderItems();

    void add (OrderItem newOrderItem);

    void update();

    OrderItem getOrderItemById (int id);
}
