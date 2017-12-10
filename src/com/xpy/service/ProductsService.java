package com.xpy.service;

import java.sql.SQLException;
import java.util.List;

import com.xpy.dao.ProductsDao;
import com.xpy.domain.Products;

/**
 * ��ƷService
 * @author xpy
 *
 */
public class ProductsService {
	/**
	 * ��ѯ���е���Ʒ
	 * @return
	 * @throws SQLException
	 */
	public List<Products> selectAllProducts() throws Exception {
		ProductsDao productsDao = new ProductsDao();
		List<Products> allProducts = productsDao.selectAllProducts();
		return allProducts;
	}
	
	/**
	 * �����µ���Ʒ
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
	 * ��ѯ��Ʒ�����ID
	 * @return
	 * @throws SQLException
	 */
	public int selectProductMaxId() throws SQLException {
		ProductsDao productsDao = new ProductsDao();
		int id = productsDao.selectProductMaxId();
		return id;
	}
	
	/**
	 * ������Ʒ���Ͳ�ѯ��Ʒ
	 * @return
	 * @throws SQLException
	 */
	public List<Products> selectProductsByType(String type) throws Exception {
		ProductsDao productsDao = new ProductsDao();
		List<Products> allProducts = productsDao.selectProductsByType(type);
		return allProducts;
	}
}
