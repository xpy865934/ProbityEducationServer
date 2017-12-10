package com.xpy.service;

import java.sql.SQLException;
import java.util.List;

import com.xpy.dao.ProductsDao;
import com.xpy.domain.Products;

/**
 * 作品Service
 * @author xpy
 *
 */
public class ProductsService {
	/**
	 * 查询所有的作品
	 * @return
	 * @throws SQLException
	 */
	public List<Products> selectAllProducts() throws Exception {
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
	public int insertProduct(Products products) throws Exception {
		ProductsDao productsDao = new ProductsDao();
		int insert = productsDao.insert(products);
		return insert;
	}
	
	/**
	 * 查询作品的最大ID
	 * @return
	 * @throws SQLException
	 */
	public int selectProductMaxId() throws SQLException {
		ProductsDao productsDao = new ProductsDao();
		int id = productsDao.selectProductMaxId();
		return id;
	}
	
	/**
	 * 根据作品类型查询作品
	 * @return
	 * @throws SQLException
	 */
	public List<Products> selectProductsByType(String type) throws Exception {
		ProductsDao productsDao = new ProductsDao();
		List<Products> allProducts = productsDao.selectProductsByType(type);
		return allProducts;
	}
}
