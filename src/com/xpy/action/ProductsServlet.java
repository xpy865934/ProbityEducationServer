package com.xpy.action;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xpy.domain.Products;
import com.xpy.service.ProductsService;
import com.xpy.utils.GetRequestJsonUtils;

/**
 * ��ȡ��Ʒ��Ϣ
 * @author xpy
 *
 */
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//���ñ���
		response.setCharacterEncoding("utf-8");
		
		String request_json = GetRequestJsonUtils.getRequestPostStr(request);
		
		Gson gson = new Gson();
		
		JsonObject jsonObject = new JsonParser().parse(request_json).getAsJsonObject();
		
		//��ȡ���е���Ʒ
		ProductsService productsService = new ProductsService();
		try {
			List<Products> allProducts = productsService.selectProductsByType(jsonObject.get("type").getAsString());
			String json = gson.toJson(allProducts);
			//���췵�ص�json
			json="{\"result\":"+json+"}";
			response.getWriter().write(json);			
		} catch (Exception e) {
			System.err.println("��ѯ��Ʒ��Ϣ����");
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
