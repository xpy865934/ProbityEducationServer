package com.xpy.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.xpy.domain.Products;
import com.xpy.utils.HibernateUtils;
import com.xpy.utils.JDBCUtils;

/**
 * ProductDao
 * 
 * @author xpy
 *
 */
public class ProductsDao {

	/**
	 * �����µ���Ʒ
	 * 
	 * @param products
	 * @return
	 * @throws SQLException
	 */
	public int insert(Products products) throws Exception {
		Session session = null;
		Transaction tr = null;
		int update = 0;
		try {
			session = HibernateUtils.getSession();
			tr = session.beginTransaction();
			update = (int) session.save(products);
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("��Ʒ���ݱ������");
		} finally {
			if (tr != null) {
				tr.commit();
			}
			if (session != null) {
				session.close();
			}
		}
		return update;

		// QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		// String sql = "insert into
		// products(product_name,banji,author,department,product_path,type,tel)
		// values(?,?,?,?,?,?,?)";
		// Object[] param =
		// {products.getProduct_name(),products.getBanji(),products.getAuthor(),products.getDepartment(),products.getProduct_path(),products.getType(),products.getTel()};
		// int update = qr.update(sql, param);
		// return update;
	}

	/**
	 * ��ѯ���е���Ʒ
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Products> selectAllProducts() throws Exception {
		Session session = null;
		Transaction tr = null;
		int update = 0;
		List<Products> list = null;
		try {
			session = HibernateUtils.getSession();
			tr = session.beginTransaction();
			Criteria criteria = session.createCriteria(Products.class);
			list = criteria.list();
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("��ѯȫ����Ʒ���ݴ���");
		} finally {
			if (tr != null) {
				tr.commit();
			}
			if (session != null) {
				session.close();
			}
		}
		return list;

//		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
//		String sql = "select * from products";
//		List<Products> query = qr.query(sql, new BeanListHandler<Products>(Products.class));
//		return query;
	}

	/**
	 * ��ѯ���ݿ�����Ʒ�����ID
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int selectProductMaxId() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select max(id) from products";
		Object[] query = qr.query(sql, new ArrayHandler());
		if (query[0] == null) {
			return 0;
		}
		return (int) query[0];
	}

	/**
	 * ������Ʒ���Ͳ�ѯ��Ʒ
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Products> selectProductsByType(String type) throws Exception {
		Session session = null;
		Transaction tr = null;
		int update = 0;
		List<Products> list = null;
		try {
			session = HibernateUtils.getSession();
			tr = session.beginTransaction();
			Criteria criteria = session.createCriteria(Products.class);
			criteria.add(Restrictions.eq("type", type));
			list = criteria.list();
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("�����Ͳ�ѯȫ����Ʒ���ݴ���");
		} finally {
			if (tr != null) {
				tr.commit();
			}
			if (session != null) {
				session.close();
			}
		}
		return list;
		
//		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
//		String sql = "select * from products where type='" + type + "'";
//		List<Products> query = qr.query(sql, new BeanListHandler<Products>(Products.class));
//		return query;
	}
}
