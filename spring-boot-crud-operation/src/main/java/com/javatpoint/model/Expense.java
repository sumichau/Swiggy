package com.javatpoint.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//mark class as an Entity 
//@Entity
////defining class name as Table name
//@Table
public class Expense
{
//Defining book id as primary key

private String groupId;

private String expenseId;
private String expenseType;
private List<UserInfo> userinfos;

public String getGroupId() {
	return groupId;
}
public void setGroupId(String groupId) {
	this.groupId = groupId;
}
public String getExpenseId() {
	return expenseId;
}
public void setExpenseId(String expenseId) {
	this.expenseId = expenseId;
}
public String getExpenseType() {
	return expenseType;
}
public void setExpenseType(String expenseType) {
	this.expenseType = expenseType;
}

public List<UserInfo> getUserinfos() {
	return userinfos;
}
public void setUserinfos(List<UserInfo> userinfos) {
	this.userinfos = userinfos;
}



}