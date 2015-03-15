<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order History</title>
<script type="text/javascript" language="javascript">
   $(document).ready(function() {
      
          $.getJSON('http://localhost:8080/order-mgmt/rest/json/getOrdersForUser?_id=<%= session.getAttribute( "userId" ) %>', function(jsonArray) {
        	  $.each(jsonArray, function(idx, jd){
        		  $('#stage').append('<div class="product" id="product'+idx+'"></div>');
        		  console.log(jd.imageURL);
        		  $('#product'+idx).append('<div id="productImg"><img src="http://localhost:3000'+jd.imageURL+'" height="100"></div>');
      
        		$('#product'+idx).append('<div id="productOrderNum"> Order Number: ' + jd.orderNum + '</div>');
        		
        		if(!jd.cancelledStatus){
        				$('#product'+idx).append('<form action="cancelOrder" method="post">'+
        				'<input type="hidden" name="orderNum" value="'+jd.orderNum+'">'+
        				'<input id="cancelButton" type="submit" value="Cancel Order">'+
        				'</form>');
        		}
        	 	$('#product'+idx).append('<div id="productName"> Name: ' + jd.name + '</div>');
             	$('#product'+idx).append('<div id="productDesc"> Description: ' + jd.description+ '</div>');
             	$('#product'+idx).append('<div id="productQty"> Quantity: ' + jd.qtyOrdered+ '</div>');
             	$('#product'+idx).append('<div id="productDate"> Order Date: ' + jd.orderDate+ '</div>');
             	if(!jd.cancelledStatus)
             		$('#product'+idx).append('<div id="productStatus"> Order Status: ' + jd.orderStatus+ '</div>');
             	else
             		$('#product'+idx).append('<div id="productStatus"> Order Status: Cancelled</div>');
             	
             });
          });
     
   });
   </script>
</head>
<body>

 
<form action="logout" method="post">
	<input type="submit" value="Logout"  >
</form>  
   
 <div id="stage"></div>

 
 

<div></div>
</body>
</html>