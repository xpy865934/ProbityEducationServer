package com.xpy.service;

import java.sql.SQLException;

import com.xpy.dao.MessageDao;
import com.xpy.dao.ProductsDao;
import com.xpy.domain.Message;
import com.xpy.domain.Products;

public class MessageService {
	/**
	 * ≤Â»Î¡Ù—‘–≈œ¢
	 * @param message
	 * @return
	 * @throws Exception 
	 */
	public int insertMessage(Message message) throws Exception {
		MessageDao messageDao = new MessageDao();
		int insert = messageDao.insert(message);
		return insert;
	}
}
