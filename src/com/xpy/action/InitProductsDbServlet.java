package com.xpy.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xpy.domain.Products;
import com.xpy.service.ProductsService;

/**
 * Servlet implementation class InitProductsDbServlet
 */
public class InitProductsDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//往数据库中插入作品信息
		ProductsService productsService = new ProductsService();
		String t = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		int num = t.indexOf(".metadata");
		String path = t.substring(1, num).replace('/', '\\') + "ProbityEducationServer\\WebContent\\products_image";

		File file = new File(path);
		System.out.println(file.getAbsolutePath());

		File[] images = file.listFiles();
		System.out.println(images.length);
		for (File file2 : images) {
			String name = file2.getName();
			String name1 = name.substring(0,name.lastIndexOf("."));
			System.out.println(name);
			System.out.println(name1);
			String[] params = name1.split("--"); 
			Products products = new Products();
			products.setProduct_name(params[0]);
			products.setBanji(params[1]);
			products.setAuthor(params[2]);
			products.setDepartment(params[3]);
			products.setType(params[4]);
			products.setTel(params[5]);
			products.setProduct_path(getServletContext().getContextPath() + "/products_image/"+name);
			try {
				productsService.insertProduct(products);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
