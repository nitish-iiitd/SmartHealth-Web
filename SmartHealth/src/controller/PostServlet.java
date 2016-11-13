package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Post;
import entities.User;

/**
 * Servlet implementation class CommentServlet
 */
//@WebServlet("/storeComment")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("In Comment Servlet");
		//System.out.println(request.getAttribute("comment"));
		User  u = null ;
		HttpSession session =request.getSession();
		u = (User) session.getAttribute("user");
		if(u==null)
			System.out.println("USer is NULL");
		String username = request.getParameter("fid");
		String comment = request.getParameter("comment");
		
		String text = comment ;
		
		String photoLocation = null,linkLocation = null, videoLocation = null;
		photoLocation = request.getParameter("photoLocation");
		linkLocation = request.getParameter("linkLocation");
		videoLocation = request.getParameter("videoLocation");
		System.out.println(photoLocation+linkLocation+videoLocation+text);
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		System.out.println("Time: "+ date);
		Post p = new Post(u.getUsername(),null,Integer.parseInt(username),text,photoLocation,linkLocation,videoLocation);
		
				
		try {
			if(p.storePost()){
				
				text = "success";
			}
			else{
				text = "fail";
			}
						
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	    //response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    //response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(photoLocation+linkLocation+videoLocation+text);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
