package com.xpy.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * �������ӹ����࣬ʹ�ñ�������ṩc3p0-config.xml
 * @author xpy
 *
 */
public class JDBCUtils {
	//����ʽ
	private static DataSource ds = new ComboPooledDataSource();
	
	/**
	 * null ��ʾû������
	 * ��Ϊnull ��ʾ������
	 * ����������ʱ����Ҫ������ֵ
	 * ����������ʱ����Ҫ��ֵΪnull
	 * ��������ʱ����dao�Ķ�������������Connection
	 */
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	public static DataSource getDataSource() {
		return ds;
	}
	
	/**
	 * dao���ñ�������ȡ����
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		/**
		 * ��������񣬷��ص�ǰ�����con
		 * ���û������ͨ�����ӳط����µ�con
		 */
		Connection con = tl.get();//��ȡ��ǰ�̵߳���������
		if(con!=null) 
			return con;
		return ds.getConnection();
	}
	
	/**
	 * ��������
	 * @throws SQLException
	 */
	public static void beginTransaction() throws SQLException {
		Connection con = tl.get();
		if(con!=null)
			throw new SQLException("�Ѿ����������񣬲����ظ�������");
		con = ds.getConnection();//��con��ֵ����ʾ����������
		con.setAutoCommit(false);//����Ϊ�ֶ��ύ
		tl.set(con);//�ѵ�ǰ�������ӷŵ�tl��
	}
	
	/**
	 * �ύ����
	 * @throws SQLException
	 */	
	public static void commitTransaction() throws SQLException {
		Connection con = tl.get();
		if(con==null)
			throw new SQLException("û�����񣬲����ύ��");
		con.commit();
		con.close();
		con=null;
		tl.remove();
	}
	
	/**
	 * �ع�����
	 * @throws SQLException
	 */
	public static void rollbackTransaction() throws SQLException {
		Connection con = tl.get();
		if(con==null) throw new SQLException("û�����񣬲��ܻع���");
		con.rollback();
		con.close();
		con=null;
		tl.remove();
	}
	
	public static void releaseConnection(Connection connection) throws SQLException {
		Connection con = tl.get();//��ȡ��ǰ�̵߳���������
		if(connection!=con) {//����������ӣ��뵱ǰ�������Ӳ�ͬ��˵��������Ӳ��ǵ�ǰ����
			if(connection!=null && !connection.isClosed()) {//�����������û�йرգ���ر�
				connection.close();
			}
		}
	}
	
	

}
