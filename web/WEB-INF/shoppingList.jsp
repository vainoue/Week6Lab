<%-- 
    Document   : shoppingList
    Created on : Feb 14, 2023, 12:15:54 AM
    Author     : vitor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username}</p>
        <a href="ShoppingList?action=logout">Logout</a>
        <h2>List</h2>
        <form action="" method="post">
            Add item:
            <input type="text" name="input_item">
            <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>
        <form action="" method="post">
            <ul>
                <c:forEach var="add_item" items="${list_items}">
                    <li>
                        <input type="radio" name="item" value="${add_item}">${add_item}
                    </li>
                </c:forEach>
            </ul>
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>
