package sis.pitt.edu;

import android.graphics.drawable.Drawable;

/**
 * Each basic item was described with ID, name, image, price and description sentence
 * the variable "selected" indicates whether this item is chose by the user or not
 * @author Liu
 *
 */
public class Item {
	private int itemID;
	private String name;
	private Drawable image;
	private double price;
	private String description;
	private boolean selected;
	
	public Item(int itemID, String name, Drawable image, double price, String description)
	{
		this.itemID = itemID;
		this.name = name;
		this.image = image;
		this.price = price;
		this.description = description;		
	}
	
	public boolean getSelected()
	{
		return this.selected;
	}

	public Drawable getImage() {
		return image;
	}

	public String getName() {
		return name;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setSelected(boolean flag)
	{
		this.selected = flag;
	}
}
