package sis.pitt.edu;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


/**
 * This class displays all the available items from the List<Item> Menu
 * using the method from ItemCollection
 * @author Liu
 *
 */
public class MenuActivity extends Activity{
	
private List<Item> myMenu;
	
 /** Called when the activity is first created. */
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.menu);

  // Obtain a reference to the product catalog
  myMenu = ItemCollection.getMenu(getResources());
  
  // Create the menu list
  ListView listViewMenu = (ListView) findViewById(R.id.listViewMenu);
  listViewMenu.setAdapter(new ItemAdapter(myMenu, getLayoutInflater(), false));  
  //when clicking on an item in the menu, it takes user to the item details
  listViewMenu.setOnItemClickListener(new OnItemClickListener() {  
   @Override
   public void onItemClick(AdapterView<?> parent, View view, int position,
     long id) {
    Intent itemDetailsIntent = new Intent(getBaseContext(),ItemDetailsActivity.class);
    itemDetailsIntent.putExtra(ItemCollection.ITEM_INDEX, position);
    startActivity(itemDetailsIntent);
   }
  });//end of listViewMenu.setOnItemClickListener
  
  //when clicking on the "Go to Cart" button, it takes user to the shopping cart
  Button viewShoppingCart = (Button) findViewById(R.id.ButtonViewCart);
  viewShoppingCart.setOnClickListener(new OnClickListener() {
   @Override
   public void onClick(View v) {
    Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
    startActivity(viewShoppingCartIntent);
   }
  });//end of viewShoppingCart.setOnClickListener
  
 }//end of onCreate()
}//end of class