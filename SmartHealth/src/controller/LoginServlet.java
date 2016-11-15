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
import entities.Moderator;
import entities.NUser;
import entities.User;
import model.FriendshipDBHandler;
import model.HealthDataDBHanlder;
import model.UserDBHandler;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static final int NEW_USER = 101;
	private static final int MID_USER = 102;
	private static final int OLD_USER = 103;
	private static final int INIT_KARMA = 0;
	private static final int NEW_MOD = 200;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String email = request.getParameter("email").toString();
		String password = request.getParameter("password").toString();
		try {
			User u = UserDBHandler.loginAndGetType(email, password);
			if(u==null)
			{
				request.setAttribute("message", "danger_Invalid Details!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			if(u.getType()==NEW_USER || u.getType()==MID_USER || u.getType()==OLD_USER)
			{
				//NUser nu = (NUser) UserDBHandler.loginAndGetType(email, password);
				UserDBHandler.updateUserType(u.getUsername(), u.getType());
				ArrayList<Datum> healthdata = HealthDataDBHanlder.getData(u.getUsername());
				ArrayList<String> friendrequests = FriendshipDBHandler.getFriendRequests(u.getUsername());
				HttpSession session =request.getSession();
				session.setAttribute("user", u);
				session.setAttribute("healthdata", healthdata);
				session.setAttribute("friendrequests", friendrequests);
				request.getRequestDispatcher("nuser.jsp").forward(request, response);
			}
			else if(u.getType()==NEW_MOD)
			{
				//Moderator mod = (Moderator) UserDBHandler.loginAndGetType(email, password);
				ArrayList<String> quals = UserDBHandler.getQualifications(u.getUsername());				
				HttpSession session =request.getSession();
				session.setAttribute("user",u);
				session.setAttribute("quals",quals);
				System.out.println("inside loginservlet:"+u);
				request.getRequestDispatcher("moderator.jsp").forward(request, response);
			}
		} catch (SQLException | ClassNotFoundException e) {
			
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
