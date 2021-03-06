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
 <%
    
  List<CurrentAccount> currentAccounts = (List<CurrentAccount>)session.getAttribute("currentAccountList");
 List<SavingsAccount> savingsAccounts = (List<SavingsAccount>)session.getAttribute("savingsAccountList");
 
 %>
 
 
 <h1> List of Current Accounts : </h1>
 
  
  <br>
  <br>
  
  <table >
  
  <thread>
  
  	<tr>
  	
  		<th> Account Name </th>
  		<th> Account Number </th>
  		<th> Balance </th>
  		<th> Date Opened </th>
  
  	</tr>
  	
  </thread>
  
  	<c:forEach items="${currentAccountList}" var="c">
		
		<tr>
		<td> <c:out value="${c.getAccountName()}"/> </td>
		<td> <c:out value="${c.getAccountNumber()}"/> </td>
		<td> <c:out value="${c.getBalance()}"/> </td>
		<td> <c:out value="${c.getDateOpenedString()}"/> </td>
		
		<td>
		
			 <form action="/accounts" method="POST">
   
			    <input type = "hidden" name="<%= "accountToViewNumber" %>" value="${c.getAccountNumber()}" />
			    <input type = "hidden" name="<%= "accountToViewType" %>" value="Cur" />
			    <input type="submit" class="registerbtn"  value="View!"/>
   
   		</form> 
	
		</td>
	
		</tr>
	
	</c:forEach>
  
  </table>
  
  
  <h1> List of Savings Accounts : </h1>
 
  
  <br>
  <br>
  
  <table >
  
  <thread>
  
  	<tr>
  	
  		<th> Account Name </th>
  		<th> Account Number </th>
  		<th> Balance </th>
  		<th> Date Opened </th>
  
  	</tr>
  	
  </thread>
  
  	<c:forEach items="${savingsAccountList}" var="c">
		
		<tr>
		<td> <c:out value="${c.getAccountName()}"/> </td>
		<td> <c:out value="${c.getAccountNumber()}"/> </td>
		<td> <c:out value="${c.getBalance()}"/> </td>
		<td> <c:out value="${c.getDateOpenedString()}"/> </td>
		
		<td>
		
			 <form action="/accounts" method="POST">
   
			    <input type = "hidden" name="<%= "accountToViewNumber" %>" value="${c.getAccountNumber()}" />
			    <input type = "hidden" name="<%= "accountToViewType" %>" value="Sav" />
			    <input type="submit" class="registerbtn"  value="View!"/>
   
   		</form> 
	
		</td>
	
		</tr>
	
	</c:forEach>
  
  </table>
	
	

	

</body>

</html>