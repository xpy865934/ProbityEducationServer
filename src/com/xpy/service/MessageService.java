package com.xpy.service;

import com.xpy.dao.MessageDao;
import com.xpy.domain.Message;

/**
 * ������ϢService
 * @author xpy
 *
 */
public class MessageService {
	/**
	 * ����������Ϣ
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
