<%@page import="com.ab.entities.Customer"%>
<%@page import="com.ab.entities.CurrentAccount"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<style>
body {background-color: white;}
h1   {color: black;}


a {
  color: LightPink;
}

</style>

<body>

 <% Customer c = (Customer)session.getAttribute("loggedInCustomer"); %>

   <h1> Welcome to your new current account  <%= " " + c.getFirstname() + "!" %> </h1>
    

<% CurrentAccount account = (CurrentAccount)session.getAttribute("currentAccount"); %>

Account Number : <%= " " + account.getAccountNumber() %> <br><br>
Account Name : <%= " " + account.getAccountName() %> <br><br>
Balance : <%= " " + account.getBalance() %> <br><br>
Date Opened : <%= " " + account.getDateOpened() %> <br><br>

<a href="welcome"> Back to home page! </a> <br>

</body>
</html>