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
		String sql = "insert into products(product_name,banji,author,department,product_path,type,tel) values(?,?,?,?,?,?,?)";
		Object[] param = {products.getProduct_name(),products.getBanji(),products.getAuthor(),products.getDepartment(),products.getProduct_path(),products.getType(),products.getTel()};
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
