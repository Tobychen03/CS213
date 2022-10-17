package com.example.project4.bean;

import com.example.project4.inter.Customizable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Coffee class
 * manipulate the information of coffee
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class Coffee extends MenuItem implements Customizable {


    private int addins = 0;
    private ArrayList<Integer> lists;
    private int size;
    private int cnt;
    private static String cate[] = new String[]{"cream", "syrup", "milk", "caramel", "whipped cream"};
    private static String size_str[] = new String[]{"Short", "Tall", "Grande", "Venti"};

    /**
     * create coffee constructor
     */
    public Coffee() {
        lists = new ArrayList<>();
        cnt = 0;
    }

    /**
     * create coffee constructor with parameters
     * @param addins
     * @param lists
     * @param size
     * @param cnt
     */
    public Coffee(Integer addins, ArrayList<Integer> lists, Integer size, Integer cnt) {
        this.addins = addins;
        this.lists = lists;
        this.size = size;
        this.cnt = cnt;
    }

    /**
     * get ingredients add in
     * @return
     */
    public int getAddins() {
        return addins;
    }

    /**
     * set ingredient which add in
     * @param addins
     */
    public void setAddins(Integer addins) {
        this.addins = addins;
    }

    /**
     * get the size
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * set the size of coffee
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * get the number of coffee
     * @return
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * set the number of coffee
     * @param cnt
     */
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    /**
     * add ingredient
     * @param obj
     * @return true if add in else false
     */
    @Override
    public boolean add(Object obj) {
        try {
            Integer ins = (Integer) obj;
            lists.add(ins);
            addins = lists.size();
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    /**
     * delete ingredient
     * @param obj
     * @return true if success else false
     */
    @Override
    public boolean remove(Object obj) {
        try {
            Integer ins = (Integer) obj;
            lists.remove(ins);
            addins = lists.size();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * calculate the price of coffee
     * @return total price of coffee
     */
    @Override
    public double itemPrice() {
        double base = 1.69;
        if(addins == 0){
            base += size * 0.4;
        }else {
            base += addins * 0.3 + size * 0.4;
        }
        return base * cnt;
    }

    /**
     * show the information of coffee
     * @return string which is information of coffee
     */
    public String toString() {
        String str = "Coffee(" + cnt + ")" + size_str[size];
        Collections.sort(lists);
        for (int i = 0; i <lists.size(); i++) {
            if (i == 0) str += "[";
            if (i != lists.size() - 1 ) str += cate[i] + ", ";
            else str += cate[i] + "]";
        }
        return str;
    }

    /**
     * determine whether coffee is equal
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        System.out.println(coffee + " " + this);
        boolean ok = true;
        Collections.sort(coffee.lists);
        Collections.sort(lists);
        if (lists.size() != coffee.lists.size()) {
            ok =false;
        } else {
            for (int i = 0; i < lists.size(); i++) {
                System.out.println(lists.get(i) + " " + coffee.lists.get(i));
                if (lists.get(i) == coffee.lists.get(i)) continue;
                ok = false;
                break;
            }
        }
        System.out.println(ok);
        System.out.println(addins + " "  + coffee.addins + " " + size + " " + coffee.size);
        return addins == coffee.addins && size == coffee.size && ok  ;
    }

    /**
     * get hash code
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(addins, lists, size, cnt);
    }
}
