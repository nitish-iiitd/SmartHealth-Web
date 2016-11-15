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
import entities.NUser;
import entities.User;
import model.UserDBHandler;
import model.HealthDataDBHanlder;

/**
 * Servlet implementation class AddHealthServlet
 */
@WebServlet("/AddHealthServlet")
public class AddHealthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddHealthServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ArrayList<Datum> datumList = new ArrayList<>();
		
		HttpSession session =request.getSession();
		String property= request.getParameter("property").toString();
		String propertyvalue = request.getParameter("propertyvalue").toString();
		User user = (User)session.getAttribute("user");
		if(user==null)
		{
			request.setAttribute("message", "danger_Please Login First !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		long millis = System.currentTimeMillis();
		java.sql.Date dateCreated = new java.sql.Date(millis);
		String result="";
		Datum datum=null;
		if(property.equals("distance"))
		{
			System.out.println("distance");
			datum = new Datum(1,user.getUsername(),1,propertyvalue,dateCreated);
		}
		else if(property.equals("calories"))
		{
			System.out.println("calories");
			datum = new Datum(1,user.getUsername(),2,propertyvalue,dateCreated);
		}
		else if(property.equals("bp"))
		{
			System.out.println("bp");
			datum = new Datum(1,user.getUsername(),3,propertyvalue,dateCreated);
		}
		try {
			UserDBHandler.incrementKarma(user.getUsername());
			
			result = HealthDataDBHanlder.saveDatum(datum);
			
		} catch (SQLException e) {
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
			ArrayList<Datum> updateddatumList = HealthDataDBHanlder.getData(user.getUsername());
			session.setAttribute("healthdata", updateddatumList);
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
