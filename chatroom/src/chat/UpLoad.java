package chat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import dataBase.dbfunction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UpLoad
 */
@WebServlet({ "/UpLoad", "/upload" })
public class UpLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpLoad() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// DBfunction需要继续改
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("img/jpg");
		PrintWriter out = response.getWriter();
		// Check that we have a file upload request

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("name");
		username = URLDecoder.decode(username, "UTF-8");
		String dirname = new String("E:\\temp\\" + username);
		File dir = new File(dirname);
		if (!dir.exists()) {
			dir.mkdir();
		}

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			File repository = new File(dirname);
			factory.setRepository(repository);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			try {
				List<FileItem> items = upload.parseRequest(request);
				dbfunction db = new dbfunction();
				for (int i = 0; i < items.size(); i++) {
					FileItem item = items.get(i);
					String name = item.getName();
					if (name != null) {
						byte[] buffer = item.get();
						//db.insert_in(username, buffer);
						// String[] location = name.split("\\\\");
						// String last_name = location[location.length-1];
						// item.write(new
						// File("E:\\coding\\intern\\icons\\"+last_name));
					}
				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.addHeader("refresh", "1;url=info.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		PrintWriter out = response.getWriter();

		// 创建目录，或将图片存在已有目录，目录以用户名命名
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("name");
		username = URLDecoder.decode(username, "UTF-8");
		String dirname = new String("E:\\temp\\" + username);
		File dir = new File(dirname);
		if (!dir.exists()) {
			dir.mkdir();
		}

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			File repository = new File(dirname);
			factory.setRepository(repository);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			try {
				List<FileItem> items = upload.parseRequest(request);
				dbfunction db = new dbfunction();
				for (int i = 0; i < items.size(); i++) {
					FileItem item = items.get(i);
					String name = item.getName();
					if (name != null) {
						byte[] buffer = item.get();
						db.insert_info(username,buffer);
					}
				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.println("修改成功<a href='login_check.html'>返回</a>");
		response.addHeader("refresh", "1;url=info.jsp");
		// boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// System.out.println("error1");
		// if (isMultipart) {
		// DiskFileItemFactory factory = new DiskFileItemFactory();
		// File repository = new File(dirname);
		// factory.setRepository(repository);
		// ServletFileUpload upload = new ServletFileUpload(factory);
		//
		//
		// try {
		// List<FileItem> items = upload.parseRequest(request);
		// System.out.println(items);
		// for (int i = 0; i < items.size(); i++) {
		// FileItem item = items.get(i);
		// String filename = item.getName().trim();
		// filename = filename.substring(filename.lastIndexOf("\\") + 1);
		// System.out.println("22222222222");
		// System.out.println(filename);
		// int dot = filename.lastIndexOf('.');
		// if ((dot > -1) && (dot < (filename.length()))) {
		// filename=filename.substring(0, dot);
		// }
		// //name = name.substring(name.lastIndexOf(".") + 2);
		// System.out.println(item.getString());
		// System.out.println("333333333333");
		// System.out.println(filename);
		// //item.write(repository);//new File("E:\\tu\\" + name));
		// }
		// } catch (FileUploadException e) {
		// e.printStackTrace();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// PrintWriter out1 = response.getWriter();
		// out1.print("ok");
	}
}
