package com.xpy.service;

import com.xpy.dao.MessageDao;
import com.xpy.domain.Message;

/**
 * 留言信息Service
 * @author xpy
 *
 */
public class MessageService {
	/**
	 * 插入留言信息
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
