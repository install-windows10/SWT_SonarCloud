<%@page import="java.util.Map"%>
<%@page import="kimnv.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your cart includes</h1>
        <!---->
        <%
            //1.Khách hàng đến chỗ đặt giỏ của họ
            //Phải kiểm tra có giỏ chưa
            if (session != null) {
                //2.Khách hàng lấy giỏ của họ
                //Lấy attribute từ Scope session
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    //Cart = pointer to items
                    //3.Customer gets items to check 
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <form action="RemoveBook">
                <tbody>
                    <%
                        int count = 0;
                        for (String title : items.keySet()) {
                    %>
                    <tr>

                        <td><%= ++count%></td>
                        <td><%= title%></td>
                        <td><%= items.get(title)%></td>
                        <td>
                            <input type="checkbox" name="chkItems" 
                                   value="<%= title%>" />
                        </td>


                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="3">

                            <a href="bookStore.html">Add more to cart</a>

                        </td>
                        <td>

                            <input type="submit" value="Remove selected items" name="btAction" />

                        </td>
                    </tr>
                </tbody>
            </form>
        </table>

        <h2>No item in cart</h2>
        
        <%
                    }//end if items not null
                }//end if cart existed

            }
        %>
        
    </body>
</html>
