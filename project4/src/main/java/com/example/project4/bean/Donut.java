package com.example.project4.bean;

import java.util.Objects;

/**
 * Donuts class
 * manipulate the information of Donuts
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class Donut extends MenuItem{
    /**
     *  0:yeast donut  1:cake donut 2:donut holes
     */
    private static String cate[] = new String[]{"yeast donut", "cake donut", "donut holes"};
    private int type;
    private String flavor;
    private int cnt;
    public Donut( ) {
        cnt = 0;
    }

    /**
     * create constructor
     * @param type
     * @param flavor
     */
    public Donut(int type, String flavor) {
        this.type = type;
        this.flavor = flavor;
        cnt = 0;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    /**
     * calculate the price of donuts according to the type of donuts
     * @return price of donut
     */
    @Override
    public double itemPrice() {
        if (type == 0) return  1.59 * cnt;
        if (type == 1) return  1.79 * cnt;
        if (type == 2) return  0.39 * cnt;
        return 0;
    }

    /**
     * determine whether is same item
     * @param o
     * @return true if same, false else
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donut donut = (Donut) o;
        return type == donut.type  && Objects.equals(flavor, donut.flavor);
    }

    /**
     * get the hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, flavor);
    }

    /**
     * information of donuts
     * @return string that the information of donuts
     */
    @Override
    public String toString() {
        String str = cate[type] + ":" + flavor + "(" + cnt + ")";
        return str;
    }
}
