package sis.pitt.edu;

import java.util.List;
import java.util.Vector;
import android.content.res.Resources;

/**
 * In the collections, it holds the information about all the items
 * It keeps track of what items are in the Menu and the Shopping Cart
 * It also allows any activity to access this Menu and Shopping Cart data with the method getMenu() and getCart() 
 * @author Liu
 *
 */
public class ItemCollection {

	public static final String ITEM_INDEX = "ITEM_INDEX";
	private static List<Item> menu;
	private static List<Item> cart;
	
 public static List<Item> getMenu(Resources res){
	if(menu == null) 
	{
		menu = new Vector<Item>();
		//menu.add(new Item(1, "Coffee", res.getDrawable(R.drawable.ic_launcher), 1.89, "One of our rich, flavorful brewed coffees, selected to showcase our roasting and blending artistry."));
		menu.add(new Item(1, "Coffee", res.getDrawable(R.drawable.itemcoffee), 1.89, "One of our rich, flavorful brewed coffees, selected to showcase our roasting and blending artistry."));
		menu.add(new Item(1, "Tea", res.getDrawable(R.drawable.itemtea), 1.59, "A black breakfast tea of boldness, depth and character, invigorating any time of day."));
		menu.add(new Item(1, "Smoothies", res.getDrawable(R.drawable.itemsmoothies), 3.89, "A nourishing blend of natural strawberry puree, a whole banana, milk, whey protein and fiber powder, and ice."));
		menu.add(new Item(1, "Hot Chocolate", res.getDrawable(R.drawable.itemhotchocolate), 3.69, "Steamed milk with vanilla- and mocha-flavored syrups. Topped with sweetened whipped cream and chocolate-flavored drizzle."));
	}
	
	return menu;
}

	public static List<Item> getCart() {
	if(cart == null) {
	cart = new Vector<Item>();
	}
	return cart;
	}
}
