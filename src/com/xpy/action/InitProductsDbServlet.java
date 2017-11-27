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
		String id="";
		String path = t.substring(1, num).replace('/', '\\') + "ProbityEducationServer\\WebContent\\products_image";

		File file = new File(path);

		File[] images = file.listFiles();
		for (File file2 : images) {
			String name = file2.getName();
			String name1 = name.substring(0,name.lastIndexOf("."));
			String[] params = name1.split("--"); 
			Products products = new Products();
			
			try {
				id = String.valueOf(productsService.selectMessageMaxId()+1);
				products.setId(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			products.setProduct_name(params[0]);
			products.setBanji(params[1]);
			products.setAuthor(params[2]);
			products.setDepartment(params[3]);
			products.setType(params[4]);
			products.setTel(params[5]);

			//注意重命名需要路径一致
			File newFile = new File(file2.getParent() + "/"+id+name.substring(name.lastIndexOf(".")));
			
			file2.renameTo(newFile);
			products.setProduct_path(getServletContext().getContextPath() + "/products_image/"+newFile.getName());
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
