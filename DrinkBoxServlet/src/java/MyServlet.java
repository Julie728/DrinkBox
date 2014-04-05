/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet accept request that contianing user's order informaiton process
 * the order and then returns the order confirmation as response
 *
 * @author Liu
 */
public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final double SHIPPING_FEE = 2.99;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("Order Confirmation: " + System.getProperty("line.separator"));

        if (request.getParameter("items") != null) {
            //calculate the cart total
            String items = request.getParameter("items");
            String prices = request.getParameter("prices");
            double cartTotal = 0;
            String[] a = items.split(",");
            String[] b = prices.split(",");
            for (int i = 0; i < a.length; i++) {
                out.println(a[i] + ": " + b[i] + System.getProperty("line.separator"));
                cartTotal += Double.valueOf(b[i]);
            }
            out.println("Shipping fee: " + SHIPPING_FEE + System.getProperty("line.separator"));
            cartTotal += SHIPPING_FEE;
            out.println("--------------------------------------------");
            out.println("Cart Total: " + cartTotal + System.getProperty("line.separator"));
        }

    }
}
