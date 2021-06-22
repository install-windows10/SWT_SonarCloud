/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLogin;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import kimnv.utils.DBHelpers;

/**
 *
 * @author nguye
 */

//Phai khai bao bien obj o dau phuong thuc. Khong duoc gan bien o khoi tao
public class DBLoginDAO implements Serializable{
    public boolean checkLogin(String username, String password) 
            throws SQLException, NamingException{
        
        //Phai khai bao bien obj o dau phuong thuc. Cho bang null. Khong duoc gan bien o khoi tao
        //Doi voi object, truoc khi su dung -> kieu null
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
           //1.Connect DB 
           con = DBHelpers.makeConnection();
           
           if(con != null){
                      //2.Create SQL String
                      String sql = "Select username"
                              + " From TblLogin"
                              + " Where username = ? And password = ?";
                      //3.Create statement and assign Parameter if any
                    stm = con.prepareStatement(sql);
                    stm.setString(1,username);
                    stm.setString(2,password);
                      //4.Excecute Query
                      rs = stm.executeQuery();
                      //5.Process RS
                      if(rs.next())
                          return true;        
                  }
           
           //End if con is connected.
        }finally{
            if(stm != null){
                stm.close();
            }
            if(rs != null){
                rs.close();
            }
            if(con != null){
                con.close();
            }
        }
        
        return false;
    }
    
    //List DTO
    private List<TblLoginDTO> accountList;
    
    //De lay list DTO 
    public List<TblLoginDTO> getAccountList() {
        return accountList;
    }
    
    public void searchLastname(String searchValue) 
            throws SQLException, NamingException{
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            
        //1.Connect DB
        con = DBHelpers.makeConnection();
        if(con != null){
            //2.Create SQL String
            String sql = "Select username, password, lastname, isAdmin "
                    + " From TblLogin "
                    + " Where lastname Like ?";//like: chua 1 substring
        
        //3.Create statement and assign value to parameter 
        stm = con.prepareStatement(sql);
        stm.setString(1, "%" + searchValue +"%"); //ko search String rong
        //4.Execute Query
        rs = stm.executeQuery(); //Ko truyen parameter vi...
        //5.Process Result
        while(rs.next()){
            String username = rs.getString("username");
            String password = rs.getString("password");
            String fullname = rs.getString("lastname");
            boolean role = rs.getBoolean("isAdmin");
            
            TblLoginDTO dto = 
                    new TblLoginDTO(username, password, fullname, role);
            
            if(this.accountList == null){
                this.accountList = new ArrayList<>();
            }//end if list account is not existed
            
            this.accountList.add(dto);
            
            
        }
        }
        
        
    }finally{
            if (rs != null) {
                rs.close();
            }if (stm != null)
                stm.close();
            if(con != null){
                con.close();
            }
        }
    }
    
    public boolean deleteAccount(String username) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{          
        //1.Connect DB
        con = DBHelpers.makeConnection();
        if(con != null){
            //2.Create SQL String
            String sql = "Delete From TblLogin"
                    + " Where username = ?";
        
        
        //3.Create statement and assignment value to parameter 
        stm = con.prepareStatement(sql);
        stm.setString(1,username);
        
        int row = stm.executeUpdate();//Kiem tra so song da xoa trong db
        if(row > 0){
            return true;
        }
        }       
        
        }finally{
            if(con != null){
                con.close();
            }
            if(stm != null){
                stm.close();
            }
        }
        
        
        return false;   
    }
    

    public boolean updateAccount(String username, String password, String role) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean isAdmin = false;
        
        try{
            con = DBHelpers.makeConnection();
            
            String sql = "Update TblLogin"                   
                    + " set password = ?, isAdmin = ?"
                    + " where username = ?";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, password);
            
            if(role != null){
                if(role.equals("ON"))
                    isAdmin = true;
                
            }else isAdmin = false;           
            stm.setBoolean(2, isAdmin);
            
            stm.setString(3,username);
            int row = stm.executeUpdate();
            if(row > 0) 
                return true;

        }finally{
            if(stm != null)
                stm.close();
            if(con != null)
                con.close();
        }
        
        
        return false;
    }
    
    //Nen truyen het cac cot duoi database vao
    public boolean createNewAccount(String username, String password, String fullname, boolean role) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{          
        //1.Connect DB
        con = DBHelpers.makeConnection();
        if(con != null){
            //2.Create SQL String
            String sql = "Insert Into TblLogin(username, password, lastname, isAdmin"
                    + " Values(?, ?, ?, ?)";
        
        
        //3.Create statement and assignment value to parameter 
        stm = con.prepareStatement(sql);
        stm.setString(1,username);
        stm.setString(2,password);
        stm.setString(3,fullname);
        stm.setBoolean(4,role);
        
        int row = stm.executeUpdate();//Kiem tra so song da xoa trong db
        if(row > 0){
            return true;
        }
        }       
        
        }finally{
            if(con != null){
                con.close();
            }
            if(stm != null){
                stm.close();
            }
        }
        
        
        return false;   
    }
    
}
