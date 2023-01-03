package com.example.project;

import java.io.Serializable;

/**
 * This class defines the data structure of an donut to be displayed in the RecyclerView
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class Donuts implements Serializable {
	private String donuts_name;
	private int donuts_image;
	private String donuts_unitPrice; //for demo purpose, the unitPrice is of String type

	/**
	 * Parameterized constructor.
	 * @param donuts_name
	 * @param donuts_image
	 * @param donuts_unitPrice
	 */
	public Donuts(String donuts_name, int donuts_image, String donuts_unitPrice) {
		this.donuts_name = donuts_name;
		this.donuts_image = donuts_image;
		this.donuts_unitPrice = donuts_unitPrice;
	}

	/**
	 * get donuts name
	 * @return the item name of an item.
	 */
	public String getDonuts_name() {
		return donuts_name;
	}

	/**
	 * get the image of donut
	 * @return the image of an item.
	 */
	public int getDonuts_image() {
		return donuts_image;
	}

	/**
	 * get the unit price of donut
	 * @return the unit price of the item.
	 */
	public String getDonuts_unitPrice() {
		return donuts_unitPrice;
	}
}
