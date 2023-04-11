<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html> 
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> Mariyamman Indian Bank </title>
<link rel="stylesheet" href="./css/index.css">
</head>  
<body>  
    <h1> Welcome to Mariyamman Indian Bank </h1>  
    <form action="adminlogin" method="post">
        <div class="container"> 
            <label>Admin name : </label> 
            <input type="text" placeholder="Enter Admin Name" name="admin" required>
            <pre></pre>
            <label>Password : </label> 
            <input type="password" placeholder="Enter Password" name="password" required>
            <pre></pre>
            <button type="submit">Log in</button>
            <h3><a href="index.jsp">User login</a></h3>
        </div> 
    </form>   
</body>   
</html>