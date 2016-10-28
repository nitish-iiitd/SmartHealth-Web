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

import entities.User;
import model.FriendshipDBHandler;

/**
 * Servlet implementation class FriendshipServlet
 */
@WebServlet("/AcceptFriendRequestServlet")
public class AcceptFriendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcceptFriendRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Accept Friend servlet");
		HttpSession session =request.getSession();
		User user = (User)session.getAttribute("user");
		String message="";
		if(user==null)
		{
			request.setAttribute("message", "danger_Please Login First !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		String acceptname = request.getParameter("acceptname").toString();
		
		String result="";
		try {
			result = FriendshipDBHandler.acceptFriendRequest(acceptname, user.getUsername());
			ArrayList<User> friends=null;
			ArrayList<String> friendrequests=null;
			try {
				friends = FriendshipDBHandler.listFriends(user.getUsername());
				friendrequests = FriendshipDBHandler.getFriendRequests(user.getUsername());
				System.out.println("friend requests : "+friendrequests.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("friends", friends);
			session.setAttribute("friendrequests", friendrequests);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] splitResult = result.split(":");
		if(splitResult[0].equals("-1"))
		{
			request.setAttribute("message", "danger_"+splitResult[1]);
			request.getRequestDispatcher("nuser.jsp").forward(request, response);
		}
		else if(splitResult[0].equals("0"))
		{
			request.setAttribute("message", "success_"+splitResult[1]);
			request.getRequestDispatcher("nuser.jsp").forward(request, response);
		}

		//request.getRequestDispatcher("nuser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
