package com.xpy.service;

import java.sql.SQLException;
import java.util.List;

import com.xpy.dao.ProductsDao;
import com.xpy.domain.Products;

public class ProductsService {
	/**
	 * 查询所有的作品
	 * @return
	 * @throws SQLException
	 */
	public List<Products> selectAllProducts() throws SQLException {
		ProductsDao productsDao = new ProductsDao();
		List<Products> allProducts = productsDao.selectAllProducts();
		return allProducts;
	}
	
	/**
	 * 插入新的作品
	 * @param products
	 * @return
	 * @throws SQLException
	 */
	public int insertProduct(Products products) throws SQLException {
		ProductsDao productsDao = new ProductsDao();
		int insert = productsDao.insert(products);
		return insert;
	}
	
	public int selectMessageMaxId() throws SQLException {
		ProductsDao productsDao = new ProductsDao();
		int id = productsDao.selectProductMaxId();
		return id;
	}
}
