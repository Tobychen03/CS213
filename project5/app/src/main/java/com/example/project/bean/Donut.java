package com.example.project.bean;

import java.util.Objects;

/**
 * donuts parameter
 * create or set the information of donuts
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

    /**
     * create parameter constructor
     * @param flavor
     */
    public Donut(String flavor) {
        if (flavor.equals("jelly") ||  flavor.equals("glazed") || flavor.equals("chocolate frosted") || flavor.equals("strawberry frosted")) type = 0;
        if (flavor.equals("sugar") ||  flavor.equals("lemon filled") || flavor.equals("cinnamon sugar") || flavor.equals("old fashion") || flavor.equals("blueberry")) type = 1;
        if (flavor.equals("glazed holes") ||  flavor.equals("jelly holes") || flavor.equals("cinnamon sugar hole")) type = 2;

        this.flavor = flavor;
        this.cnt = 1;
    }

    /**
     * get flavor
     * @return
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * set flavor
     * @param flavor
     */
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    /**
     * get type of donut
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     * set type of donut
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * get the number of donuts
     * @return
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * set the number of donut
     * @param cnt number
     */
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
