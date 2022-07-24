
<%@page import="com.muskan.shop.dao.CategoryService"%>
<%@page import="com.muskan.shop.dao.ProductService"%>
<%@page import="java.util.List" %>
<%@page import="com.muskan.shop.entity.*" %>	
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="WEB-INF/mytags.tld" prefix="m" %>
<%
int itemPerPage=5;
int begin=0;
int nofItems=5;
if(request.getParameter("pageNo")!=null)
{
nofItems=Integer.parseInt(request.getParameter("pageNo"))*itemPerPage;
 begin=nofItems-itemPerPage;
 }
%>
<html>
<head>
<%@include file="component/common.jsp" %>
<title>home</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<div class="container-fluid main">
<div class="row mt-3 mx-2">

<div class="col-md-2">
<div class="list-group mt-4" id="category-list" style="position:fixed;width:13%">

  <a href="${servletPath}?category=all" class="list-group-item list-group-item-action" id="All" onclick="makeActive('All')">
    All Products
  </a>

  
<c:forEach var="cat" items="${categories}"> 
<a href="${servletPath}?category=${cat.categoryId}" class="list-group-item list-group-item-action" id="category${cat.categoryId}" onclick="makeActive(${cat.categoryId})">${cat.categoryTitle}</a>
</c:forEach>
</div>
</div>

<div class="col-md-10">

<div class="row mt-4">
<%@include file="component/Message.jsp"%>


<div class="col-md-12">

<div class="card-columns">
<c:forEach var="p" items="${products}" begin="<%=begin%>" end="<%=nofItems%>">
<div class="products">
 <div class="product-card card">
 <div class="container text-center image-container">
    <img class="card-img-top mt-1" src="data:image/jpeg;base64,${p.imageData}" alt="${p.productTitle} image" onclick="findProductDetail(${p.productId})">
    </div>
    <div class="card-body">
      <h6 class="card-title">${p.productTitle}</h6>
      <p class="card-text"><m:short-description  description="${p.productDesc}"></m:short-description> </p>
      </div>
    <div class="card-footer">
    <button class="btn nav-bg-color" onclick="add_to_cart(${p.productId},'${p.productTitle}',${p.dicountedPrice})">add to cart</button>
    <button class="btn btn-success"><span>&#8377;</span>${p.dicountedPrice}</button>
    <span style="color:gray; text-decoration:line-through;">&#8377;${p.productPrice}</span>
    <span style="color:blue">&#8377;${p.discount}%off</span>
    </div>
    </div>
    
  
</div>
</c:forEach>
</div>
<footer>
<div class="pagination row mt-4">
<%
List<Product> allProduct=((List<Product>)request.getAttribute("products"));

int total=0;
if(allProduct!=null)
{
	total=allProduct.size();
}
if(total>6)
{
int pageNumber=1;
for(int i=1;i<total;i=i+6)
{
%>
<a href="${servletPath}?pageNo=<%=pageNumber%>" style="text-decoration: none !important;"><%=pageNumber%></a>
<% 

pageNumber++;
}
}
%>      
</div>
</footer>
</div>

</div>
</div>
 


</div>

</div>


<%@include file="component/common_modal.jsp" %>
</body>
</html>