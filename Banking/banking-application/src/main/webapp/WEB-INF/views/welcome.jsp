<%@page import="com.ab.entities.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome </title>

<style>
body {background-color: white;}
h1   {color: black;}


a {
  color: LightPink;
}

</style>

</head>
<body>

  <h1 action=/login method="POST"> 
   
    <% Customer c = (Customer)session.getAttribute("loggedInCustomer"); %>

    Hello <%= " " + c.getFirstname() + "!" %>
    
    </h1>
    
    Create A New Account  <a href="createAccount"> Here! </a> <br>
   
 
   	View Accounts <a href = "accounts"> Here! </a> <br>
 
    
    
    
</body>
</html>