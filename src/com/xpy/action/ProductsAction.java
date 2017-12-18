package com.xpy.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionSupport;
import com.xpy.domain.Products;
import com.xpy.service.ProductsService;
import com.xpy.utils.GetRequestJsonUtils;

/**
 * ��Ʒ
 * @author xpy
 *
 */
public class ProductsAction extends ActionSupport{

	private static final long serialVersionUID = -624809692321209319L;
	
	/**
	 * ��ʼ����Ʒ���ݱ�����ݿ�����
	 * @return
	 */
	public String init() {
		// �����ݿ��в�����Ʒ��Ϣ
		ProductsService productsService = new ProductsService();
		// ��ȡ�ļ�·��
		String t = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		int num = t.indexOf(".metadata");
		int id = 0;
		String path = t.substring(1, num).replace('/', '\\') + "ProbityEducationServer\\WebContent\\products_image";

		File file = new File(path);

		File[] images = file.listFiles();
		for (File file2 : images) {
			String name = file2.getName();
			String name1 = name.substring(0, name.lastIndexOf("."));
			String[] params = name1.split("--");
			Products products = new Products();

			try {
				id = productsService.selectProductMaxId() + 1;
				products.setId(id);
			} catch (SQLException e1) {
				System.err.println("��ȡProductMaxId����");
				e1.printStackTrace();
			}
			try {
				if(params[0].trim().length()!=0) {
					products.setProduct_name(params[0]);
				}
				if(params[1].trim().length()!=0) {
					products.setBanji(params[1]);
				}
				if(params[2].trim().length()!=0) {
					products.setAuthor(params[2]);
				}
				if(params[3].trim().length()!=0) {
					products.setDepartment(params[3]);
				}
				if(params[4].trim().length()!=0) {
					products.setType(params[4]);
				}
				if(params[5].trim().length()!=0) {
					products.setTel(params[5]);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(name);
			}

			// ע����������Ҫ·��һ��
			File newFile = new File(file2.getParent() + "/" + id + name.substring(name.lastIndexOf(".")));

			// ������
			file2.renameTo(newFile);
			products.setProduct_path("/products_image/" + newFile.getName());
			try {
				productsService.insertProduct(products);
			} catch (Exception e) {
				System.err.println("��������Ʒ��Ϣ����");
				e.printStackTrace();
			}
		}
		return NONE;
	}
	
	/**
	 * ��������ȡ��Ʒ��Ϣ
	 * @return
	 */
	public String getAllByType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		
		String request_json="";
		try {
			request_json = GetRequestJsonUtils.getRequestPostStr(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
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
		return NONE;
	}

}
