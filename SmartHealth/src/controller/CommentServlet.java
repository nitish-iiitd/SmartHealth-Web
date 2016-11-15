package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Comment;
import entities.User;
import model.UserDBHandler;

/**
 * Servlet implementation class CommentServlet
 */
//@WebServlet("/savecomment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Hi this is Comment servlet...");
		User user = (User) request.getSession().getAttribute("user");
		if(user==null)
		{
			request.setAttribute("message", "danger_Please Login First !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		
		String fid = request.getParameter("fid");
		String Comment = request.getParameter("comment");
		String PostTime = request.getParameter("myPostTime");
		String photoLocation = request.getParameter("photoLocation");
		String myPostUserName = request.getParameter("myPostUserName");
		String videoLocation = request.getParameter("videoLocation");
		String linkLocation= request.getParameter("linkLocation");
		
		System.out.println(fid+Comment+PostTime);
		Comment com = new  Comment(myPostUserName,Timestamp.valueOf(PostTime), user.getUsername(), Timestamp.valueOf(PostTime),Comment, photoLocation,linkLocation, videoLocation);
		try {
			
			UserDBHandler.incrementKarma(user.getUsername());
			com.storeComment();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
