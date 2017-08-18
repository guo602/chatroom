package chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Vali_Code
 */
@WebServlet({ "/Vali_Code", "/vali_code" })
public class Vali_Code extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Vali_Code() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		//以下三行功能相同，表示图片是一次性，不需要存储
		response.addHeader("Cache_Control", "no-cache");
		response.addHeader("Pragma", "no-cache");
		response.addDateHeader("Expires", 0);
		
		BufferedImage img=new BufferedImage(55,25,BufferedImage.TYPE_INT_BGR);
		Graphics g=img.getGraphics();
		
		Random r=new Random();
		
		//Color c=new Color(138,138,138);
		int red=r.nextInt(256);
	    int green=r.nextInt(256);
	    int blue=r.nextInt(256);
	    g.setColor(new Color(red,green,blue));
		g.fillRect(0, 0, 55, 25);

	    int a=r.nextInt(10);
	    int b=r.nextInt(10);
	    int answer = 0;
	    int sign=r.nextInt(3);
	    String s=new String();
	    if(sign==0){
	        s="+";
	        answer=a+b;
	    }
	    else if(sign==1){
	    	s="-";
	    	answer=a-b;
	    }
	    else if(sign==2){
	    	s="*";
	    	answer=a*b;
	    }
	    
	   
	    HttpSession session=request.getSession();
	    session.setAttribute("answer", answer);
	    
	    //g.setColor(new Color(255,255,0));
	    g.setFont(new Font("宋体",Font.BOLD,20));
	    
	    for(int i=0;i<10;i++){
	       red=r.nextInt(256);
	       green=r.nextInt(256);
	       blue=r.nextInt(256);
	       g.setColor(new Color(red,green,blue));
	       //直线
	       //g.drawLine(r.nextInt(50), r.nextInt(20), r.nextInt(50), r.nextInt(20));
	       //弧线
	       int startAngle=r.nextInt(360);
	       int arcAngle=r.nextInt(360);
	       g.drawArc(r.nextInt(50), r.nextInt(25), 50, 20, startAngle, arcAngle);
	    }
	    
	    g.drawString(a+s+b+"=", 5, 20);
	    ImageIO.write(img, "jpeg", response.getOutputStream());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
