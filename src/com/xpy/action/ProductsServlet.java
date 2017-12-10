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
 * 获取作品信息
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
		//设置编码
		response.setCharacterEncoding("utf-8");
		
		String request_json = GetRequestJsonUtils.getRequestPostStr(request);
		
		Gson gson = new Gson();
		
		JsonObject jsonObject = new JsonParser().parse(request_json).getAsJsonObject();
		
		//获取所有的作品
		ProductsService productsService = new ProductsService();
		try {
			List<Products> allProducts = productsService.selectProductsByType(jsonObject.get("type").getAsString());
			String json = gson.toJson(allProducts);
			//构造返回的json
			json="{\"result\":"+json+"}";
			response.getWriter().write(json);			
		} catch (Exception e) {
			System.err.println("查询作品信息出错！");
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
