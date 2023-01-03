package com.example.project.bean;

import com.example.project.inter.Customizable;

import java.util.ArrayList;
/**
 * store parameter
 * create or set the information of store orders
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class StoreOrders implements Customizable {

    private ArrayList<Order> orders;
    private Integer cnt;

    /**
     * create constructor
     */
    public StoreOrders() {
        orders = new ArrayList<>();
        cnt = 0;
    }

    /**
     * get the order
     * @return order
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * set the order
     * @param orders set order
     */
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    /**
     * get the number
     * @return number
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     * set the number
     * @param cnt
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    /**
     * add order
     * @param obj Order
     * @return true if add success, false else
     */
    @Override
    public boolean add(Object obj) {
        try {
            Order order = (Order) obj;
            orders.add(order);
            cnt = orders.size();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * remove order
     * @param obj
     * @return true if remove success, false else
     */
    @Override
    public boolean remove(Object obj) {
        try {
            int idx = (int) obj;
            orders.remove(idx);
            cnt = orders.size();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
