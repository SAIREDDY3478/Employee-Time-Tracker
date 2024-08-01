<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Helvetica+Neue&display=swap" rel="stylesheet" />
    <style><%@include file="CustomerLogin.css"%></style>
    <title>Login</title>
</head>
<body class="page">
    <form class="form-control" action="LoginServlet" method="post">
        <p class="title">Login</p>
        <div class="input-field">
            <input required="" class="input" type="text" name="username"/>
            <label class="label" for="input">Enter Username:</label>
        </div>
        <div class="input-field">
            <input required="" class="input" type="password" name="password"/>
            <label class="label" for="input">Enter Password</label>
        </div>
        <button class="submit-btn" type="submit">Login</button>
        <% if ("invalid".equals(request.getParameter("error"))) { %>
            <p class="error-message">Invalid username or password. Please try again.</p>
        <% } %>
    </form>
</body>
</html>