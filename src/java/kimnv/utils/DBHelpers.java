package kimnv.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguye
 */

//Excecuted Server-side -> Implements Serializable
public class DBHelpers implements Serializable{
    public static Connection makeConnection() 
            //throws ClassNotFoundException, SQLException,
            throws NamingException, SQLException{
        
//    //1. Load driver -> add driver into obj
//    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //Xu ly loi cho thang goi ham 
//    //2. Create Connection String to determine Container address
//    String url = "jdbc:sqlserver://localhost:1433;databaseName=DBLogin"; //Protocol:Server://ip:port;databaseName=DB[;instanceName=Instance]
//    //3. Open connection
//    Connection con = DriverManager.getConnection(url,"sa","admin");
//        return con;

    //See which os is in use
    Context currentContext = new InitialContext();
    Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
    DataSource ds = (DataSource)tomcatContext.lookup("DS");
    Connection con = ds.getConnection();
    
    return con;
    }
}
