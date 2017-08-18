<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

全部用户昵称列表 ：
	<br>
	<p></p>

<%
    response.setContentType("text/html");
    out.print("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><body>");
    out.print("<table>");
	dbfunction db = new dbfunction();
	ArrayList<User_show> temp1 = db.GetAllUser();
	int i=0;
	for (User_show user1 : temp1) {
%>
	
<%
        if(i%2==0){
           out.print("<tr>");
        }
		String name = user1.getUserName();
		if (db.img_is_in(name)) {
			byte[] b;
			b = db.ShowImg(name);
			System.out.println("begin" + name);
			
			out.print("<td><img width=40 height=40 src='ShowImg?name="+ name + "'></td>");
			System.out.println("end");
				// out.print("</body></head></html>");
			} 
		else {
			out.print("<td><img width=40 height=40 src='img/sky93.jpg'></td>");
				//out.print("</body></head></html>");
		}
        out.print("<td>"+user1.getUserName()+"</td><td>(o°ω°o)</td>");
        if(i%2==1){
            out.print("</tr>");
        }
        i++;
	}
	out.print("</table>");
	out.print("</body></head></html>");
%>



</body>
</html>