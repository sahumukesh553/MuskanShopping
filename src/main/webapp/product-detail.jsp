<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="WEB-INF/mytags.tld" prefix="m" %>
<html>
<head>
<%@include file="component/common.jsp" %>
<title>product-detail</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<%@include file="component/Message.jsp" %>
<div class="container main">
<div class="row mt-5">
<div class="col-md-4 mx-5">
<img  src="data:image/jpeg;base64,${product.imageData}" alt="product image" style="max-height:200px; max-width:100%">
</div>
<div class="col-md-6 mx-3">
<h3 style="color:#673ab7!important">${product.productTitle}</h3>
<h4 style="color:green"><span>&#8377;</span>${product.dicountedPrice}</h4>
<p><span style="color:gray; text-decoration:line-through;">&#8377;${product.productPrice}</span></p>
<p style="color:blue">&#8377;${product.discount}%off</p>
<p style="color:blue">${product.productDesc}</p>
<button class="btn nav-bg-color" onclick="add_to_cart(${product.productId},'${product.productTitle}',${product.dicountedPrice})">add to cart</button>
</div>
</div>
</div>
<%@include file="component/common_modal.jsp" %>
</body>
</html>