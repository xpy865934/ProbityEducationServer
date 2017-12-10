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
 * 初始化作品
 * 将作品信息插入到数据库中并且对作品进行重新命名
 * @author xpy
 *
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
		//获取文件路径
		String t = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		int num = t.indexOf(".metadata");
		int id = 0;
		String path = t.substring(1, num).replace('/', '\\') + "ProbityEducationServer\\WebContent\\products_image";

		File file = new File(path);

		File[] images = file.listFiles();
		for (File file2 : images) {
			String name = file2.getName();
			String name1 = name.substring(0,name.lastIndexOf("."));
			String[] params = name1.split("--"); 
			Products products = new Products();
			
			try {
				id = productsService.selectProductMaxId()+1;
				products.setId(id);
			} catch (SQLException e1) {
				System.err.println("获取ProductMaxId出错！");
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
			
			//重命名
			file2.renameTo(newFile);
			products.setProduct_path(getServletContext().getContextPath() + "/products_image/"+newFile.getName());
			try {
				productsService.insertProduct(products);
			} catch (Exception e) {
				System.err.println("插入新作品信息出错！");
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
		doGet(request, response);
	}

}
