package com.xpy.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageUtils {
	
	/**
	 * 图片转化成base64字符串
	 * @param filepath
	 * @return
	 * @throws IOException 
	 */
	public static String EncoderImageToStr(String filepath) throws Exception {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(filepath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			throw new Exception("图片转换base64字符串错误");
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * base64字符串转化成图片
	 * @param imgStr
	 * @return  byte[]
	 * @throws Exception 
	 */
	public static byte[] DecodeStrToImage(String imgStr) throws Exception { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null)
		{
			// 图像数据为空
			throw new Exception("图像数据为空");
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			return b;
		} catch (Exception e) {
			throw new Exception("base64数据转换为图片失败");
		}
	}
}
