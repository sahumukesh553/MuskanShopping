<%@page import="com.muskan.shop.model.User"%>
<nav class="navbar navbar-expand-lg navbar-dark nav-bg-color">
 <div class="container">
  <a class="navbar-brand" href="home">MuskanShopping</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
     <% if(session.getAttribute("loggedInUser")==null) 
   {
	   %>
      <li class="nav-item active">
        <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
      </li>
      
   <%}else if(((User)session.getAttribute("loggedInUser")).getUserType().equals("admin"))
   {
	 %>
	    <li class="nav-item active">
        <a class="nav-link" href="admin.jsp"> Admin Home <span class="sr-only">(current)</span></a>
      </li>
      <% 
   }else{
	   %>
	    <li class="nav-item active">
        <a class="nav-link" href="normal.jsp"> User Home <span class="sr-only">(current)</span></a>
      </li>
      <% 
   }
     %>
      <li class="nav-item">
        <a class="nav-link" href="about.jsp">About</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Category
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
   </ul>
   <% if(session.getAttribute("loggedInUser")==null) 
   {
	   %>
   
      <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#" data-toggle="modal" data-target="#cart"><i class="fa fa-cart-plus" style="font-size:30px;"></i><span id="cart-items" style="font-size:20px;">( 0 )</span></a>
          
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="login.jsp">Login</a>
          
      </li>
      <li class="nav-item active">
      <a class="nav-link" href="register.jsp">Register</a>
      </li>
      </ul>
      <%}else{
    	  %>
    	 <ul class="navbar-nav ml-auto">
    	 <li class="nav-item active">
        <a class="nav-link" href="#" data-toggle="modal" data-target="#cart"><i class="fa fa-user" ></i>${loggedInUser.userName}</a>
          
      </li>
    	 <li class="nav-item active">
        <a class="nav-link" href="#" data-toggle="modal" data-target="#cart"><i class="fa fa-cart-plus" style="font-size:30px;"></i><span id="cart-items" style="font-size:20px;">( 0 )</span></a>
          
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="logout">Logout</a>
          
      </li>
       
      </ul>
      <% 
      }
   %>
  </div>
  </div>
</nav>