package com.kitchen.dao;

import java.util.List;

import com.kitchen.model.User;

public interface UserDao {
	public void insert(User user);
	public User getUser(String userid);
	public void update(User user);
	public void delete(String userid);
	public List<User> getList();
}
