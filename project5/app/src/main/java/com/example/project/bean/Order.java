package com.example.project.bean;

import com.example.project.inter.Customizable;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * order parameter
 * create or set the information of order
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class Order implements Customizable {

    private ArrayList<MenuItem> lists;
    private int cnt;
    private double totalPrice;
    private int orderNum;
    /**
     * create constructor
     */
    public Order() {
        lists = new ArrayList<>();
        cnt = 0;
        totalPrice = 0;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * get the list
     * @return list
     */
    public ArrayList<MenuItem> getLists() {
        return lists;
    }

    /**
     * set the list of item
     * @param lists
     */
    public void setLists(ArrayList<MenuItem> lists) {
        this.lists = lists;
    }

    /**
     * get the number of item
     * @return number of item
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * set the number of item
     * @param cnt
     */
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    /**
     * get the total price
     * @return total price
     */
    public double getTotalPrice() {
        calPrice();
        return totalPrice;
    }

    /**
     * set total price
     * @param totalPrice
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * add number of items
     * @param obj
     * @return true if add success, false else
     */
    @Override
    public boolean add(Object obj) {
        try {
            MenuItem menuItem = (MenuItem) obj;
            for (int i = 0; i < lists.size(); i++) {
                MenuItem menu = lists.get(i);
                if (menu.equals(menuItem)) {
                    int cnt = menu.getCnt() + menuItem.getCnt();
                    menu.setCnt(cnt);
                    return true;
                }
            }
            lists.add(menuItem);
            cnt = lists.size();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * calculate the price of item
     */
    private void calPrice() {
        totalPrice = 0;
        for (MenuItem menuItem : lists) {
            totalPrice += menuItem.itemPrice();
        }
    }

    /**
     * remove item
     * @param obj
     * @return true if success, false else
     */
    @Override
    public boolean remove(Object obj) {
        try {
            MenuItem menuItem = (MenuItem) obj;
            lists.remove(menuItem);
            cnt = lists.size();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *get the information of item
     * @return information of item
     */
    @Override
    public String toString() {
        double sub = getTotalPrice();
        double tax = sub * 0.06625;
        double total_price = sub + tax;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String str = "Number of Order: " + orderNum + ", Number of goods: " + cnt + ", sub Price: "+ decimalFormat.format(sub) + ", tax: " + decimalFormat.format(tax) +", total: " + decimalFormat.format(total_price) + ", Information: \n";
        for (MenuItem menuItem: lists) {
            str += menuItem.toString() + ", price: " + decimalFormat.format(menuItem.itemPrice()) + "\n";
        }
        return str;
    }
}
