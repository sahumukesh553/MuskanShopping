<%@page import="com.muskan.shop.dao.CategoryService"%>
<%@page import="com.muskan.shop.dao.ProductService"%>
<%@page import="java.util.List" %>
<%@page import="com.muskan.shop.model.*" %>	
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="WEB-INF/mytags.tld" prefix="m" %>
<html>
<head>
<%@include file="component/common.jsp" %>
<title>home</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<div class="container-fluid">
<div class="row mt-3 mx-2">

<div class="col-md-2">
<div class="list-group mt-4" id="category-list">

  <a href="category-product?category=all" class="list-group-item list-group-item-action" id="All" onclick="makeActive('All')">
    All Products
  </a>

  
<c:forEach var="cat" items="${categories}"> 
<a href="category-product?category=${cat.categoryId}" class="list-group-item list-group-item-action" id="category${cat.categoryId}" onclick="makeActive('category${cat.categoryId}')">${cat.categoryTitle}</a>
</c:forEach>
</div>
</div>

<div class="col-md-10">

<div class="row mt-4">
<%@include file="component/Message.jsp"%>
<div class="col-md-12">
<div class="card-columns">
<c:forEach var="p" items="${products}">
<div class="products">
 <div class="product-card card">
 <div class="container text-center image-container">
    <img class="card-img-top mt-1" src="data:image/jpeg;base64,${p.imageData}" alt="Card image cap">
    </div>
    <div class="card-body">
      <h6 class="card-title">${p.productTitle}</h6>
      <p class="card-text"><m:short-description  description="${p.productDesc}"></m:short-description> </p>
      </div>
    <div class="card-footer">
    <button class="btn nav-bg-color" onclick="add_to_cart(${p.productId},'${p.productTitle}',${p.productPrice})">add to cart</button>
    <button class="btn btn-success"><span>&#8377;</span>${p.productPrice}</button>
    </div>
    </div>
    
  
</div>
</c:forEach>
</div>
</div>
</div>
</div>
 


</div>

</div>
<%@include file="component/common_modal.jsp" %>
</body>
</html>