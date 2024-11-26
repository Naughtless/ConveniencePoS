/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usr.jason.mainSales;

import java.sql.*;

/**
 *
 * @author jason
 */
public class DatabaseHandler {
    //Private variables and some default values.
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String directory = "jdbc:mysql://localhost/cstore_pos2";
    private static String username = "root"; //Default for phpMyAdmin is root
    private static String password = ""; //Default for phpMyAdmin is blank.
    
    private static ResultSet query(String statement) throws Exception{
        Class.forName(driver);
        Connection connectionEndpoint = DriverManager.getConnection(directory, username, password);
        Statement statementEndpoint = connectionEndpoint.createStatement();
        try{
            System.out.println("Attempting to perform executeQuery() ...");
            return statementEndpoint.executeQuery(statement);
        }
        catch(SQLException e){
            System.out.println("==== <Handled Exception> ====\n" + e + "\nTask executeQuery() failed due to attempted data manipulation.\nAttemtping to perform executeUpdate() ...");
            statementEndpoint.executeUpdate(statement);
            System.out.println("=== Success! ====");
            return null;
        }
        catch(Exception e){
            System.out.println("=== ERROR: Unhandled Exception ===\n"+e);
            return null;
        }
        finally{
            connectionEndpoint.close();
        }
    }
    
    public static boolean checkID(String productID) throws Exception{
        String statement = "SELECT * FROM products_table WHERE productID = "+productID;
        ResultSet data = query(statement);
        
        if(data.next()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static String[] getProductInfo(String productID) throws Exception{
        String statement = "SELECT * FROM products_table WHERE productID = "+productID;
        
        String[] output = new String[3];
        ResultSet data = query(statement);
        
        if (data.next()) {
            output[0] = data.getString("productNAME").trim();
            output[1] = data.getString("productPRICE").trim();
            output[2] = data.getString("productDISCOUNT").trim();
        }
        else{
            System.out.println("That product ID does not exist!");
            UI_Popup.call("ERROR : That product ID does not exist!");
        }
        
        return output;
    }
    
    public static void registerProduct(Product newProduct) throws Exception {
        String PID = newProduct.getPID();
        String PName = newProduct.getPName();
        int PPrice = newProduct.getPPrice();
        int PDiscount = newProduct.getPDiscount();
        
        if(checkID(PID)){
            System.out.println("Product ID already exists! Use a new one!");
            UI_Popup.call("ERROR: Product ID already exists!");
            throw new Exception();
        }
        
        String statement = "INSERT INTO products_table (productID, productNAME, productPRICE, productDISCOUNT) VALUES ('" +PID+ "','" +PName+ "'," +PPrice+ "," +PDiscount+ ")";
        query(statement);
    }
    
    public static void modifyProduct(String PID, String PName, int PPrice, int PDiscount) throws Exception {
        if(!checkID(PID)){
            System.out.println("Specified Product ID does not exist!");
            UI_Popup.call("ERROR: Product ID does not exist!");
            throw new Exception();
        }
        
        String statement = "UPDATE products_table SET productNAME = '"+PName+"', productPRICE = "+PPrice+", productDISCOUNT = "+PDiscount+" WHERE productID = '"+PID+"'";
        query(statement);
    }
    
    public static void deleteProduct(String PID) throws Exception {
        if(!checkID(PID)){
            System.out.println("Specified Product ID does not exist!");
            UI_Popup.call("ERROR: Product ID does not exist!");
            throw new Exception();
        }
        
        String statement = "DELETE FROM products_table WHERE productID = "+PID;
        query(statement);
    }
    
    public static boolean checkUsername(String checkUsername) throws Exception{
        String statement = "SELECT * FROM users_table WHERE accountUSERNAME= '" + checkUsername + "'";
        ResultSet data = query(statement);
        
        if(data.next()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void registerAccount(Account account) throws Exception{
        String acUsername = account.getUsername();
        String acFullname = account.getFullname();
        String acPassword = account.getPassword();
        String acType = account.getAccountType();
        
        if(checkUsername(acUsername)){
            System.out.println("Username already taken!");
            UI_Popup.call("ERROR: Username already taken!");
            throw new Exception();
        }
        
        String statement = "INSERT INTO users_table (accountUSERNAME, accountFULLNAME, accountPASSWORD, accountTYPE) VALUES ('" +acUsername+ "','" +acFullname+ "','" +acPassword+ "','" +acType+ "')";
        query(statement);
        
    }
    
    public static void deleteAccount(String username) throws Exception{
        
        if(!checkUsername(username)){
            System.out.println("Username does not exist!");
            UI_Popup.call("ERROR: Username does not exist!");
            throw new Exception();
        }
        
        String statement = "DELETE FROM users_table WHERE accountUSERNAME = '" + username + "'";
        query(statement);
    }
    
    public static boolean checkLogin(String inputUsername, String inputPassword) throws Exception{
        
        String statement = "SELECT * FROM users_table WHERE accountUSERNAME = '" +inputUsername+ "'";
        
        String dbUsername = null;
        String dbPassword = null;
        ResultSet data = query(statement);
        
        if (data.next()) {
            dbUsername = data.getString("accountUSERNAME").trim();
            dbPassword = data.getString("accountPASSWORD").trim();
            System.out.println("DBPASSWORD: " + dbPassword);
            System.out.println("PASSWORD: " + inputPassword);
        }
        else{
            throw new Exception();
        }
        
        if(dbPassword.equals(inputPassword.trim())){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static String getAccountType(String inputUsername) throws Exception {
        String statement = "SELECT * FROM users_table WHERE accountUSERNAME = '" +inputUsername+ "'";
        ResultSet data = query(statement);
        
        if(data.next()){
            return data.getString("accountTYPE");
        }
        else{
            throw new Exception();
        }
    }
    
    public static String getFullname(String inputUsername) throws Exception {
        String statement = "SELECT * FROM users_table WHERE accountUSERNAME = '" +inputUsername+ "'";
        ResultSet data = query(statement);
        
        if(data.next()){
            return data.getString("accountFULLNAME");
        }
        else{
            throw new Exception();
        }
    }
    
}
