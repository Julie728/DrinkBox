package sis.pitt.edu;

import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Display the detail of the item chose by the user
 * @author Liu
 *
 */

public class ItemDetailsActivity extends Activity 
{
 @Override
  protected void onCreate(Bundle savedInstanceState) {
  
  super.onCreate(savedInstanceState);
  setContentView(R.layout.itemdetails);
  
  List<Item> catalog = ItemCollection.getMenu(getResources());
  final List<Item> cart = ItemCollection.getCart();
  
  int productIndex = getIntent().getExtras().getInt(ItemCollection.ITEM_INDEX);
  final Item selectedItem = catalog.get(productIndex);
  
  // Set the proper image and text
  ImageView itemImageView = (ImageView) findViewById(R.id.ImageViewItem);
  itemImageView.setImageDrawable(selectedItem.getImage());
  TextView ItemNameTextView = (TextView) findViewById(R.id.TextViewItemName);
  ItemNameTextView.setText(selectedItem.getName());
  TextView itemDetailsTextView = (TextView) findViewById(R.id.TextViewItemDetails);
  itemDetailsTextView.setText("Price: $ " + selectedItem.getPrice() + "\n" + selectedItem.getDescription());

  Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);
  addToCartButton.setOnClickListener(new OnClickListener() { 
   @Override
   public void onClick(View v) {
    cart.add(selectedItem);
    finish();
   }
  });//end of addToCartButton.setOnClickListener
  
  // Disable the add to cart button if the item is already in the cart
  if(cart.contains(selectedItem)) {
   addToCartButton.setEnabled(false);
   addToCartButton.setText("Item in Cart");
  }
 }//end of onCreate()
 
}//end of class