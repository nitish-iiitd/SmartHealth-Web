package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Forum;
import entities.User;

/**
 * Servlet implementation class StoreForum
 */
@WebServlet("/StoreForum")
public class StoreForum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreForum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String topic= request.getParameter("topic").toString();
		String summary = request.getParameter("summary").toString();
		
		User user = (User) request.getSession().getAttribute("user");
		if(user==null)
		{
			request.setAttribute("message", "danger_Please Login First !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		System.out.println(user.getUsername());
		Forum f = new Forum(topic,summary,user.getUsername());
		if(f!=null)
		f.storeForum();
		else
			System.out.println("Fourm is null");
		
		request.getRequestDispatcher("moderator.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
