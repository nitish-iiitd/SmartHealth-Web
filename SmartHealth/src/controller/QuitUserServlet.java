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
//@WebServlet("/quituser")
public class QuitUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int NEW_USER = 101;
	private static final int INIT_KARMA = 0;
	private static final int NEW_MOD = 200;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuitUserServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		User  usr = (User) request.getSession().getAttribute("user");
		
		String res = UserDBHandler.quitUser(usr.getUsername());
				
		request.getSession().setAttribute("user", null);
		
		request.setAttribute("message", "success_"+res);
		HttpSession session =request.getSession();
		session.invalidate();

		String[] splitResult = res.split(":");
		if(splitResult[0].equals("-1"))
		{
			request.setAttribute("message", "danger_"+splitResult[1]);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else if(splitResult[0].equals("0"))
		{
			request.setAttribute("message", "success_"+splitResult[1]);
			request.getRequestDispatcher("login.jsp").forward(request, response);
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
