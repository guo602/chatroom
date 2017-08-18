package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		arg0.setCharacterEncoding("UTF-8");
		arg1.setCharacterEncoding("UTF-8");
		String s = arg0.getRemoteAddr();
		//System.out.println(s);
		
		
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		PrintWriter out=response.getWriter();
		HttpSession  session = request.getSession();
		//System.out.println("error1");
		if(session.getAttribute("name") == null){
			//System.out.println(session+"11111111111111111111");
			String temp = request.getRequestURI();
			if(temp.equals("/chatroom/login_check.html")||temp.equals("/chatroom/Vali_Code")) {
				//System.out.println(temp+"22222222222222222222");
				//System.out.println("error2");
				arg2.doFilter(arg0, arg1);
			}
			else {	
				//System.out.println("error3");
				response.addHeader("refresh", "2;url=login_check.html");
				response.setContentType("text/html");
				out.print("未登录用户不可访问"+"<a href='login_check.html'>返回登录界面</a>");
			    
				//response.sendRedirect("login_check.html");
			}
			
		}
		else {
			arg2.doFilter(arg0, arg1);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
