package com.xpy.action;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.google.gson.Gson;
import com.xpy.domain.Products;
import com.xpy.service.ProductsService;

/**
 * Servlet implementation class ProductsServlet
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
		
		//获取所有的商品
		ProductsService productsService = new ProductsService();
		try {
			List<Products> allProducts = productsService.selectAllProducts();
			Gson gson = new Gson();
			String json = gson.toJson(allProducts);
			response.getWriter().write(json);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
