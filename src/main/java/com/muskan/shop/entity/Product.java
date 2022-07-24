package com.muskan.shop.entity;

import java.io.InputStream;

public class Product {
	private int productId ;
	private String productTitle ;
	private String productDesc ;
	private InputStream productPhoto ;
	private int productPrice ;
	private int productDiscount ;
	private int productQuantity ;
	private int cid ;
	private String imageData;
	private int dicountedPrice;
	private int discount;
	
	public int getDicountedPrice() {
		dicountedPrice=productPrice-productDiscount;
		return dicountedPrice;
	}
	
	public int getDiscount() {
		 this.discount = 100 * (productPrice - getDicountedPrice()) / productPrice;
		return discount ;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public InputStream getProductPhoto() {
		return productPhoto;
	}
	public void setProductPhoto(InputStream productPhoto) {
		this.productPhoto = productPhoto;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(int productDiscount) {
		this.productDiscount = productDiscount;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public String getImageData() {
		return imageData;
	}
	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	@Override
	public String toString() {
		return "{ productId :" + productId + ", productTitle :" + productTitle + ", productDesc :" + productDesc
				+ ", productPrice :" + productPrice + ", productDiscount :"
				+ productDiscount + ", productQuantity :" + productQuantity + ", cid :" + cid 
				+ "}";
	}
	
	
}
