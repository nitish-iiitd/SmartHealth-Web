package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Datum;
import entities.User;
import model.FriendshipDBHandler;
import model.HealthDataDBHanlder;
import model.UserDBHandler;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/ShowProfileServlet")
public class ShowProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int NEW_USER = 101;
	private static final int INIT_KARMA = 0;
	private static final int NEW_MOD = 200;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProfileServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String profilename = request.getParameter("profilename").toString();
		
		//String password = request.getParameter("password").toString();
		HttpSession session =request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null)
		{
			request.setAttribute("message", "danger_Please Login First !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else
		{
			User profile=null;
			try {
				profile = UserDBHandler.getUser(profilename);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(profile.getType()==NEW_USER)
			{
				
				try {
					ArrayList<Datum> profilehealthdata = HealthDataDBHanlder.getData(profile.getUsername());
					//ArrayList<String> friendrequests = FriendshipDBHandler.listFriends(profile.getUsername());
					request.setAttribute("profilehealthdata", profilehealthdata);
					request.setAttribute("profile", profile);
					request.getRequestDispatcher("profile.jsp").forward(request, response);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
