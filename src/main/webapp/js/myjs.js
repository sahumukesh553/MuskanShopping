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
var request;  
function sendInfo( url)  
{  
console.log(url);
  
if(window.XMLHttpRequest){  
request=new XMLHttpRequest();  
}  
else if(window.ActiveXObject){  
request=new ActiveXObject("Microsoft.XMLHTTP");  
}  
  
try  
{  
request.onreadystatechange=getInfo;  
request.open("GET",url,true);  
request.send();  
}  
catch(e)  
{  
alert("Unable to connect to server");  
}  
} 
function getInfo(){  
if(request.readyState==4){  
var val=request.responseText;  
console.log(val);
}  
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
			<td> ${item.productQuantity} </td>
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
localStorage.setItem("active-category",catId);
}
function activate()
{let catId=localStorage.getItem("active-category");
	if(catId==null)
	{
	$('#All').addClass('active');	
	}else{
$(`#${catId}`).addClass('active');
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