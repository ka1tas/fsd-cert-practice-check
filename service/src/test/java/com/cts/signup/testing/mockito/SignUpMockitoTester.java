package com.cts.signup.testing.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.signup.bean.User;
import com.cts.signup.rest.SignupController;
import com.cts.signup.service.UserService;


	public class SignUpMockitoTester {
		

		
		
		
		   @Mock
		   private UserService service;
		   
		   @InjectMocks
		   private SignupController controller;
		   
		   @Before
		   public void setUp() throws Exception {
		      //Create a user object which is to be tested		
			   MockitoAnnotations.initMocks(this);
		   }
		   
		   @Test
		   public void testUserController(){
		    	   
		      //Creates a list of users to be added to the portfolio
		    //  List<User> users = new ArrayList<User>();
		      User user1 = new User(1,"Saikat","saikat@gmail.com","12345");
		      User user2 = new User(2,"SAm","sam@gmail.com","12345");	
		      
		      /*users.add(user1);
		      users.add(user2);*/
		      
		      when(service.save(user1)).thenReturn(true);
		      
		     	boolean result = controller.saveUser(user1);
		      
		      List<User> usersresult2=null;
		      
		      assertEquals(true, result);	   
		   }
		   
		
		   
		   
		}