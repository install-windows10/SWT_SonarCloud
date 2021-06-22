/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kimnv.controller;

import DBLogin.DBLoginCreateError;
import DBLogin.DBLoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
@WebServlet(name = "NewAccountServlet", urlPatterns = {"/NewAccountServlet"})
public class NewAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private final String ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String username = request.getParameter("txtUsername");
         String password = request.getParameter("txtPassword");
         String confirm = request.getParameter("txtConfirm");
         String fullName = request.getParameter("txtFullName");
         String url = ERROR_PAGE;
         DBLoginCreateError errors = new DBLoginCreateError();
         boolean foundErr = false;
         
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //1.Check valid user input
           if(username.trim().length()<6 || username.trim().length() > 30){
               foundErr = true;
               errors.setUsernameLengthErr("Username requires 6 - 30 characters");
           }
           if(password.trim().length()<6 || password.trim().length() > 30){
               foundErr = true;
               errors.setPasswordLengthErr("Password requires 6 - 20 characters");
           }else if(!password.trim().equals(confirm.trim())){
               foundErr = true;
               errors.setConfirmNotMatchErr("Confirm must match password.");
           }
           if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
            foundErr = true;
            errors.setFullNameLengthErr("fullname length must be from 2 - 50");
            }
            
           
           if(foundErr){
               //set attribute de luu loi lai o request scrope
               request.setAttribute("CREATE_ERROR", errors);
           }
           //2.Else call DAO
           DBLoginDAO dao = new DBLoginDAO();
           boolean result = dao.createNewAccount(username, password, fullName, false);
           if(result)
               url = LOGIN_PAGE;
            
//        }   catch (SQLException ex) {
//            String errMSG = ex.getMessage();
//            log("NewAccountServlet  _  SQL:"+errMSG);
//            if(errMSG.contains("duplicate")){
//                errors.setUserIsExisted(username + "existed");
//                request.setAttribute("CREATE_ERROR", errors);
//            }
        } catch (SQLException ex) {
            
            log("NewAccountServlet  _  SQL:" + ex.getMessage());
            errors.setUserIsExisted(username + "Username is already exist.");
            request.setAttribute("CREATE_ERRORS", errors);
        } catch (NamingException ex) {
            //Loi he thong -> Viet vao log
                log("NewAccountServlet  _  Naming: " +ex.getCause());
        }finally{
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
