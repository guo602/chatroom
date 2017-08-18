package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import dataBase.dbfunction;
/**
 * Servlet implementation class ValidateUser
 */
@WebServlet({ "/ValidateUser", "/validateuser" })
public class ValidateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		PrintWriter out = response.getWriter();
		
	    String name=new String(request.getParameter("name"));
	    String pass=new String(request.getParameter("pass"));
	    String check=new String(request.getParameter("way"));
	    String code=new String(request.getParameter("code"));
	    
	    System.out.println(check);
	    System.out.println(name);
	    System.out.println(pass);
	    System.out.println(check.equals("0"));
	    
		HttpSession session=request.getSession();
		Integer answer=(Integer)(session.getAttribute("answer"));
		
		boolean verify=false;
		if(Integer.parseInt(code)==answer.intValue()){
			verify=true;
		}
		else{
			verify=false;
		}
	    
	    if(check.equals("0")){
	    	
	    	dbfunction db=new dbfunction();
	    
	    	Boolean result=db.check_match(name, pass);
	    	//System.out.println(result);
	    	if(result==true&&verify==true){
	    		out.println("验证成功！欢迎进入小仙女聊天室");
	    		name=URLEncoder.encode(name,"UTF-8");
				//Cookie cookie = new Cookie("name",name);
				//cookie.setMaxAge(60*60*24);
				//response.addCookie(cookie);
	    		session.setAttribute("name", name);
				response.addHeader("refresh", "2;url=structure.html");
	    	}
	    	else{
	    		if(result==true){
				    out.println("验证码输入错误，请重新输入");
					out.println("<a href='login_check.html'>返回</a>");
					response.addHeader("refresh", "2;url=login_check.html");
				}
	    		else if(verify==true){
	    			out.println("签名或密码输入错误，请重新输入");
				    out.println("<a href='login_check.html'>返回</a>");
				    response.addHeader("refresh", "2;url=login_check.html");
	    		}
	    		else{
	    			out.println("签名，密码，验证码输入错误，请重新输入");
				    out.println("<a href='login_check.html'>返回</a>");
				    response.addHeader("refresh", "2;url=login_check.html");
	    		}
	    	}
	    }
	    else{
	    	System.out.println("error8");
	    	dbfunction db=new dbfunction();
	    	Boolean result=db.check_name(name);
	    	if(result==true&&verify==true){
	    		db.insert_in(name, pass);
	    		out.println("注册成功，欢迎进入小仙女聊天室");
	    		name=URLEncoder.encode(name,"UTF-8");
				//Cookie cookie = new Cookie("name",name);
				//cookie.setMaxAge(60*60*24);
				//response.addCookie(cookie);
	    		session.setAttribute("name", name);
				response.addHeader("refresh", "2;url=structure.html");	    	}
	    	else{
				if(result==true){
					out.println("验证码输入错误，请重新输入");
					out.println("<a href='login_check.html'>返回</a>");
					response.addHeader("refresh", "2;url=login_check.html");
				}
	    		else if(verify==true){
					out.println("用户名已存在，请重新输入");
					out.println("<a href='login_check.html'>返回</a>");
					response.addHeader("refresh", "2;url=login_check.html");
	    		}
	    		else{
	    			out.println("用户名已存在且验证码输入错误，请重新输入");
				    out.println("<a href='login_check.html'>返回</a>");
				    response.addHeader("refresh", "2;url=login_check.html");
	    		}
	    	}
	    }
	    
	}

}
