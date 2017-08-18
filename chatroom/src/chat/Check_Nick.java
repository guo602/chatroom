package chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.dbfunction;

/**
 * Servlet implementation class Check_nick
 */
@WebServlet({ "/Check_nick", "/check_nick" })
public class Check_Nick extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_Nick() {
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
		
		String name=request.getParameter("name");
		
		System.out.println("name");
		dbfunction db=new dbfunction();
    	Boolean result=db.check_name(name);
    	String answer=new String();
    	if(result==true){
    		answer="1";
    	}
    	else{
    		answer="0";
    	}
    	System.out.println(answer);
    	out.print(answer);
	}

}
