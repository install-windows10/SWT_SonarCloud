<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <font color ="red">
    Welcome, ${sessionScope.Username}
    </font>
    <form action="searchLastName">
        Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/> </br>
        <input type="submit" value="Search" name="btAction"/>        
    </form>
    <form action="logout">
        <input type="submit" value="Logout" name="btAction" />
    </form></br>

    <c:set var="searchValue" value="${param.txtSearchValue}"/>
    <c:if test="${not empty searchValue}">
        <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>

        <table border ="1">
            <thead>
                <tr>
                    <td>No.</td>
                    <td>Username</td> 
                    <td>Password</td>
                    <td>Full name</td>
                    <td>Role</td>
                    <td>Delete</td>
                    <td>Update</td>

                </tr>

            </thead>
            <tbody>
                <c:forEach var="dto" items="${result}"
                           varStatus="counter" >
                <form action="updateAccount">
                    <tr>
                        <td>${counter.count}.</td>                          
                        <td>
                            ${dto.username}        
                            <input type="hidden" name="txtUsername" value="${dto.username}" />
                        </td>
                        <td>
                            ${dto.password}
                            <input type="hidden" name="txtPassword" value="${dto.password}" />
                        </td>
                        <td>
                            ${dto.fullname}                   
                        </td>
                        <td>             
                            <input type="checkbox" name="chkAdmin" value="ON" 
                                   <c:if test="${dto.role}">
                                       checked = "checked"
                                   </c:if> />
                        </td>
                        <td>
                            <c:url var="deleteLink" value="deleteAccount">
                                <c:param name="btAction" value="del"/>
                                <c:param name="pk" value="${dto.username}"/>
                                <c:param name="lastSearchValue"
                                         value="${param.searchValue}"/>
                            </c:url>
                            <a href="${deleteLink}">Delete</a>
                        </td>
                        <td>
                            <input type="submit" value="Update" name="btAction"/>
                            <input type="hidden" name="lastSearchValue" value="${param.searchValue}" />
                        </td>

                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>

    <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
</c:if>

<c:if test="${empty searchValue}">
    <h2>No record match.</h2>
</c:if>

</html>
