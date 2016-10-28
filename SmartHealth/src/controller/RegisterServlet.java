package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Moderator;
import entities.NUser;
import model.UserDBHandler;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int NEW_USER = 101;
	private static final int INIT_KARMA = 0;
	private static final int NEW_MOD = 200;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		String emailp = request.getParameter("emailp").toString();
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
		String usertype = request.getParameter("usertype").toString();
		
		long millis = System.currentTimeMillis();
		java.sql.Date dateCreated = new java.sql.Date(millis);
		String result="";
		if(usertype.equals("nuser"))
		{
			System.out.println("nuser");
			NUser nuser = new NUser(username,  password,  emailp,  emails,
					 fname,  lname,  about,  photourl1,  photourl2,
					 photourl3,  snumber,  sname,
					 munic,  district,
					 parea,  NEW_USER,true, INIT_KARMA,
					 dateCreated);
			
			try {
				result = UserDBHandler.addNUser(nuser);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] splitResult = result.split(":");
			if(splitResult[0].equals("-1"))
			{
				request.setAttribute("message", "danger_"+splitResult[1]);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			else if(splitResult[0].equals("0"))
			{
				request.setAttribute("message", "success_"+splitResult[1]);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		}
		else if(usertype.equals("moderator"))
		{
			System.out.println("moderator");
			Moderator moderator = new Moderator(username,  password,  emailp,  emails,
					 fname,  lname,  about,  photourl1,  photourl2,
					 photourl3,  snumber,  sname,
					 munic,  district,
					 parea,  NEW_MOD,true,"999999999");
			try {
				result = UserDBHandler.addModerator(moderator);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] splitResult = result.split(":");
			if(splitResult[0].equals("-1"))
			{
				request.setAttribute("message", "danger_"+splitResult[1]);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			else if(splitResult[0].equals("0"))
			{
				request.setAttribute("message", "success_"+splitResult[1]);
				request.getRequestDispatcher("login.jsp").forward(request, response);
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
