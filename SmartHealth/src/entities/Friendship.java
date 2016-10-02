package entities;

import java.sql.Timestamp;

public class Friendship {

	private String requested_username ;
	private String requester_username ;
	private Timestamp whenRequested;
	private Timestamp whenWithdrawn ;
	private Timestamp whenRejected ;
	private Timestamp whenConfirmed ;
	private Timestamp whenUnfriended ;

	public Friendship( String requester_username,String requested_username, Timestamp whenRequested,
			Timestamp whenWithdrawn, Timestamp whenRejected, Timestamp whenConfirmed, Timestamp whenUnfriended) {
		super();
		this.requested_username = requested_username;
		this.requester_username = requester_username;
		this.whenRequested = whenRequested;
		this.whenWithdrawn = whenWithdrawn;
		this.whenRejected = whenRejected;
		this.whenConfirmed = whenConfirmed;
		this.whenUnfriended = whenUnfriended;
	}

	public String getRequested_username() {
		return requested_username;
	}

	public void setRequested_username(String requested_username) {
		this.requested_username = requested_username;
	}

	public String getRequester_username() {
		return requester_username;
	}

	public void setRequester_username(String requester_username) {
		this.requester_username = requester_username;
	}

	public Timestamp getWhenRequested() {
		return whenRequested;
	}

	public void setWhenRequested(Timestamp whenRequested) {
		this.whenRequested = whenRequested;
	}

	public Timestamp getWhenWithdrawn() {
		return whenWithdrawn;
	}

	public void setWhenWithdrawn(Timestamp whenWithdrawn) {
		this.whenWithdrawn = whenWithdrawn;
	}

	public Timestamp getWhenRejected() {
		return whenRejected;
	}

	public void setWhenRejected(Timestamp whenRejected) {
		this.whenRejected = whenRejected;
	}

	public Timestamp getWhenConfirmed() {
		return whenConfirmed;
	}

	public void setWhenConfirmed(Timestamp whenConfirmed) {
		this.whenConfirmed = whenConfirmed;
	}

	public Timestamp getWhenUnfriended() {
		return whenUnfriended;
	}

	public void setWhenUnfriended(Timestamp whenUnfriended) {
		this.whenUnfriended = whenUnfriended;
	}





}
