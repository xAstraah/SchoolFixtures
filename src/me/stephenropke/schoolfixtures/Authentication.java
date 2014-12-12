/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.stephenropke.schoolfixtures;

/**
 *
 * @author Stephen
 */
public class Authentication {
    
    /*
    * The variables username and authLevel are set to static so they cannot be changed by the application itself but only
    * changed manually by a method executed by the code.
    *
    * Also the variables are set to "Guest" and "0" at first so that if the user skips the login the variables will stay the
    * same but if they login the login code will change the variables.
    */
    
    private static String username = "Guest";
    private static int authLevel = 0;
    
    /*
    * Set's the field username to whatever String un contains.
    */
    
    public void setUsername(String un) {
        Authentication.username = un;
    }
    
    /*
    * Will return (print out) whatever the String username contains.
    */
    
    public String getUsername() {
        return username;
    }
    
    /*
    * Will set variable authLevel to 0 or 1, anything else will cause bugs.
    */
    
    public void setAuthLevel(int i) {
        Authentication.authLevel = i;
    }
    
    /*
    * Will return (print out) what the int authLevel contains.
    */
    
    public int getAuthLevel() {
        return authLevel;
    }
    
}
