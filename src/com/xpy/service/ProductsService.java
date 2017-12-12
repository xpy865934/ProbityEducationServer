package com.xpy.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xpy.dao.ProductsDao;
import com.xpy.domain.Products;
import com.xpy.utils.HibernateUtils;

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
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		List<Products> allProducts = null;
		try {
			ProductsDao productsDao = new ProductsDao();
			allProducts = productsDao.selectAllProducts();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("查询全部作品数据错误！");
		}
		return allProducts;
	}
	
	/**
	 * 插入新的作品
	 * @param products
	 * @return
	 * @throws SQLException
	 */
	public int insertProduct(Products products) throws Exception {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		int insert = 0;
		try {
			ProductsDao productsDao = new ProductsDao();
			insert = productsDao.insert(products);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("作品数据保存错误！");
		}
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
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		List<Products> allProducts =null;
		try {
			ProductsDao productsDao = new ProductsDao();
			allProducts = productsDao.selectProductsByType(type);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("按类型查询全部作品数据错误！");
		}
		return allProducts;
	}
}
