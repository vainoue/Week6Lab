<%-- 
    Document   : register
    Created on : Feb 14, 2023, 12:15:36 AM
    Author     : vitor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form action="ShoppingList" method="post">
            Username:
            <input type="text" name="username">
            <input type="hidden" name="action" value="register">
            <input type="submit" value="Register name">
        </form>
        <p>${empty_username}</p>
    </body>
</html>
