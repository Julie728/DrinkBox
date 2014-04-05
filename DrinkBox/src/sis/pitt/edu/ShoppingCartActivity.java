package sis.pitt.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.os.AsyncTask;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Display all the items added by user in the shopping cart
 * It also displays checkboxes for each item, allowing user to remove selected items
 * @author Liu
 *
 */
public class ShoppingCartActivity extends Activity{

 private List<Item> myCart;
 private ItemAdapter myItemAdapter;
 
 @Override
  protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.shoppingcart);
  
  //update shopping cart
  myCart = ItemCollection.getCart();
  
  // Make sure to clear the selections
  for(int i = 0; i < myCart.size(); i++) {
	  myCart.get(i).setSelected(false);
  }
  
  // Create the list
  final ListView listViewCart = (ListView) findViewById(R.id.listViewCart);
  myItemAdapter = new ItemAdapter(myCart, getLayoutInflater(), true);
  listViewCart.setAdapter(myItemAdapter);
  
  listViewCart.setOnItemClickListener(new OnItemClickListener() {

   @Override
   public void onItemClick(AdapterView<?> parent, View view, int position,
     long id) {
    
    Item selectedProduct = myCart.get(position);
    if(selectedProduct.getSelected() == true)
     selectedProduct.setSelected(false);
    else
     selectedProduct.setSelected(true);
    
    myItemAdapter.notifyDataSetInvalidated();
    
   }
  });//end of listViewCart.setOnItemClickListener
  
  Button removeButton = (Button) findViewById(R.id.ButtonRemoveFromCart);
  removeButton.setOnClickListener(new OnClickListener() {
   @Override
   public void onClick(View v) {
    // Loop through and remove all the items that are selected
    // Loop backwards so that the remove works correctly
    for(int i=myCart.size()-1; i>=0; i--) {
     
     if(myCart.get(i).getSelected()) {
    	 myCart.remove(i);
     }
    }
    myItemAdapter.notifyDataSetChanged();
   }
  }); //end of removeButton.setOnClickListener
  
  //When clicking, call remote servlet to calculate the cart total
  //sending items' price as request
  //getting cart's total as response
  Button cartTotalButton = (Button) findViewById(R.id.ButtonCartTotal);
  cartTotalButton.setOnClickListener(new OnClickListener() {
	  String servletUrl = "http://10.0.2.2:8080/DrinkBox_Servlet";
	  TextView textCartTotal = (TextView) findViewById(R.id.TextCartTotal);
	  
	  @Override
	public void onClick(View view) 
	  {
		  int n = myCart.size();
		  String items = "";
		  String prices = "";
		  for(int i = 0; i < n; i++) 
		  {
			  items += myCart.get(i).getName() + ",";
			  double itemPrice = myCart.get(i).getPrice();
			  prices += String.valueOf(itemPrice) + ",";
		  }//end of forLoop
		  
		  servletUrl += "?items=" + items + "&prices=" + prices;
		  
	      GetXMLTask task = new GetXMLTask();
	      task.execute(new String[] { servletUrl });
	   }


    class GetXMLTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String output = null;
            for (String url : urls) {
                output = getOutputFromUrl(url);
            }
            return output;
        }
 
        private String getOutputFromUrl(String url) {
            StringBuffer output = new StringBuffer("");
            try {
                InputStream stream = getHttpConnection(url);
                BufferedReader buffer = new BufferedReader(
                        new InputStreamReader(stream));
                String s = "";
                while ((s = buffer.readLine()) != null)
                    output.append(s + System.getProperty("line.separator"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return output.toString();
        }
 
        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
 
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();
 
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
 
        @Override
        protected void onPostExecute(String output) {
        	textCartTotal.setText(output);
        }
    }
  
  }); //end of viewCartTotal.setOnClickListener
  
 }//end of onCreate()

}//end of class