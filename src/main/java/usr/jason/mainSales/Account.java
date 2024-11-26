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
public class Account {
    
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String accountType;
    
    public Account(String username, String firstname, String lastname, String password, String accountType){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.accountType = accountType;
    }
    
    public String getUsername(){
        return username;
    }
    public String getFullname(){
        return firstname + " " + lastname;
    }
    public String getPassword(){
        return password;
    }
    public String getAccountType(){
        return accountType;
    }
    
}
