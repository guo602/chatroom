<%@page import="model.History_show"%>
<%@page import="dataBase.dbfunction"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="2" charset=UTF-8">
<title>历史记录</title>
</head>
<body>

<table>
<tr><td></td><td>历史记录列表 ：</td><td></td>
<%
dbfunction db = new dbfunction();
ArrayList<History_show> temp = db.GetAllWord();
for(History_show user : temp) {
		
%>
	<tr>
	<td><div style="color: #8600FF; font-size: 20px;"><% out.print(user.getName()); %>:</div></td>
	<td><% out.print(user.getTime()); %></td>
	</tr>
	<tr>
	<td><% out.print(user.getWord()); %></td>
	</tr>
<%
	}
%>

</table>


</body>

</html>