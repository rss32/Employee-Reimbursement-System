package com.revature.project1.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.User;
import com.revature.project1.service.UserService;


class UserServiceImplTest {

	private static UserService us;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 us = new UserServiceImpl();
	}	

	@ParameterizedTest
	@CsvSource({ "user,pass", "1,1", "a,%", "username,jjlk&HIUH" })
	void testValidateUserCredentials(String user, String pass) {
		try {
			assertTrue(us.validateUserCredentials(user, pass));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@ParameterizedTest
	@CsvSource({ "' ',pass", ",1", "a,", "username,'  '"," ,","'   ',pass" })
	void testValidateUserCredentialsInvalid(String user, String pass) {
		assertThrows(BusinessException.class, () -> us.validateUserCredentials(user, pass));
	}

	static User[] userListInvalid() {
		User u1 = new User("f9", "lname", "", "", "email@email.com", "");
		User u2 = new User("frank", "l name", "", "", "ema il@email.com", "");
		User u3 = new User("f", "l", "", "", "emailemail.com", "");
		User u4 = new User("frank", "", "", "", "email@emailcom", "");
		User u5 = new User("test-name", "4", "", "", "email@email.com", "");
		User u6 = new User("9", "lname", "", "", "email@email.com", "");
		return new User[] { u1, u2, u3, u4, u5, u6 };
	}

	@ParameterizedTest
	@MethodSource("userListInvalid")
	void testValidateUserInvalidInfo(User u) {
		assertThrows(BusinessException.class, () -> us.validateUserInfo(u));
	}

	static User[] userListValid() {
		User u1 = new User("frank", "l name", "", "", "email@email.com", "");
		User u2 = new User("frank", "l-name", "", "", "email@email.com", "");
		User u3 = new User("f", "l", "", "", "email@ema.il.com", "");
		User u4 = new User("fra nk ma", "lname", "", "", "email@email.com", "");
		User u5 = new User("test-name", "name-name-name", "", "", "email@email.com", "");
		User u6 = new User("name", "lname", "", "", "email@email.com", "");
		return new User[] { u1, u2, u3, u4, u5, u6 };
	}

	@ParameterizedTest
	@MethodSource("userListValid")
	void testValidateUserInfo(User u) throws BusinessException {
		assertTrue(us.validateUserInfo(u));
	}
}
