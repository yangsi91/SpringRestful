package com.kitchen.dao;

import java.util.List;

import com.kitchen.model.User;

public interface UserDao {
	public void register(User user);
	public User get(String userid);
	public List<User> getList();
}
