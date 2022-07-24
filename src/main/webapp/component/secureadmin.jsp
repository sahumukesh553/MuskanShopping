<%@page import="com.muskan.shop.entity.User"%>
<%
RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
if(session.getAttribute("loggedInUser")==null) 
{
	request.setAttribute("message", "please login first to access page");
	rd.forward(request, response);
}else
{
	if(((User)session.getAttribute("loggedInUser")).getUserType().equalsIgnoreCase("normal"))
	{
		request.setAttribute("message", "you are not admin please login with admin account to access this page");
		rd.forward(request, response);
	}
}
%>