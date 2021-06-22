/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLogin;

/**
 *
 * @author nguye
 */
public class DBLoginCreateError {
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String confirmNotMatchErr;
    private String fullNameLengthErr;
    private String userIsExisted;

    public DBLoginCreateError() {
    }
    
    public DBLoginCreateError(String usernameLengthErr, String passwordLengthErr, String confirmNotMatchErr, String fullNameLengthErr, String userIsExisted) {
        this.usernameLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.confirmNotMatchErr = confirmNotMatchErr;
        this.fullNameLengthErr = fullNameLengthErr;
        this.userIsExisted = userIsExisted;
    }

    

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmNotMatchErr() {
        return confirmNotMatchErr;
    }

    public void setConfirmNotMatchErr(String confirmNotMatchErr) {
        this.confirmNotMatchErr = confirmNotMatchErr;
    }

    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    public String getUserIsExisted() {
        return userIsExisted;
    }

    public void setUserIsExisted(String userIsExisted) {
        this.userIsExisted = userIsExisted;
    }
            
    
            
        
            
}
