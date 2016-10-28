package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Moderator;
import entities.NUser;
import entities.User;
import model.UserDBHandler;

/**
 * Servlet implementation class UpdateDetailsServlet
 */
@WebServlet("/UpdateDetailsServlet")
public class UpdateDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDetailsServlet() {
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
		User user = (User) session.getAttribute("user");

		String username = user.getUsername();
		String password = user.getPass();
		String emailp = user.getEmailp();
		String emails = request.getParameter("emails").toString();
		String fname = request.getParameter("fname").toString();
		String lname = request.getParameter("lname").toString();
		String about = request.getParameter("about").toString();
		String photourl1 = request.getParameter("photourl1").toString();
		String photourl2 = request.getParameter("photourl2").toString();
		String photourl3 = request.getParameter("photourl3").toString();
		String snumber = request.getParameter("snumber").toString();
		String sname = request.getParameter("sname").toString();
		String munic = request.getParameter("munic").toString();
		String district = request.getParameter("district").toString();
		String parea= request.getParameter("parea").toString();
		int type= user.getType();
		boolean status= user.isStatus();


		long millis = System.currentTimeMillis();
		java.sql.Date dateCreated = new java.sql.Date(millis);
		String result="";

		System.out.println("nuser");
		User updateduser = new User(username,  password,  emailp,  emails,
				fname,  lname,  about,  photourl1,  photourl2,
				photourl3,  snumber,  sname,
				munic,  district,
				parea, type,status);


		try {
			result = UserDBHandler.updateUser(updateduser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			session.setAttribute("user", updateduser);
			System.out.println(updateduser.getAbout());
			request.getRequestDispatcher("nuser.jsp").forward(request, response);
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
