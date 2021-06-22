/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kimnv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kimnv.cart.CartObj;

/**
 *
 * @author nguye
 */
@WebServlet(name = "RemoveBookFromCart", urlPatterns = {"/RemoveBookFromCart"})
public class RemoveBookFromCartServlet extends HttpServlet {

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
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        try {
           //1. Khách hàng tìm giỏ của họ
           //Check vùng nhớ còn tồn tại ko
           HttpSession session = request.getSession(false);
           if(session != null){
           //2.Khách hàng lấy giỏ hàng của họ
               CartObj cart = (CartObj)session.getAttribute("CART");
               if(cart != null){
                   //3.Khách hàng lấy hàng trong giỏ
                   Map<String,Integer> items = cart.getItems();
                   if(items != null){
                       //4.Khách hàng lấy danh sách món hàng cần xoá
                       String[] selectedItems = request.getParameterValues("chkItem");
                       //5.Xoá hàng khỏi giỏ
                       if(selectedItems != null){
                           for(String title : selectedItems){
                               cart.removeBookFromCart(title);
                           }
                           session.setAttribute("CART", cart);
                           
                       }//selected Items list has existed
                   }//end if items have book
                   
               }
           }//Session exists
           
           //6.Gọi lại chức năng View Cart để refresh
           String urlRewriting = "cart"
                   + "?btAction=View Cart";
           response.sendRedirect(urlRewriting);
        }finally{
            
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
