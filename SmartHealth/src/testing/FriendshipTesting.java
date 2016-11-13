package testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import model.FriendshipDBHandler;

public class FriendshipTesting {

	@Test
	public void test() throws NumberFormatException, SQLException, IOException
	{
		sendFriendRequestTest();
		//acceptFriendRequestTest();
		//removeFriendRequestTest();
		rejectFriendRequestTest();
		
	}



	public void sendFriendRequestTest() throws SQLException, NumberFormatException, IOException {

		assertEquals("Must not send request to oneself","-1:You can't send the request to yourself.",FriendshipDBHandler.sendRequest("modd", "modd"));

		assertEquals("Must not send request to moderator","-1:User is not a valid user",FriendshipDBHandler.sendRequest("modd", "mode"));

		assertEquals("Must not send request to non-existing user","-1:User does not exist!",FriendshipDBHandler.sendRequest("modd", "nouser"));

		assertEquals("Successful friend request","0:Friend request sent.",FriendshipDBHandler.sendRequest("fastrack", "modd"));

		assertEquals("Must not send multiple requests","-1:You are not allowed to send multiple requests.",FriendshipDBHandler.sendRequest("fastrack", "modd"));

		assertEquals("Must not send request to one from whom there is already a request.","-1:You already have a friend request from this user",FriendshipDBHandler.sendRequest("modd", "fastrack"));


	}



	public void acceptFriendRequestTest() throws SQLException {

		assertEquals("Cannot accept request of invalid user","-1:Error in Request Acceptance",FriendshipDBHandler.acceptFriendRequest("modd", "kamna"));
		//assertEquals("Cannot accept request of invalid user","-1:Error in Request Acceptance",FriendshipDBHandler.acceptFriendRequest("modd", "fastrack"));		
		assertEquals("Cannot accept if there is not a request","-1:Error in Request Acceptance",FriendshipDBHandler.acceptFriendRequest("modd", "fastrack"));

		assertEquals("Successfully accepts request.","0:Friend Request Accepted",FriendshipDBHandler.acceptFriendRequest("fastrack", "modd"));

	
	}

	public void rejectFriendRequestTest() throws SQLException {

		assertEquals("Cannot reject request of invalid user","-1:Error in Request Rejection",FriendshipDBHandler.rejectFriendRequest("modd", "kamna"));
		//assertEquals("Cannot accept request of invalid user","-1:Error in Request Acceptance",FriendshipDBHandler.acceptFriendRequest("modd", "fastrack"));		
		assertEquals("Request rejected","0:Friend Request Rejected",FriendshipDBHandler.rejectFriendRequest("fastrack", "modd"));

	}
	
	public void removeFriendRequestTest() throws SQLException, IOException {

		assertEquals("Cannot remove a non-existent friend.","-1:Can't remove  friend",FriendshipDBHandler.removeFriend("modd", "kamna"));
		//assertEquals("Cannot accept request of invalid user","-1:Error in Request Acceptance",FriendshipDBHandler.acceptFriendRequest("modd", "fastrack"));		
		assertEquals("Successfully removed friend","0:Successfully removed friend",FriendshipDBHandler.removeFriend("fastrack", "modd"));

	}
	
	
}
