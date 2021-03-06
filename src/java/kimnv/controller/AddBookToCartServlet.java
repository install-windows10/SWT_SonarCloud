/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kimnv.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddBookToCartServlet", urlPatterns = {"/AddBookToCartServlet"})
public class AddBookToCartServlet extends HttpServlet {
    private final String SHOPPING_PAGE ="bookStore.html";
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
        
        String url = SHOPPING_PAGE;
        
        try {
            //1.Customer đi đến chỗ lấy giỏ
            HttpSession session = request.getSession();
            //2.Customer lấy giỏ
            CartObj cart = (CartObj)session.getAttribute("CART");
            //Nếu ko có giỏ -> khởi tạo & cấp phát
            if(cart == null){
                cart = new CartObj();
            }//End if cart not exist
            //3.Khách hàng chọn hàng 
            String title = request.getParameter("cboBox");
            //4.Khách hàng bỏ hàng vào giỏ
            cart.addBookToCart(title);        
            //Sau khi bỏ phải set attribute (vì mới chỉ bỏ ở Servlet, chưa cập nhật lưu trữ)
            session.setAttribute("CART", cart);
            //5.Khách hàng tiếp tục shopping
            response.sendRedirect(url);
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
