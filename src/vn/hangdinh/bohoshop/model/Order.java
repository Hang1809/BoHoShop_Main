package vn.hangdinh.bohoshop.model;

import vn.hangdinh.bohoshop.utils.DateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private long id;
    private String name;
    private String phone;
    private String address;
    private Date createdDate;

    private List<OrderItem> oderItems = new ArrayList<>();

    private Order(){}

    public Order( String row){
        String [] oderInfo = row.split(",");
        id = Long.parseLong(oderInfo[0]);
        name = oderInfo[1];
        phone = oderInfo[2];
        address = oderInfo[3];
        createdDate = DateUtils.stringToDate(oderInfo[4]);
    }

    public Order(long id, String name, String phone, String address, Date createdDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.createdDate = createdDate;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return
                id +
                "," + name +
                "," + phone +
                "," + address +
                "," + DateUtils.dateToString(createdDate) ;

    }

    public static void transferFields(Order oldOrder, Order newOrder){
        oldOrder.id = newOrder.id;
        oldOrder.name = newOrder.name;
        oldOrder.phone = newOrder.phone;
        oldOrder.address = newOrder.address;
        oldOrder.createdDate = newOrder.createdDate;

    }
}
