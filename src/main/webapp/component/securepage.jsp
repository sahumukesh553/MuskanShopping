<%
RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
if(session.getAttribute("loggedInUser")==null) 
{
	request.setAttribute("message", "please login first to access page");
	rd.forward(request, response);
} 
%>