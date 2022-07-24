package com.muskan.shop.model;

public class ApplicationError {
	private String message;
	private	Throwable throwable;
	private Integer statusCode;
	private String servletName;
	private String requestUri;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	@Override
	public String toString() {
		return "ApplicationError [message=" + message + ", statusCode=" + statusCode + ", servletName=" + servletName
				+ ", requestUri=" + requestUri + ", throwable=" + throwable + "]";
	}
	

}
