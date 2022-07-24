<%@include file="component/secureadmin.jsp" %>
<%@page import="com.muskan.shop.entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.muskan.shop.dao.CategoryService"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="WEB-INF/mytags.tld" prefix="m" %>
<%
CategoryService categoryService2=new CategoryService();
List<Category> categories=categoryService2.getAllCategory();
%>
<html>
<head>
<%@include file="component/common.jsp" %>
<title>categories list</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<%@include file="component/Message.jsp" %>
<table class='table mt-4 main'>
		<thead class='thead-light'>
		<tr>
		<th>Category Id</th>
		<th>Category Title</th>
		<th>Category Description</th>
		<th>delete</th>
		<th>update</th>
		</tr>
		<c:forEach var="category" items="<%=categories%>">
		<tr>
		<td>${category.categoryId}</td>
		<td>${category.categoryTitle}</td>
		<td>${category.categoryDescription}</td>
		<td><button class="btn btn-danger delete-button"><a href="delete-category?id=${category.categoryId}" >delete</a></button></td>
		<td><button class="btn btn-success" onclick="loadCurrentCategory(${category.categoryId})">update</button></td>
		</tr>
		</c:forEach>
		</thead>
		</table>
		
		<!-- update category modal start -->
<div class="modal fade" id="updateCategoryModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Update category</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="categoryUpdateForm">
      
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