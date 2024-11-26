/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usr.jason.mainSales;

/**
 *
 * @author jason
 */
public class Product {
    private String productID;
    private String productName;
    private int productPrice;
    private int productDiscount;
    
    public Product(String PID, String PName, int PPrice, int PDiscount){
        productID = PID;
        productName = PName;
        productPrice = PPrice;
        productDiscount = PDiscount;
    }
    
    public String getPID(){
        return productID;
    }
    
    public String getPName(){
        return productName;
    }
    
    public int getPPrice(){
        return productPrice;
    }
    
    public int getPDiscount(){
        return productDiscount;
    }

}
