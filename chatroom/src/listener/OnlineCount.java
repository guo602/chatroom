/**
 * 
 */
package listener;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.catalina.core.ApplicationContext;

import model.OnlineUser;

/**
 * @author lenovo
 *
 */
public class OnlineCount implements HttpSessionAttributeListener{

	
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
    	ServletContext application = session.getServletContext();
    	System.out.println("attributeAdded"+(String) session.getAttribute("name"));
    	if(application.getAttribute("OnlineUsers") == null)
    	{
    		ArrayList<OnlineUser> users = new ArrayList<OnlineUser>();
    		OnlineUser temp = new OnlineUser();
    		
    		String name =(String) session.getAttribute("name");
    		try {
    			name = URLDecoder.decode(name, "UTF-8");
				//name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
    		temp.setName(name);
    		System.out.println("Online"+name);
    		users.add(temp);
    		application.setAttribute("OnlineUsers", users);
    	}
    	else
    	{
    		ArrayList<OnlineUser> users = (ArrayList<OnlineUser>) application.getAttribute("OnlineUsers");
    		OnlineUser temp = new OnlineUser();
    		String name =(String) session.getAttribute("name");
    		try {
				name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
    		temp.setName(name);
    		users.add(temp);
    		application.setAttribute("OnlineUsers", users);
    		for(OnlineUser p : users)
    		{
    			System.out.println(p.getName());
    		}
    	}
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
    	ServletContext application = session.getServletContext();
    	System.out.println("attributeRemoved");
    	if(application.getAttribute("username") == null)
    	{
    		//做什么？
    		System.out.println("application中为空");
    	}
    	else
    	{
    		ArrayList<OnlineUser> users = (ArrayList<OnlineUser>) application.getAttribute("OnlineUser");
    		OnlineUser temp = new OnlineUser();
    		temp.setName((String) session.getAttribute("username")); 
    		for(OnlineUser p : users)
    		{
    			if(p.getName() == temp.getName())
    			{
    				users.remove(p);
    				System.out.println(p.getName()+"退出");
    			}
    			else{}//好像也不用做什么
    		}
    		application.setAttribute("OnlineUsers",users);
    		for(OnlineUser p : users)
    		{
    			System.out.println(p.getName());
    		}
    	}
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
