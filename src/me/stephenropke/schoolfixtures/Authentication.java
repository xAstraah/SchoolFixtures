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
    
    private static String username = "Guest";
    private static int authLevel = 0;
    
    public void setUsername(String un) {
        Authentication.username = un;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setAuthLevel(int i) {
        Authentication.authLevel = i;
    }
    
    public int getAuthLevel() {
        return authLevel;
    }
    
}
