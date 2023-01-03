package com.example.project.bean;
/**
 * menu parameter
 * create or set the information of menu
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class MenuItem {
    int cnt;

    /**
     * get the number of item
     * @return number of item
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * set the number of item
     * @param cnt number
     */
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    /**
     * get the price of item
     * @return price of item
     */
    public double itemPrice() {
        return 0;
    }
}
