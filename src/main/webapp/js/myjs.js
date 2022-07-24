function minus_to_cart(pid)
{
let cart= localStorage.getItem("cart");
		let pcart=JSON.parse(cart);
		let oldProduct=pcart.find((item)=>item.productId==pid);
		if(oldProduct)
		{
if(oldProduct.productQuantity!=1)
{
			oldProduct.productQuantity=oldProduct.productQuantity-1;
			pcart.map((item)=>{
				if(item.productId == oldProduct.productId)
				{
					item.productQuantity=oldProduct.productQuantity;
				}
			});
			localStorage.setItem("cart",JSON.stringify(pcart));
			showToast(oldProduct.productName+" quantity decreased by : "+oldProduct.productQuantity);
			console.log("product quantity decreased");
}else{
showToast(oldProduct.productName+" quantity can not decreased further ");
			console.log("product quantity can not decreased");
}
		}
update_cart();
}


function add_to_cart(pid,pname,price)
{
	let cart= localStorage.getItem("cart");
	if(cart == null)
	{
		let products=[];
		let product={
			productId: pid,
			productName: pname,
			productPrice: price,
			productQuantity:1
		};
		products.push(product);
		localStorage.setItem("cart",JSON.stringify(products));
		console.log(" first product added");
		showToast("product added to cart");
	}else
	{
		let pcart=JSON.parse(cart);
		let oldProduct=pcart.find((item)=>item.productId==pid);
		if(oldProduct)
		{
			oldProduct.productQuantity=oldProduct.productQuantity+1;
			pcart.map((item)=>{
				if(item.productId == oldProduct.productId)
				{
					item.productQuantity=oldProduct.productQuantity;
				}
			});
			localStorage.setItem("cart",JSON.stringify(pcart));
			showToast(oldProduct.productName+" quantity incresed by : "+oldProduct.productQuantity);
			console.log("product quantity increased");
		}else{
			let product={
			productId: pid,
			productName: pname,
			productPrice: price,
			productQuantity:1
		};
		pcart.push(product);
		localStorage.setItem("cart",JSON.stringify(pcart));
		console.log("new product added ");
		showToast("product added to cart");
		}
		
	}
	update_cart();
}

function update_cart()
{
	let cartString=localStorage.getItem("cart");
	let cart=JSON.parse(cartString);
	if(cart==null || cart.length==0)
	{
		console.log("cart is Empty");
		$("#cart-items").html("( 0 )");
		$(".cart-body").html("<h3>Cart does not have any items </h3>");
		$(".checkout-btn").addClass('disabled');
		
	}else
	{
		console.log("new product added to cart");
		$("#cart-items").html(`( ${cart.length} )`);
		$(".checkout-btn").removeClass('disabled');
		let table=`
		<table class='table'>
		<thead class='thead-light'>
		<tr>
		<th>product-name</th>
		<th>price</th>
		<th>Quantity</th>
		<th>Total price</th>
		<th>action</th>
		</tr>
		</thead>
		`;
		let totalPrice=0;
		cart.map((item)=>{
			table+=`
			<tr>
			<td> ${item.productName} </td>
			<td> ${item.productPrice} </td>
			<td><input type='button' value='-'  field='quantity' style="font-weight: bold;"  class="btn btn-primary" onclick="minus_to_cart(${item.productId})" /> 
			${item.productQuantity} 
			<input type='button' value='+'  field='quantity' style="font-weight: bold;" class="btn btn-primary" onclick="add_to_cart(${item.productId},'${item.productName}',${item.productPrice})"/>
			</td>
			<td> ${item.productQuantity*item.productPrice} </td>
			<td>
			<buttton class='btn btn-danger' onclick='deleteFromCart(${item.productId})'>delete</button>
			</td>
			</tr>
			`;
			totalPrice+=item.productQuantity*item.productPrice;
		})
		table=table+`
		<tr><td colspan=5 class='text-right font-weight-bold md-5'>Total price : ${totalPrice}</td></tr>
		</table>`;
		$(".cart-body").html(table);
		
	}
}

function makeActive(catId)
{
sessionStorage.setItem("active-category",catId);
}

function activate()
{let catId=sessionStorage.getItem("active-category");
	if(catId==null || catId=='All')
	{
	$('#All').addClass('active');	
	}else{
$(`#category${catId}`).addClass('active');
}
} 

function deleteFromCart(pid)
{
	let cart=JSON.parse(localStorage.getItem("cart"));
	let newCart=cart.filter((item)=>item.productId != pid);
	localStorage.setItem("cart",JSON.stringify(newCart));
	showToast("item deleted from cart");
	update_cart();
}

$(document).ready(function()
{activate();
update_cart();
});

function showToast(content)
{console.log("toast called");
	$("#toast").addClass("display");
	$("#toast").html(content);
	setTimeout(()=>{
		$("#toast").removeClass("display");
	},2000);
}

function Checkout()
{
	window.location='checkout.jsp';
}

function gotoListItems(page){
	window.location=page;
}


function loadCurrentUser(id,userType)
{
	$.ajax({
		url: "get-user",
		method:'post',
		data:{userId:id},
		success:function(result){
			$("#userUpdateForm").html(result);
			$("#"+userType).attr("selected","selected");
		}});
		
	$("#updateUserModal").modal("show");
}
function loadCurrentProduct(productId,productTitle,productPrice,productDiscount,productQuantity,productDesc,cid)
{
	
	$("#catid"+cid).attr("selected","selected");
	$("#productId").val(productId);
	$("#productTitle").val(productTitle);
	$("#productPrice").val(productPrice);
	$("#productDiscount").val(productDiscount);
	$("#productQuantity").val(productQuantity);
	$("#productDesc").val(productDesc);
	
	
		
	$("#updateProductModal").modal("show");
}
function loadCurrentCategory(id)
{
	$.ajax({
		url: "get-category",
		method:'post',
		data:{categoryId:id},
		success:function(result){
			$("#categoryUpdateForm").html(result);
		}});
	$("#updateCategoryModal").modal("show");
}
function validatePhone(){
	phone=$("#userPhone").val();
	console.log(phone);
	$.ajax({
		url: "validate",
		method:'post',
		data:{userPhone:phone},
		success:function(result){
			$("#phoneValidation").html(result);
		}});
	
}
function validateEmail(){
	email=$("#userEmail").val();
	console.log(email);
	$.ajax({
		url: "validate",
		method:'post',
		data:{email:email},
		success:function(result){
			$("#emailValidation").html(result);
		}});
	
}
function findProductDetail(id){
	window.location='product-detail?productId='+id;
}
function loadCurrentProfile(id)
{
	$.ajax({
		url: "profile-update",
		method:'post',
		data:{userId:id},
		success:function(result){
			$("#userUpdateForm").html(result);
		}});
		
	$("#updateProfileModal").modal("show");
}
function mobileMode(){
	$("nav").removeClass("navigation");
}