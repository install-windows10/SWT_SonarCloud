/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLogin;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class TblLoginDTO implements Serializable{
    //Khai bao ung voi row trong tablet
    private String username;
    private String password;
    private String fullname;
    private boolean role;

    public TblLoginDTO() {
    }

    public TblLoginDTO(String username, String password, String fullname, boolean role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the role
     */
    public boolean getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "TblLoginDTO{" + "username=" + username + ", password=" + password + ", fullname=" + fullname + ", role=" + role + '}';
    }
    
    
}
