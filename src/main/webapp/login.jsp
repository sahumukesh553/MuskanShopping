
<%

if(session.getAttribute("loggedInUser")!=null) 
{
	if(((User)session.getAttribute("loggedInUser")).getUserType().equalsIgnoreCase("normal"))
	{
		response.sendRedirect("normal.jsp");
	}else{
		response.sendRedirect("admin.jsp");
	}
}
%>
<html>
<head>
<%@include file="component/common.jsp" %>
<title>login page</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<div class="container-fluid main" style="position:fixed">
<div class="row">
<div class="col-md-5 offset-md-3">
<div class="card mt-4">
<div class="card-header nav-bg-color text-white">
<%@include file="component/Message.jsp" %>
<h4>login form</h4>
</div>
<div class="card-body">
<form action="login" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="userEmail" required>
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="userPassword" required>
  </div>
  <div class="container text-center">
    <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</form>
</div>

</div>
</div>
</div>
</div>
<%@include file="component/common_modal.jsp" %>
</body>
</html>
