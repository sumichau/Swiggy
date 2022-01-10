package com.javatpoint.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.javatpoint.model.Expense;
import com.javatpoint.model.UserInfo;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {

	@InjectMocks
	EventController eventController;

	@Test
	public void testFor1Expense() {
		Expense es=new Expense();
		es.setExpenseType("EXACT");
		es.setExpenseId("e1");
		es.setGroupId("g1");
		List<UserInfo> userinfos = userInfoFor1();
		
		es.setUserinfos(userinfos);
	eventController.saveexpense(es);
	
	assertEquals(2,eventController.getAllExpense().size() );
	assertEquals(1,eventController.getbyuserid("u2").size() );
	
		
	}
	@Test
	public void testFor2Expense() {
		eventController.getclear();
		Expense es=new Expense();
		es.setExpenseType("EXACT");
		es.setExpenseId("e1");
		es.setGroupId("g1");
		List<UserInfo> userinfos = userInfoFor1();
		
		
		
		es.setUserinfos(userinfos);
		Expense es1=new Expense();
		es1.setExpenseType("EQUAL");
		es1.setExpenseId("e2");
		es1.setGroupId("g1");
		List<UserInfo> userinfos1 =userInfoFor2();
		es1.setUserinfos(userinfos1);
		
	eventController.saveexpense(es);
	eventController.saveexpense(es1);
	
	assertEquals(3,eventController.getAllExpense().size() );
	assertEquals(0,eventController.getbyuserid("u2").size() );
	assertEquals(2,eventController.getbyuserid("u4").size() );
	
		
	}
	private List<UserInfo> userInfoFor1() {
		List<UserInfo> userinfos=new ArrayList<UserInfo>();
		createuser(userinfos,"u1",1250,0);
		createuser(userinfos,"u2",0,370);
		createuser(userinfos,"u3",0,880);
		createuser(userinfos,"u4",0,0);
		return userinfos;
	}

	
	private List<UserInfo> userInfoFor2() {
		
		List<UserInfo> userinfos=new ArrayList<UserInfo>();
		createuser(userinfos,"u1",200,250);
		createuser(userinfos,"u2",800,250);
		createuser(userinfos,"u3",0,250);
		createuser(userinfos,"u4",0,250);
		return userinfos;
	}

	private void createuser(List<UserInfo> userinfos, String id, int paid, int owns) {
		UserInfo u1=new UserInfo();
		u1.setUserId(id);
		
		u1.setPaid(paid);
		u1.setOwns(owns);
		userinfos.add(u1);
	
	}

}
