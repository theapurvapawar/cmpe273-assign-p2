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
<title>Cart</title>

<style>
#stage {
    height: 120px;
}
#pUrl {
    float:left;
} 
#pname, #pPrice, #pQty {
	position: relative;
	clear:right;
	font-size:20px;
	padding:10px;
}
#pPrice{
clear:left;
float:left;}
</style>
</head>
<body>

 <script type="text/javascript" language="javascript">
 $(document).ready(function() {     
          $.getJSON('http://localhost:8080/order-mgmt/rest/json/getProductInfo?_id=<%= session.getAttribute( "productId" ) %>', function(jd) {
        	  
              $('#stage').append('<div id="pUrl"><img src="http://localhost:3000'+jd.image_url+'" height="75"></<div>');
              $('#stage').append('<div id="pname">' + jd.name + '</<div>');
              $('#stage').append('<div id="pPrice">Price : $' + jd.price+ ' per item.</<div>');
              $('#stage').append('<div id="pQty">Quantity Available : ' + jd.quantity+ '</<div>')
              
           });
       });
 </script>
        	  
 <div class="wrapper">
    <form class="form-signin" action="createOrder" method="post"> 
    	<div id=stage></div>    
      <h2 class="form-signin-heading">Add to Cart and Buy</h2>
      <input type="text" class="form-control" name="quantity" placeholder="Quantity" required="" autofocus="" />
      <input type="text" class="form-control" name="address" placeholder="Address" required=""/>
      <input type="text" class="form-control" name="cardNumber" placeholder="Card Number" required=""/>
      <input type="password" class="form-control" name="cvv" placeholder="CVV" required=""/>
      <input type="text" class="form-control" name="nameOnCard" placeholder="Name as on Card" required=""/>      
      <br>
      <input type="hidden" name="productId" value="<%= session.getAttribute( "productId" ) %>">
		<input type="hidden" name="userId" value="<%= session.getAttribute( "userId" ) %>">
      <button class="btn btn-lg btn-primary btn-block" type="submit">Buy</button> 
      
    </form>
    
  </div>

</body>
</html>