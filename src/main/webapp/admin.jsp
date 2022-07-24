<%@include file="component/secureadmin.jsp" %>
<%@page import="com.muskan.shop.dao.*" %>
<%@page import="com.muskan.shop.entity.*" %>
<%!  
CategoryService categoryService=new CategoryService();
UserService userService=new UserService();
ProductService productService=new ProductService();
%>  
<html>
<head>
<%@include file="component/common.jsp" %>
<style type="text/css">
.admin .card{
	border:1px solid #673ab7;
	color: blue !important;
}
.admin .card:hover{
background:#e2e2e2;
cursor: pointer; 
}
</style>
<title>admin page</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<%@include file="component/Message.jsp" %>
<div class="container admin main">

<div class="row mt-3">

<div class="col-md-4">
<div class="card" onclick="gotoListItems('user-list.jsp')">
<div class="card-body">
<div class="container">
<img  style="max-width:100px" alt="user-icon" src="images/users.png" class="img-fuild rounded-circle">
</div>
<h2><%= userService.getUserCount() %></h2>
<h1 class="text-uppercase text-muted">users</h1>
</div>
</div>
</div>

<div class="col-md-4">
<div class="card" onclick="gotoListItems('category-list.jsp')">
<div class="card-body">
<div class="container">
<img  style="max-width:100px" alt="categories-icon" src="images/categories.png" class="img-fuild rounded-circle">
</div>
<h2><%= categoryService.getCategoryCount() %></h2>
<h1 class="text-uppercase text-muted">categories</h1>
</div>
</div>
</div>

<div class="col-md-4">
<div class="card" onclick="gotoListItems('product-list.jsp')">
<div class="card-body">
<div class="container">
<img  style="max-width:100px" alt="products-icon" src="images/products.png" class="img-fuild rounded-circle"><a href="product-list.jsp"></a>
</div>
<h2><%= productService.getProductCount() %></h2>
<h1 class="text-uppercase text-muted">products</h1>
</div>
</div>
</div>

</div>
<!-- Second row -->
<div class="row mt-3">

<div class="col-md-6">
<div class="card" data-toggle="modal" data-target="#addCategoryModal">
<div class="card-body">
<div class="container">
<img  style="max-width:100px" alt="add-category-icon" src="images/plus.png" class="img-fuild rounded-circle">
</div>
<p>click here to add new category</p>
</div>
</div>
</div>
<div class="col-md-6">
<div class="card" data-toggle="modal" data-target="#addProductModal">
<div class="card-body">
<div class="container">
<img  style="max-width:100px" alt="add-product-icon" src="images/plus.png" class="img-fuild rounded-circle">
</div>
<p>click here to add new product</p>
</div>
</div>
</div>

</div>

</div>
<!-- add category modal starts -->
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add new category</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form action="addCategory" method="post">
       <div class="form-group">
    <label for="exampleFormControlInput1">Category Title</label>
    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Enter Category Title" name="categoryTitle" required>
  </div>
       <div class="form-group">
  <label for="exampleFormControlTextarea7">Category Description</label>
    <textarea class="form-control" id="exampleFormControlTextarea7" rows="2" name="categoryDescription" required></textarea>
  </div>
  <button type="submit" class="btn btn-outline-success">Add Category</button>
    <button type="reset" class="btn btn-outline-warning">Reset</button>
</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        
      </div>
    </div>
  </div>
</div>
<!-- add category modal end -->

<!-- add product modal start -->
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add new product</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form action="addProduct" method="post" enctype="multipart/form-data">
 <div class="form-group">
    <label for="exampleFormControlInput1">Product Title</label>
    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Enter Product Title" name="productTitle" required>
  </div>
  <div class="form-group">
    <label for="exampleFormControlInput2">Product price</label>
    <input type="number" class="form-control" id="exampleFormControlInput2" placeholder="Enter Product Price" name="productPrice" required>
  </div>
  <div class="form-group">
    <label for="exampleFormControlInput3">Product Quantity</label>
    <input type="number" class="form-control" id="exampleFormControlInput3" placeholder="Enter Product Quantity" name="productQuantity" required>
  </div>
  <div class="form-group">
    <label for="exampleFormControlInput4">Product Discount</label>
    <input type="number" class="form-control" id="exampleFormControlInput4" placeholder="Enter Product Discount" name="productDiscount" required>
  </div>
   <div class="form-group">
    <label for="exampleFormControlSelect5">Product Category</label>
    <select class="form-control" id="exampleFormControlSelect5" name="productCategory" required>
      <% for(Category cat:categoryService.getAllCategory())
    	  {
    	  %>
    	  <option value="<%=cat.getCategoryId()%>"><%=cat.getCategoryTitle() %></option>
    	  
    	  <%
    	  
    	  }
    	  %>
    </select>
  </div>
  <div class="form-group">
    <label for="exampleFormControlFile6">Product Pic</label>
    <input type="file" class="form-control-file" id="exampleFormControlFile6" name="productPhoto" required>
  </div>
  <div class="form-group">
  <label for="exampleFormControlTextarea7">Product Description</label>
    <textarea class="form-control" id="exampleFormControlTextarea7" rows="2" name="productDesc" required></textarea>
  </div>
  <button type="submit" class="btn btn-outline-success">Add Product</button>
    <button type="reset" class="btn btn-outline-warning">Reset</button>
</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
     
    </div>
  </div>
</div>
</div>
<!-- add product modal end -->
<%@include file="component/common_modal.jsp" %>
</body>
</html>