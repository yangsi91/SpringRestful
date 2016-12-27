package com.kitchen.dao;

import java.util.List;

import com.kitchen.model.Cook;
import com.kitchen.model.Product;

public interface ProductDao {
	public List<Product> getProductId(String name);
}
