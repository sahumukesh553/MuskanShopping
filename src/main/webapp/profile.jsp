<%@include file="component/securepage.jsp" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="WEB-INF/mytags.tld" prefix="m" %>
<html>
<head>
<%@include file="component/common.jsp" %>
<title>profile</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<%@include file="component/Message.jsp" %>
<div class="container main">
<div class="row mt-5 ">
<div class="col-md-4 " style="">
<div class="card">
<ul style="list-style-type: none">
<li><img  src="data:image/jpeg;base64,${user.userPic}" alt="product image" style="max-height:200px; max-width:100%"></li>
<li><h2 style="color:gray;">${user.userName}</h2></li>
<li><button class="btn btn-primary" onclick="loadCurrentProfile(${user.userId})">update user</button></li>
</ul>
</div>
</div>
<div class="col-md-8" style="background: gray;">

<c:forEach var="order" items="${orders}">
<div class="card">
<div class="card-title"><strong><span>order id:#</span>${order.orderId}</strong></div>
<div class="card-body">
<c:forEach var="product" items="${order.products}">
<div class="row">
<div class="col-md-6">
<img  src="data:image/jpeg;base64,${product.imageData}" alt="product image" style="max-height:150px; max-width:50%">
</div>
<div class="col-md-6">	
		<p><strong><span>product id:#</span>${product.productId}</strong></p>
		<p>${product.productTitle}</p>
		<p><span>&#8377;</span>${product.productPrice}</p>
		<p><span>product quantity :</span>${product.productQuantity}</p>
		<p class="card-text"><m:short-description  description="${p.productDesc}"></m:short-description> </p>
		</div>
		</div>
		</c:forEach>
		
		<div class="card">
		<div class="card-title"><strong>Shipping Address</strong></div>
		<div class="card-body">
		<p>${order.name }</p>
		<p>${order.mobile }</p>
		<p>${order.address }</p>
		<p>${order.city }</p>
		<p>${order.state }</p>
		<p>${order.pincode }</p>
		</div>
		</div>
		</div>
		</div>
		</c:forEach>

</div>

</div>
<div class="modal fade" id="updateProfileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Update user</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="userUpdateForm">

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
     
    </div>
  </div>
</div>
</div>
</div>
<%@include file="component/common_modal.jsp" %>
</body>
</html>