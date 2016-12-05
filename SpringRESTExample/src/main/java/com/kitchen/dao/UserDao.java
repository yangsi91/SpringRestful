package com.kitchen.dao;

import java.util.List;

import com.kitchen.model.User;

public interface UserDao {

	public void insert(User user);
	public void delete(String id);
	public String getUser(String id);
	public List<User> getList();
	
}
