package com.xpy.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xpy.domain.Message;
import com.xpy.service.MessageService;
import com.xpy.utils.GetRequestJsonUtils;

public class MessagesAction extends ActionSupport {

	private static final long serialVersionUID = -2356365880920934403L;

	public String insert() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		try {
			String json = GetRequestJsonUtils.getRequestPostStr(request);

			Gson gson = new Gson();

			Message message = gson.fromJson(json, Message.class);

			// ��ȡip��ַ�Ϳͻ���
			String ip = request.getRemoteAddr();
			String userAgent = request.getHeader("user-agent");

			message.setIp(ip);
			message.setUser_agent(userAgent);
			message.setTime(new Date());

			MessageService messageService = new MessageService();
		} catch (Exception e) {
			System.err.println("���ݿ����������Ϣ����");
			e.printStackTrace();
		}
		return NONE;
	}
}
