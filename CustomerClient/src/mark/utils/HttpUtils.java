﻿package mark.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//Http请求的工具类
public class HttpUtils {

	private static final int TIMEOUT_IN_MILLIONS = 5000;

	public interface CallBack {
		void onRequestComplete(String result);
	}

	/**
	 * 异步的Get请求
	 * @param urlStr
	 * @param callBack
	 */
	public static void doGetAsyn(final String urlStr, final CallBack callBack) {
		new Thread() {
			public void run() {
				try {
					String result = doGet(urlStr);
					Logger.v("urlStr",result);
					if (callBack != null) {
						callBack.onRequestComplete(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (callBack != null) {
						callBack.onRequestComplete("");
					}
				}

			};
		}.start();
	}

	/**
	 * 异步的Post请求
	 * 
	 * @param urlStr
	 * @param params
	 * @param callBack
	 * @throws Exception
	 */
	public static void doPostAsyn(final String urlStr, final String params,
			final CallBack callBack){
		new Thread() {
			public void run() {
				try {
					String result = doPost(urlStr, params);
					Logger.v("urlStr",result);
					if (callBack != null) {
						callBack.onRequestComplete(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (callBack != null) {
						callBack.onRequestComplete("");
					}
				}

			};
		}.start();

	}

	/**
	 * Get请求，获得返回数据
	 * 
	 * @param urlStr
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String urlStr) {
		URL url = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
			conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				baos = new ByteArrayOutputStream();
				int len = -1;
				byte[] buf = new byte[128];

				while ((len = is.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				baos.flush();
				return baos.toString();
			} else {
				throw new RuntimeException(" responseCode is not 200 ... ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null)
					baos.close();
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			conn.disconnect();
		}

		return null;

	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws Exception
	 */
	public static String doPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		HttpURLConnection conn = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("charset", "utf-8");
			conn.setUseCaches(false);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
			conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);

			if (param != null && !param.trim().equals("")) {
				// 获取URLConnection对象对应的输出流
				out = new PrintWriter(conn.getOutputStream());
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			conn.disconnect();
		}
		return result;
	}
	
	public static boolean download(String url,String fileName,File cacheDir){
		try {
			URL uc = new URL(url);
			File file = new File(cacheDir,System.currentTimeMillis()+"");
			OutputStream os = new FileOutputStream(file);
			InputStream is = uc.openStream();
			byte[] buff = new byte[1024];
			int hasRead = 0;
			while ((hasRead = is.read(buff)) > 0) {
				os.write(buff, 0, hasRead);
			}
			is.close();
			os.close();
			file.renameTo(new File(cacheDir,fileName));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
