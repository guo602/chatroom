<%@page import="model.User_show"%>
<%@page import="dataBase.dbfunction"%>
<%@page import="java.util.*"%>
<%@page import="model.OnlineUser"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="java.net.URLDecoder"%>

<%@page import="java.io.InputStream"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.ServletOutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="60" charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	在线 用户昵称列表 ：
	<br>
	<p></p>

<%
	ArrayList<OnlineUser> users = (ArrayList<OnlineUser>) application.getAttribute("OnlineUsers");
	if (users == null) {
		out.print("没有人的聊天室/(ㄒoㄒ)/~~");
	} 
	else {
		for (OnlineUser user : users) {
			String name=user.getName();
			name = URLDecoder.decode(name, "UTF-8");
			System.out.println("users.jsp"+name);
%>
	
小仙女 :<%out.print(name);%>进入聊天室&nbsp;&nbsp;(o°ω°o)<br>
<%
	     }
	}
%>


	<p></p>


</body>

</html>