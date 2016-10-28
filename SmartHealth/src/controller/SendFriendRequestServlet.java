package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Datum;
import entities.NUser;
import entities.User;
import model.UserDBHandler;
import model.FriendshipDBHandler;
import model.HealthDataDBHanlder;

/**
 * Servlet implementation class SendFriendRequestServlet
 */
@WebServlet("/SendFriendRequestServlet")
public class SendFriendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendFriendRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session =request.getSession();
		String requested= request.getParameter("requested").toString();
		User user = (User)session.getAttribute("user");
		if(user==null)
		{
			request.setAttribute("message", "danger_Please Login First !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		System.out.println("1:"+user.getUsername()+"2:"+requested);
		String	result = FriendshipDBHandler.sendRequest(user.getUsername(), requested);
		

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
