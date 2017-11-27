package com.xpy.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageUtils {
	
	/**
	 * ͼƬת����base64�ַ���
	 * @param filepath
	 * @return
	 * @throws IOException 
	 */
	public static String EncoderImageToStr(String filepath) throws Exception {
		// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
		InputStream in = null;
		byte[] data = null;
		// ��ȡͼƬ�ֽ�����
		try {
			in = new FileInputStream(filepath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			throw new Exception("ͼƬת��base64�ַ�������");
		}
		// ���ֽ�����Base64����
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// ����Base64��������ֽ������ַ���
	}

	/**
	 * base64�ַ���ת����ͼƬ
	 * @param imgStr
	 * @return  byte[]
	 * @throws Exception 
	 */
	public static byte[] DecodeStrToImage(String imgStr) throws Exception { // ���ֽ������ַ�������Base64���벢����ͼƬ
		if (imgStr == null)
		{
			// ͼ������Ϊ��
			throw new Exception("ͼ������Ϊ��");
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64����
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// �����쳣����
					b[i] += 256;
				}
			}
			return b;
		} catch (Exception e) {
			throw new Exception("base64����ת��ΪͼƬʧ��");
		}
	}
}
