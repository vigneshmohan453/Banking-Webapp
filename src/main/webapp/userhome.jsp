<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html> 
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> Mariyamman Indian Bank </title>
<link rel="stylesheet" href="./css/userhome.css">
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
            <label for="welcome">welcome ${username}</label><pre></pre>

            <form action="useraccountbalance" method="post">
            <button type="submit">Check Account Balance</button>
            </form>
            <form action="usertransfermoney.jsp" >
            <button type="submit">Transfer Money</button>
            </form>
            <form action="userwithdraw.jsp" >
            <button type="submit">Withdraw Money</button>
            </form>
            <form action="userdetails" method="post">
            <button type="submit">User Details</button>
            </form>
             <form action="userpasswordchange.jsp" method="post">
            <button type="submit">Change Password</button>
            </form>
        </div>   
         <form action="logout">
    	<div class="logout-btn">
        <button id="logout-btn" type="submit">Log out</button>  
    	</div>
    	</form>
</body>   
</html>