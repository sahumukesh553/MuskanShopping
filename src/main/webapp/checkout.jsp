<%@include file="component/securepage.jsp" %>
<html>
<head>
<%@include file="component/common.jsp" %>
<title>checkout page</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<div class="row">
<div class="col-md-6">
<div class="card mt-3">
<div class="cart-body"></div>
</div>
</div>
<div class="col-md-6">
<div class="card mt-3">
<form>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Name</label>
      <input type="email" class="form-control" id="inputEmail4" placeholder="Name" value="${loggedInUser.userName}">
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Mobile</label>
      <input type="text" class="form-control" id="inputPassword4" placeholder="mobile number" value="${loggedInUser.userPhone}">
    </div>
  </div>
  <div class="form-group">
    <label for="inputAddress">Address</label>
    <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" value="${loggedInUser.userAddress}">
  </div>
  <div class="form-group">
    <label for="inputAddress2">Address 2</label>
    <input type="text" class="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor">
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputCity">City</label>
      <input type="text" class="form-control" id="inputCity">
    </div>
    <div class="form-group col-md-4">
      <label for="inputState">State</label>
      <select id="inputState" class="form-control">
        <option selected>Choose...</option>
        <option>...</option>
      </select>
    </div>
    <div class="form-group col-md-2">
      <label for="inputZip">Zip</label>
      <input type="text" class="form-control" id="inputZip">
    </div>
  </div>
  
  <button type="submit" class="btn btn-primary">order</button>
  <a href="home">shop more</a>
</form>
</div>
</div>
</div>
<%@include file="component/common_modal.jsp" %>
</body>
</html>