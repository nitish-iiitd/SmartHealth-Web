package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Rating;
import entities.User;

/**
 * Servlet implementation class saveRating
 */
//@WebServlet("/")
public class saveRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public saveRating() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("Hi this is Rating servlet...");
		User user = (User) request.getSession().getAttribute("user");
		
		if(user==null)
		{
			request.setAttribute("message", "danger_Please Login First !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		String PostTime = request.getParameter("myPostTime");
		String myPostUserName = request.getParameter("myPostUserName");
		String myRatings = request.getParameter("rating");
		if(user!= null){
			Rating rate = new Rating(myPostUserName, Timestamp.valueOf(PostTime), user.getUsername(), Integer.parseInt(myRatings));
		try {
			rate.storeRating();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		    response.getWriter().write("Error: Already Rated!!");

		}
	    response.getWriter().write("_ok");
		}else{
		    response.getWriter().write("User Not Logged In");

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
