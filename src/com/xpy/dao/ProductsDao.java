package com.xpy.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.xpy.domain.Products;
import com.xpy.utils.JDBCUtils;

public class ProductsDao {
	
	@Test
	public int insert(Products products) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into products values(?,?,?,?,?)";
		Object[] param = {products.getId(),products.getBanji(),products.getName(),products.getDepartment(),products.getProduct_path()};
		int update = qr.update(sql, param);
		return update;
	}
	
	/**
	 * 查询所有的作品
	 * @return
	 * @throws SQLException
	 */
	public List<Products> selectAllProducts() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from products";
		List<Products> query = qr.query(sql, new BeanListHandler<Products>(Products.class));
		return query;
	}
}
