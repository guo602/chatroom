<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<%@page import="dataBase.dbfunction"%>
<%@page import="java.io.InputStream"%>
<%@ page import="java.util.*"%>
<%@ page import="model.OnlineUser"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.ServletOutputStream"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%
    dbfunction db = new dbfunction();
	//out.print("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><body>");
	
	session = request.getSession();
	String name = (String) session.getAttribute("name");
	if (name != null) {
		name = URLDecoder.decode(name, "UTF-8");
		//out.print(name);
	}
	out.print(name+"的资料<br>");
	if (db.img_is_in(name)) {
		    byte[] b;
			b = db.ShowImg(name);
			System.out.println("begin"+name);
			out.print("<img width=110 height=80 src='ShowImg?name="+name+"'><p></p>");
			System.out.println("end");
	       // out.print("</body></head></html>");
	 }
	else{
		out.print("<img width=110 height=80 src='img/sky93.jpg'><p></p>");
		//out.print("</body></head></html>");
	}
%>
	<form name="form1" id="form1" action="upload.html" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>选择头像：<input type="submit" value="修改资料"></td>
			</tr>
			<tr>
				<td><input type="file" name="face_icon" id="face_icon" value="浏览文件夹"></td>
			</tr>

		</table>
	</form>
</body>
</html>