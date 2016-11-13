package testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import entities.User;
import model.UserDBHandler;

public class LoginTesting {
	@Test
	public void loginTest() throws SQLException {
		
		User user = new User("fastrack", "1234", "fast@fast.com", "fast2@fast.com", "Fastrack", "Fast", "", "", "", "","", "", "", "", "", 101,true);
		
		// Successful Login
		assertEquals("Successful Login",user.getUsername(),UserDBHandler.loginAndGetType("fast@fast.com","1234").getUsername());
	
		// Unsuccessful Login
		assertEquals("Unsuccessful Login - Wrong password",null,UserDBHandler.loginAndGetType("fast@fast.com","12345"));
	
		// Unsuccessful Login
		assertEquals("Unsuccessful Login - Wrong Email",null,UserDBHandler.loginAndGetType("fast2@fast.com","1234"));
	
		// Unsuccessful Login
		assertEquals("Unsuccessful Login - Empty email",null,UserDBHandler.loginAndGetType("","1234"));
		
		// Unsuccessful Login
		assertEquals("Unsuccessful Login - Empty password",null,UserDBHandler.loginAndGetType("fast2@fast.com",""));
	}
	
}
