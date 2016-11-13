package entities;


import java.util.List;

public class CombinedForumDetail {
	
	Post post;
	List<Comment> comm;
	Float rat;	
	
	
	public CombinedForumDetail(Post post, List<Comment> comm, float rat) {
		super();
		this.post = post;
		this.comm = comm;
		this.rat = rat;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<Comment> getComm() {
		return comm;
	}
	public void setComm(List<Comment> comm) {
		this.comm = comm;
	}
	public float getRat() {
		return rat;
	}
	public void setRat(float rat) {
		this.rat = rat;
	}
	
	
}