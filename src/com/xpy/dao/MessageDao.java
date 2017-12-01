package com.xpy.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.xpy.domain.Message;
import com.xpy.utils.ImageUtils;
import com.xpy.utils.JDBCUtils;

/**
 * MessageDao
 * @author xpy
 *
 */
public class MessageDao {
	/**
	 * ����������Ϣ
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public int insert(Message message) throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(ImageUtils.DecodeStrToImage(message.getImage()));
		Connection conn = JDBCUtils.getConnection();
		String sql = "insert into message(name,image,content,user_agent,ip,time) values(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, message.getName());
		ps.setBinaryStream(2, in,in.available());
		ps.setString(3, message.getContent());
		ps.setString(4, message.getUser_agent());
		ps.setString(5, message.getIp());
		ps.setTimestamp(6, new Timestamp(message.getTime().getTime()));
		int update = ps.executeUpdate();
		return update;
	}
}
