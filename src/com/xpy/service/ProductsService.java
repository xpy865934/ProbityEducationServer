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
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		List<Products> allProducts = null;
		try {
			ProductsDao productsDao = new ProductsDao();
			allProducts = productsDao.selectAllProducts();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("��ѯȫ����Ʒ���ݴ���");
		}
		return allProducts;
	}
	
	/**
	 * �����µ���Ʒ
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
			throw new Exception("��Ʒ���ݱ������");
		}
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
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		List<Products> allProducts =null;
		try {
			ProductsDao productsDao = new ProductsDao();
			allProducts = productsDao.selectProductsByType(type);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("�����Ͳ�ѯȫ����Ʒ���ݴ���");
		}
		return allProducts;
	}
}
