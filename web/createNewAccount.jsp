<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a new Account</title>
    </head>
    <body>
        <h1>Create new account</h1>
        <form action="newAccount" method="POST">
            <c:set var ="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 30)<br>
            <c:if test ="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font><br>
            </c:if>
                
            Password* <input type="password" name="txtPassword" value="" />(6 - 20)<br>
             <c:if test ="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}<br>
                </font>
            </c:if>
                
            Confirm* <input type="password" name="txtConfirm" value="" /><br>
            <c:if test ="${not empty errors.confirmNotMatchErr}">
                <font color="red">
                ${errors.confirmNotMatchErr}<br>
                </font>
            </c:if>
            Full name* <input type="text" name="txtFullName" value="${param.txtFullName}" /> (2-50)<br>
            <c:if test ="${not empty errors.fullNameLengthErr}">
                <font color="red">
                ${errors.fullNameLengthErr}<br>
                </font>
            </c:if>
            
            <input type="submit" value="Create new account" name="btAction" />
            <input type="submit" value="Reset" name ="Reset" />
            
        </form>
            
            <form action ="logout">
            <input type="submit" value="Cancel Registration" name="btAction"/>
            </form>
            
               <c:if test ="${not empty errors.userIsExisted}">
                <font color="red">
                ${errors.userIsExisted}<br>
                </font>
            </c:if> 
                
    </body>
</html>
