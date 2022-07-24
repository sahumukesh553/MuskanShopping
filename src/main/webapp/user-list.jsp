<%@include file="component/secureadmin.jsp" %>
<%@page import="java.util.List"%>
<%@page import="com.muskan.shop.dao.UserService"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="WEB-INF/mytags.tld" prefix="m" %>
<%
UserService userService=new UserService();
List<User> users=userService.getAllUser();
%>
<html>
<head>
<%@include file="component/common.jsp" %>
<title>users list</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<%@include file="component/Message.jsp" %>
<table class='table mt-3 main'>
		<thead class='thead-light'>
		<tr>
		<th>User Id</th>
		<th>User Name</th>
		<th>User Email</th>
		<th>User Pic</th>
		<th>User Mobile</th>
		<th>User Address</th>
		<th>User Type</th>
		<th>delete</th>
		<th>update</th>
		</tr>
		<c:forEach var="user" items="<%=users%>"> 
		<tr>
		<td>${user.userId }</td>
		<td>${user.userName }</td>
		<td>${user.userEmail}</td>
		<td> <img  src="data:image/jpeg;base64,${user.userPic}" alt="user image" style="max-height:200px; max-width:100%"></td>
		<td>${user.userPhone}</td>
		<td>${user.userAddress}</td>
		<td>${user.userType}</td>
		<td><button class="btn btn-danger delete-button"><a href="delete-user?id=${user.userId}" >delete</a></button></td>
		<td><button class="btn btn-success" id="updateUserButton"  onclick="loadCurrentUser(${user.userId},'${user.userType}')">update</button></td>
		</tr>
		</c:forEach>
		</thead>
		</table>
		<!-- update user modal start -->
<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
		
		
<%@include file="component/common_modal.jsp" %>
</body>
</html>