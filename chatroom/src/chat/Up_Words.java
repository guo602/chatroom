package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataBase.dbfunction;

/**
 * Servlet implementation class Up_Words
 */
@WebServlet({ "/Up_Words", "/up_words" })
public class Up_Words extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Up_Words() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		PrintWriter out = response.getWriter();
		String name = new String();
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null && cookies.length != 0) {
//			for (int i = 0; i < cookies.length; i++) {
//				Cookie cookie = cookies[i];
//				if (cookie.getName().equals("name")) {
//					name = cookie.getValue();
//					name=URLDecoder.decode(name,"UTF-8");
//
//				}
//			}
//		}
		
		HttpSession session=request.getSession();
		name=(String)session.getAttribute("name");
		name=URLDecoder.decode(name,"UTF-8");
		System.out.println(name);
		
		String words=request.getParameter("words");
		
		
		dbfunction db=new dbfunction();
	    
		System.out.println(words);
		System.out.println(URLDecoder.decode(words,"UTF-8"));
		
	
		
	
		
    	Boolean result=db.insert_words(name, words);
    	if(result==true){	
    		out.println("发送成功");
			response.addHeader("refresh", "1;url=not_visit/write.html");
			
    	}
    	else{
    		out.println("发送失败");
			response.addHeader("refresh", "1;url=not_visit/write.html");
    	}
	}

}
