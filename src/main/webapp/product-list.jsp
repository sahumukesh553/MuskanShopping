<%@page import="com.muskan.shop.dao.CategoryService"%>
<%@include file="component/secureadmin.jsp" %>
<%@page import="com.muskan.shop.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.muskan.shop.dao.ProductService"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="WEB-INF/mytags.tld" prefix="m" %>
<%
ProductService productService=new ProductService();
List<Product> products=productService.getAllProduct();
CategoryService categoryService=new CategoryService(); 
%>
<html>
<head>
<%@include file="component/common.jsp" %>
<title>products list</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<%@include file="component/Message.jsp" %>
<table class='table mt-3 main'>
		<thead class='thead-light'>
		<tr>
		<th>product Id</th>
		<th>product Title</th>
		<th>product Description</th>
		<th>product Photo</th>
		<th>product Price</th>
		<th>product Discount</th>
		<th>product Quantity</th>
		<th>Category Id</th>
		<th>delete</th>
		<th>update</th>
		</tr>
		<c:forEach var="product" items="<%=products%>">
		<tr>
		<td>${product.productId}</td>
		<td>${product.productTitle}</td>
		<td>${product.productDesc}</td>
		<td> <img  src="data:image/jpeg;base64,${product.imageData}" alt="product image" style="max-height:200px; max-width:100%"></td>
		<td>${product.productPrice}</td>
		<td>${product.productDiscount}</td>
		<td>${product.productQuantity}</td>
		<td>${product.cid}</td>
		<td><button class="btn btn-danger delete-button"><a href="delete-product?id=${product.productId}" >delete</a></button></td>
		<td><button class="btn btn-success" onclick="loadCurrentProduct('${product.productId}','${product.productTitle}','${product.productPrice}','${product.productDiscount}','${product.productQuantity}','${product.productDesc}','${product.cid}')">update</button></td>
		</tr>
		</c:forEach>
		</thead>
		</table>
		
		<!-- update product modal start -->
		<div class="modal fade" id="updateProductModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Update product</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form action="update-product" method="post" enctype="multipart/form-data">
       <div class="form-group">
       <input type="hidden" name="productId" id="productId" class="form-control">
       </div>
 <div class="form-group">
    <label for="productTitle">Product Title</label>
    <input type="text" class="form-control"  placeholder="Enter Product Title" name="productTitle" id="productTitle" required>
  </div>
  <div class="form-group">
    <label for="productPrice">Product price</label>
    <input type="number" class="form-control"  placeholder="Enter Product Price" name="productPrice" id="productPrice" required>
  </div>
  <div class="form-group">
    <label for="productQuantity">Product Quantity</label>
    <input type="number" class="form-control"  placeholder="Enter Product Quantity" name="productQuantity" id="productQuantity" required>
  </div>
  <div class="form-group">
    <label for="productDiscount">Product Discount</label>
    <input type="number" class="form-control"  placeholder="Enter Product Discount" name="productDiscount" id="productDiscount" required>
  </div>
   <div class="form-group">
    <label for="productCategory">Product Category</label>
    <select class="form-control" name="productCategory" id="productCategory" required>
     <c:forEach var="cat" items="<%=categoryService.getAllCategory()%>">
    	  <option value="${cat.getCategoryId()}" id="catid${cat.getCategoryId()}">${cat.getCategoryTitle()}</option>
    	  </c:forEach> 
    	   </select>
    	   </div>
  <div class="form-group">
    <label for="productPhoto">Product picture</label>
    <input type="file" class="form-control-file"  name="productPhoto" id="productPhoto">
  </div>
  <div class="form-group">
  <label for="productDesc">Product Description</label>
    <textarea class="form-control"  rows="2" name="productDesc" id="productDesc" required></textarea>
  </div>
  <button type="submit" class="btn btn-outline-success">update Product</button>
    <button type="reset" class="btn btn-outline-warning">Reset</button>
</form>

      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
     
    </div>
  </div>
</div>
</div>

<%@include file="component/common_modal.jsp" %>
</body>
</html>