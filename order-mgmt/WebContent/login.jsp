<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Yo ecommerce - Login</title>
</head>
<body>

 
  <div class="wrapper">
    <form class="form-signin" action="authenticate" method="post">       
      <h2 class="form-signin-heading">Login</h2>
      <input type="text" class="form-control" name="username" placeholder="Username" required="" autofocus="" />
      <input type="password" class="form-control" name="password" placeholder="Password" required=""/>      
      <br>
      <input type="hidden" name="productId" value="<%= request.getParameter( "_id" ) %>">
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button> 
      <br>  
    	<a href="register.jsp?_id=<%= request.getParameter( "_id" ) %>">Register</a>
    </form>
    
  </div>
</body>
</html>