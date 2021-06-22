/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kimnv.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet"; //lay tu url pattern context.xml
    private final String SEARCH_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String STARTUP_CONNECTION = "StartupServlet";
    private final String ADD_TO_CART_CONTROLLER = "AddBookToCartServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String REMOVE_BOOK_FROM_CART_CONTROLLER = "RemoveBookFromCartServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String NEW_ACC = "NewAccountServlet";
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
        
        String url = LOGIN_PAGE;
        String button = request.getParameter("btAction"); //Ban muon an mon gi
        try {
            if(button == null){
                //do nothing
                //button = null -> access at first time
                url = STARTUP_CONNECTION;
            }else if(button.equals("Login")){
                url = LOGIN_CONTROLLER;
            }else if(button.equals("Search")){
                url = SEARCH_CONTROLLER;
            }else if(button.equals("Delete")){
                url = DELETE_ACCOUNT_CONTROLLER;
            }else if(button.equals("Update")){
                url = UPDATE_ACCOUNT_CONTROLLER;
            }else if(button.equals("Add to Cart")){
                url = ADD_TO_CART_CONTROLLER;
            }else if(button.equals("View Cart")){
                url = VIEW_CART_PAGE;
            }else if(button.equals("Remove selected items")){
                url = REMOVE_BOOK_FROM_CART_CONTROLLER;
            }else if(button.equals("Logout")){
                url = LOGOUT_CONTROLLER;
            }else if(button.equals("Create new account")){
                url = NEW_ACC;
            }
        }finally{
            //Using request dispatcher for prologing lifetime of rq obj
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
