<%@page import="com.ab.entities.Customer"%>
<%@page import="com.ab.entities.SavingsAccount"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>You won!</title>

<style>
body {background-color: white;}
h1   {color: black;}


a {
  color: LightPink;
}

</style>

</head>


<% Customer c = (Customer)session.getAttribute("loggedInCustomer"); %>

  <h1> Hello <%= " " + c.getFirstname() + "!" %> </h1>
  
  
  <% SavingsAccount account = (SavingsAccount)session.getAttribute("savingsAccountToView"); %>
  
  <h2> You won gift level <%= account.getPoints()-1 %></h2>
    


<a href="view_savings_account"> Back to savings account! </a> <br>
</body>
</html>