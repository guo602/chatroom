package dataBase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.History_show;
import model.User_show;
public class dbfunction {
	
	public dbfunction(){
		try {
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 检查用户名是否重复
	 * 返回false  有重复，插不进去
	 * 返回true   没有重复，可以插进去
	 */
	public boolean check_name(String name){
		String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		try {
			
			Connection con = DriverManager.getConnection(url, "root", "123456789pp");
			PreparedStatement sta=con.prepareStatement("select count(*) from users where name=?");
		
			sta.setString(1, name);
			ResultSet rs = sta.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)==1){
					return false;
				}
				else{
					return true;
				}
			}
			
			sta.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}
	
	/*
	 * 检查用户名是否匹配
	 * 返回true  匹配，跳转聊天室
	 * 返回false   不匹配，返回登录页
	 */
	public boolean check_match(String name,String pass){
		String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		try {
			
			//System.out.println(name);
			//System.out.println(pass);
			Connection con = DriverManager.getConnection(url, "root", "123456789pp");
			
			Statement sta = con.createStatement();
    		ResultSet rs = sta.executeQuery("select count(*) from users where name='"+name+"' and password='"+pass+"'");
    
			
			if(rs.next()){
				//System.out.println(rs.getInt(1));
				if(rs.getInt(1)==1){
					System.out.println("ok");
					return true;
				}
				else{
					System.out.println("mysql");

					System.out.println(name);
					System.out.println(pass);

					return false;
				}
			}
			sta.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}
	
	/*
	 * 插入chat数据库users表，包括用户名和密码
	 */
	public boolean insert_in(String name,String pass){
		String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		try {
			Connection con = DriverManager.getConnection(url, "root", "123456789pp");
			PreparedStatement sta=con.prepareStatement("insert into users (name,password) values(?,?)");
			sta.setString(1, name);
			sta.setString(2, pass);
			sta.execute();
			sta.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}
	
	/*
	 * 获取chat数据库users表所有的用户名
	 */
	public ArrayList<User_show> GetAllUser() {
		ArrayList<User_show> temp = new ArrayList<User_show>();
		String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		try {
			Connection con = DriverManager.getConnection(url, "root", "123456789pp");
			PreparedStatement sta = con.prepareStatement("select name from users");
			ResultSet rs = sta.executeQuery();
			while(rs.next()) {
				User_show use = new User_show();
				use.setUserName(rs.getString(1));
				temp.add(use);
			}
			rs.close();
			sta.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return temp;
	}
	
	/*
	 * 插入chat数据库history表，包括用户名和发送的话和时间表
	 */
	public boolean insert_words(String name,String word){
		String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			
			Connection con = DriverManager.getConnection(url, "root", "123456789pp");
			PreparedStatement sta=con.prepareStatement("insert into history (name,words,time) values(?,?,?)");
			sta.setString(1, name);
			sta.setString(2, word);
			//System.out.println(date.format(new Date()));
			sta.setString(3, date.format(new Date()).toString());
			sta.execute();
			sta.close();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
	
	/*
	 * 获取chat数据库history表所有的历史记录
	 */
	public ArrayList<History_show> GetAllWord() {
		ArrayList<History_show> temp = new ArrayList<History_show>();
		String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		try {
			Connection con = DriverManager.getConnection(url, "root", "123456789pp");
			PreparedStatement sta = con.prepareStatement("select * from history");
			ResultSet rs = sta.executeQuery();
			while(rs.next()) {
				History_show use = new History_show();
				use.setName(rs.getString(1));
				use.setWord(rs.getString(2));
				use.setTime(rs.getString(3));
				temp.add(use);
			}
			rs.close();
			sta.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return temp;
	}
	
	public boolean img_is_in(String name){
		String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		
			
			//System.out.println(name);
			//System.out.println(pass);
			Connection con;
			try {
				con = DriverManager.getConnection(url, "root", "123456789pp");

				Statement sta = con.createStatement();
	    		ResultSet rs = sta.executeQuery("select count(*) from user_info where name='"+name+"'");
	      
	    		String sql=new String();
	    		
	    		if(rs.next()){
					if(rs.getInt(1)==0){
						return false;
					}
					else{
						return true;
					}
				}
	    		con.close();
	    		sta.close();
	    		rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return false;
	}

	/*
	 * 插入chat数据库user_info表，包括用户名和头像
	 */
	public boolean insert_info(String name,byte[] bytes)throws FileNotFoundException{
		String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		try {
			
			//System.out.println(name);
			//System.out.println(pass);
			Connection con = DriverManager.getConnection(url, "root", "123456789pp");
			
			Statement sta = con.createStatement();
    		ResultSet rs = sta.executeQuery("select count(*) from user_info where name='"+name+"'");
      
    		String sql=new String();
    		
    		if(rs.next()){
				//System.out.println(rs.getInt(1));
				if(rs.getInt(1)==0){
					sql="insert into user_info values(?,?)";
					PreparedStatement psta = con.prepareStatement(sql);
					psta.setString(1, name);
		            psta.setBytes(2, bytes);
		            psta.executeUpdate();
		            psta.close();
					System.out.println("ok1");
					return true;
				}
				else{
					PreparedStatement psta = con.prepareStatement(sql);
					
		            sql="update user_info set img=? where name=?";
					psta = con.prepareStatement(sql);
					psta.setBytes(1, bytes);
					psta.setString(2, name);
		            psta.executeUpdate();
		            psta.close();
					System.out.println("ok2");
				}
			}
			sta.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}
	
	public byte[] ShowImg(String name) throws IOException{
		String url="jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        Connection con;
        // 创建一个 Statement 对象
        PreparedStatement stat = null;
		ResultSet rs=null;
		List<List<String>> list_test = new ArrayList<List<String>>();
		byte[] b = null;
		if(name != null && name != ""){
	        try {
	    		System.out.println("nickname="+name);
	            con = DriverManager.getConnection(url,"root","123456789pp");
				//创建语句
				String sql = "select img from user_info where name=?";
				stat = con.prepareStatement(sql);
				stat.setString(1, name);
				rs = stat.executeQuery();
				rs.next();
				InputStream in = rs.getBinaryStream("img");
				b = new byte[in.available()];
				in.read(b);
				rs.close();
				stat.close();
	            con.close();
	        } catch (SQLException e){
	            e.printStackTrace();
	        }
		}
        return b;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		dbfunction db=new dbfunction();
	    
    	Boolean result=db.insert_words("小仙女", "为谁");
    	
    	System.out.println(result);
		
		
	}
	

}
