package com.xpy.service;

import java.sql.SQLException;
import java.util.List;

import com.xpy.dao.ProductsDao;
import com.xpy.domain.Products;

public class ProductsService {
	public List<Products> selectAllProducts() throws SQLException {
		ProductsDao productsDao = new ProductsDao();
		List<Products> allProducts = productsDao.selectAllProducts();
		return allProducts;
	}
}
