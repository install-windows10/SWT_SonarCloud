/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kimnv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DBLogin.DBLoginDAO;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kim
 */
//@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    //Bien hang tat ca ki tu in hoa
    private final String INVALID_PAGE = "invalid.html";
    //private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_PAGE = "search.jsp";
    private final String ERROR_PAGE ="error.html";
    private final String NEW_ACC = "createNewAccount.jsp";
    //Thiet lap trang default
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            //Toi uu bo nho -> ko lay username + password truoc khi bam button
            
            String url = INVALID_PAGE;  
                         
                  //if(button.equals("Login")){//Lay tu login.html, phan input submit
                  //Servlet thuc hien chuc nang login -> ko check
                    String username = request.getParameter("txtUsername");
                    String password = request.getParameter("txtPassword");
                    
                    
        try  {         
                //1. Init DAO
                    DBLoginDAO dao = new DBLoginDAO();
                    boolean result = dao.checkLogin(username, password);
                    out.print(result);
                    //2.Response the Request
                   if(result){
                       url = SEARCH_PAGE;
                   
                   
//                   Cookie cookie = new Cookie(username, password);//Luu password lo lieu -> 
//                   cookie.setMaxAge(60*3);
//                   response.addCookie(cookie);
//                   //Login thanh cong -> Must show Welcom
                   //Cookie de nho password, session de luu thong tin login va thao tac
                   
                   HttpSession session = request.getSession(true);
                   session.setAttribute("USERNAME",username);
                   
                   //BT: get full name: de welcome,{name}
                   //set Attribute full name;
                   }
                   
                   
                       
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(NamingException ex){
            ex.printStackTrace();
        }finally{
            //response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
