package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.CombinedForumDetail;
import entities.Comment;
import entities.Forum;
import entities.Post;
import entities.Rating;
import model.ForumDBHandler;

/**
 * Servlet implementation class TryForum
 */
@WebServlet("/SingleForumServlet")
public class SingleForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SingleForumServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try{
			String id = request.getParameter("fid");
			int fid = Integer.parseInt(id);
			System.out.println("Forum Received: "+fid);

			List<CombinedForumDetail>allPosts=new ArrayList<CombinedForumDetail>();
			if(fid>0){

				Forum f = ForumDBHandler.getForum(fid);
				request.setAttribute("currentForum", f);
				
				List<Post> posts= Post.getPosts(fid);
				
				if(posts == null)
				{	System.out.println("posts in null");
					request.getRequestDispatcher("singleForum.jsp").forward(request, response);
					return;

				}
				System.out.println("Size of Posts read..."+posts.size());

				Iterator<Post> ite = posts.iterator();

				while(ite.hasNext()){

					Post tempPost = (Post) ite.next();
					Float tempRat = 0f;
					int countRat=0;

					// Fetch all the rating for this post.
					List<Rating> allratings = Rating.getRating(tempPost.getUsername(),tempPost.getTimeCreated());
					if(allratings == null){
						System.out.println("No Ratings for "+tempPost.getUsername()+"'s post.");
						tempRat=0f;
					}else{
						Iterator<Rating> ratIte = allratings.iterator();
						while(ratIte.hasNext()){
							tempRat+=((Rating)ratIte.next()).getStars();
							countRat++;
						}

					}

					// Fetch comment for this post (if any)
					List<Comment> com = Comment.getComment(tempPost.getUsername(),tempPost.getTimeCreated());
					if (com != null){
						//System.out.println("Comments on this post");
					}
					System.out.println("Rating for "+tempPost.getUsername()+" is: "+tempRat/countRat );
					allPosts.add(new CombinedForumDetail(tempPost, com,countRat==0?0: tempRat/countRat));
					//System.out.println("Post Added!");

				}
				request.setAttribute("Posts", allPosts);

								// getServletContext().getRequestDispatcher("/singleForum.jsp").forward(request, response);
				
								/*RequestDispatcher rdi = request.getRequestDispatcher("singleForum.jsp");
							if(rdi != null)
							System.out.println("All RqDis is not Null");*/
								//rdi.forward(request, response);
				request.getRequestDispatcher("singleForum.jsp").forward(request, response);
				return;


			} // end of fid > 0

			}catch(Exception e){
				System.out.println("Forum Not Found");
				//	request.getRequestDispatcher("/singleForum.jsp").forward(request, response);

			}



			request.getRequestDispatcher("singleForum.jsp").forward(request, response);
			return;

		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

	}