<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Successful</title>
</head>
<body>
<h2>Welcome, <%= request.getAttribute("user") %>!</h2>
<p>You have successfully logged in.</p>
</body>
</html>
