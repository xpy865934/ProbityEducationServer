package com.xpy.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xpy.domain.Message;
import com.xpy.service.MessageService;
import com.xpy.utils.GetRequestJsonUtils;

/**
 * 插入新的留言信息
 * @author xpy
 *
 */
public class InsertMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String json = GetRequestJsonUtils.getRequestPostStr(request);
		
		Gson gson = new Gson();
		
		Message message = gson.fromJson(json, Message.class);
		
		//获取ip地址和客户端
		String ip = request.getRemoteAddr();
		String userAgent = request.getHeader("user-agent");
		
		message.setIp(ip);
		message.setUser_agent(userAgent);
		message.setTime(new Date());
		
		MessageService messageService = new MessageService();
		try {
			messageService.insertMessage(message);
		} catch (Exception e) {
			System.err.println("数据库插入留言信息出错！");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
