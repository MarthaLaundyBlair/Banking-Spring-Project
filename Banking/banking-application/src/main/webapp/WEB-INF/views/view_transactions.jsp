<%@page import="com.ab.entities.Customer"%>
<%@page import="com.ab.entities.SavingsAccount"%>
<%@page import="java.util.List"%>
<%@page import="com.ab.entities.Transaction"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page import="com.ab.entities.CurrentAccount"%>
<%@page import="com.ab.entities.SavingsAccount"%>
<%@page import="java.util.List"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.ObjectOutputStream"%>
<%@page import="java.util.Base64"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Books</title>

<style>

a {
  color: LightPink;
}

th, td {
  padding: 15px;
  text-align: left;
}

th {
  background-color: lightpink;
  color: white;
}

.registerbtn {
  background-color: powderblue;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
</style>

</head>

<body>
 <% List<Transaction> accountTransactions = (List<Transaction> ) session.getAttribute("accountTransactions");%>

  
  <h1> List of Transactions : </h1>
 
  
  <br>
  <br>
  
  <table >
  
  <thread>
  
  	<tr>
  	
  		<th> Transaction Id </th>
  		<th> Transaction Type </th>
  		<th> Transaction Date </th>
  		<th> Amount </th>
  		<th> Balance </th>
  
  	</tr>
  	
  </thread>
  
  	<c:forEach items="${accountTransactions}" var="t">
		
		<tr>
		<td> <c:out value="${t.getTransactionId()}"/> </td>
		<td> <c:out value="${t.getTransactionType()}"/> </td>
		<td> <c:out value="${t.getDateOfTransaction()}"/> </td>
		<td> <c:out value="${t.getAmount()}"/> </td>
		<td> <c:out value="${t.getBalance()}"/> </td>
		
	
	
		</tr>
	
	</c:forEach>
  
  </table>
	
	

	

</body>

</html>