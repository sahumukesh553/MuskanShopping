<html>
<head>
<%@include file="component/common.jsp" %>
<title>New User Registration</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<div class="container-fluid main">
<div class="row">
<div class="col-md-4 offset-md-4">
<div class="card">
<%@include file="component/Message.jsp" %>
<div class="card-body px-5">
<div class="text-center"><img alt="signup" src="images/signup.jpg" height="50px" width="50px"></div>
<h3 class="text-center mt-2">Sign up Form</h3>
<form action="register" method="post" enctype="multipart/form-data">
 <div class="form-group">
    <label for="exampleFormControlInput1">Name</label>
    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Enter your name" name="userName" required="required">
  </div>
  <div class="form-group">
    <label for="userEmail">Email address</label>
    <input type="email" class="form-control" id="userEmail" placeholder="name@example.com" name="userEmail" onblur="validateEmail()" required="required">
    <small id="emailValidation"></small>
  </div>
   <div class="form-group">
    <label for="exampleInputPassword3">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword3" placeholder="Password" name="userPassword" required="required">
  </div>
  <div class="form-group">
    <label for="userPhone">mobile number</label>
    <input type="tel" class="form-control" id="userPhone" placeholder="Enter mobile number" name="userPhone" onblur="validatePhone()" required="required">
    <small id="phoneValidation"></small>
  </div>
  <div class="form-group">
    <label for="exampleFormControlFile5">profile pic</label>
    <input type="file" class="form-control-file" id="exampleFormControlFile5" name="userPic">
  </div>
  <div class="form-group">
  <label for="exampleFormControlTextarea6">address</label>
    <textarea class="form-control" id="exampleFormControlTextarea6" rows="2" name="userAddress" required></textarea>
  </div>
  <button type="submit" class="btn btn-outline-success">Register</button>
    <button type="reset" class="btn btn-outline-warning">Reset</button>
</form>
</div>
</div>
</div>
 </div>
 </div>
 <%@include file="component/common_modal.jsp" %>
</body>
</html>