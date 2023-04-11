<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html> 
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> Mariyamman Indian Bank </title>
<link rel="stylesheet" href="./css/usertransfermoney.css">
</head>  
<body> 
<%
	response.setHeader("Cache-Control","no=cache,no-store,must-revalidate");

	response.setHeader("Pragma","no-cache");
	
	response.setHeader("Expires","0");

	if(session.getAttribute("username")==null)
		response.sendRedirect("index.jsp");

%> 
    <h1> Mariyamman Indian Bank </h1> 
    <div class="container">
        <h2>Amount Transferred Success......</h2>
        <div class="back">
            <p><b><a id="back" href="userhome.jsp">Go Back</a></b></p>
        </div>
    </div> 
    <form action="logout">
    	<div class="logout-btn">
        <button id="logout-btn" type="submit">Log out</button>  
    	</div>
    </form>	
</body>   
</html>