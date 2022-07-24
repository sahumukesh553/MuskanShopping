<%@include file="component/securepage.jsp" %>
<html>
<head>
<%@include file="component/common.jsp" %>
<title>checkout page</title>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<%@include file="component/Message.jsp" %>
<div class="container main">
<div class="row">
<div class="col-md-6">
<div class="card mt-3">
<div class="cart-body"></div>
</div>
</div>
<div class="col-md-6">
<div class="card mt-3">
<form action="make-order" method="post" onsubmit="loadProducts()">
<input type="hidden" name="userId" value="${loggedInUser.userId}" >
<input type="hidden" name="products" id="orderedProducts" >

  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Name</label>
      <input type="text" class="form-control" id="inputEmail4" placeholder="Name" value="${loggedInUser.userName}" name="name" required>
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Mobile</label>
      <input type="text" class="form-control" id="inputPassword4" placeholder="mobile number" value="${loggedInUser.userPhone}" name="mobile" required>
    </div>
  </div>
  <div class="form-group">
    <label for="inputAddress">Address</label>
    <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" value="${loggedInUser.userAddress}"  name="address" required>
  </div>
 
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputCity">City</label>
      <input type="text" class="form-control" id="inputCity" name="city" required>
    </div>
    <div class="form-group col-md-4">
      <label for="inputState">State</label>
      <select id="inputState" class="form-control" name="state" required>
        <option selected>Choose...</option>
        <option value="Madhya Pradesh">MP</option>
         <option value="Uttar Pradesh">UP</option>
          <option value="Andhra Pradesh">AP</option>
      </select>
    </div>
    <div class="form-group col-md-2">
      <label for="inputZip">Pin Code</label>
      <input type="text" class="form-control" id="inputZip"  name="pincode"required >
    </div>
  </div>
  
  <button type="submit" class="btn btn-primary">order</button>
  <a href="home">shop more</a>
</form>
</div>
</div>
</div>
</div>
<%@include file="component/common_modal.jsp" %>
<script type="text/javascript">
function loadProducts(){
	let cart= localStorage.getItem("cart");
	let pcart=JSON.parse(cart);
	console.log(cart);
	$("#orderedProducts").val(cart);
	localStorage.removeItem("cart");
	showToast("your order placed successfully");
}
</script>
</body>
</html>