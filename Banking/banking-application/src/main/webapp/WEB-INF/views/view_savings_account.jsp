<%@page import="com.ab.entities.Customer"%>
<%@page import="com.ab.entities.SavingsAccount"%>
<%@page import="java.util.List"%>
<%@page import="com.ab.entities.Transaction"%>
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

* {box-sizing: border-box}

/* Add padding to containers */
.container {
  padding: 16px;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: LightPink;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for the submit/register button */
.registerbtn {
  background-color: powderblue;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.registerbtn:hover {
  opacity:1;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
  background-color: #f1f1f1;
  text-align: center;
}


</style>

<body>

 <% Customer c = (Customer)session.getAttribute("loggedInCustomer"); %>

   <h1><%= " " + c.getFirstname() + "'s Savings Account!" %> </h1>
    

 <% SavingsAccount account = (SavingsAccount)session.getAttribute("savingsAccountToView"); %>

Account Number : <%= " " + account.getAccountNumber() %> <br><br>
Account Name : <%= " " + account.getAccountName() %> <br><br>
Balance : <%= " " + account.getBalance() %> <br><br>
Date Opened : <%= " " + account.getDateOpened() %> <br><br>
Level : <%= account.getPoints() - 1 %> <br><br>


	<form action="/depositSavings" method="POST">
	
		Amount to deposit : <input type="text" name="money" /> <br>
	    
	    <input type="submit" class="registerbtn"  value="Deposit"/>
	   
	</form>
	
	<br><br>
	
	<form action="/withdrawSavings" method="POST">
	
		Amount to withdraw : <input type="text" name="money" /> <br>
	    
	    <input type="submit" class="registerbtn"  value="Withdraw"/>
	   
	</form>



<br>

<br>

<a href="view_transactions"> Load Transactions! </a> <br>



<br>

<a href="welcome"> Back to home page! </a> <br>

</body>
</html>