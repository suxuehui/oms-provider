package cn.com.dubbo.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class HttpClientUtils {
	/**
	 * dopost
	 * 
	 * @param url_str
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url_str, Map<String, Object> map)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			// 创建连接
			URL url = new URL(url_str);

			JSONObject obj = new JSONObject();
			obj.element("orderNo", map.get("orderNo"));
			obj.element("trackingNumber", map.get("trackingNumber"));
			obj.element("logLogisticCompanyName",
					map.get("logLogisticCompanyName"));
			obj.element("logLogisticCompanyId", map.get("logLogisticCompanyId"));
			obj.element("receiveTime", map.get("receiveTime"));

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");

			connection.connect();

			// POST请求
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());

			out.write(obj.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String lines;

			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			reader.close();
			// 断开连接
			connection.disconnect();

			return sb.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] getRequestPostBytes(HttpServletRequest request)
			throws IOException {
		int contentLength = request.getContentLength();
		/* 当无请求参数时，request.getContentLength()返回-1 */
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {
			int readlen = request.getInputStream().read(buffer, i,
					contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}

	/**
	 * dopost
	 * 
	 * @param url_address
	 * @param request_body
	 * @return
	 * @throws Exception
	 */
	public static String do_post(String url_address, String request_body)
			throws Exception {
		String body = "";
		// Configure and open a connection to the site you will send the request
		// java.net.URL java.net.URLConnection
		URL url = new URL(url_address);
		URLConnection urlConnection = url.openConnection();
		// 设置doOutput属性为true表示将使用此urlConnection写入数据
		urlConnection.setDoOutput(true);
		// 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
		urlConnection.setRequestProperty("content-type",
				"application/x-www-form-urlencoded");
		// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(
				urlConnection.getOutputStream());
		// 把数据写入请求的Body
		out.write(request_body);
		out.flush();
		out.close();
		// 从服务器读取响应
		InputStream inputStream = urlConnection.getInputStream();
		body = ConvertStream2Json(inputStream);
		return body;
	}

	private static String ConvertStream2Json(InputStream inputStream) {
		String jsonStr = "";
		// ByteArrayOutputStream相当于内存输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		try {
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
			}
			// 将内存流转换为字符串
			jsonStr = new String(out.toByteArray(),"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			// log.error(e.getMessage(),e);
		}
		return jsonStr;
	}
	
	
	/**
	 * post请求
	 */
	public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
//            System.out.println(result);
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
	
	
	
	
	 
	public static void main(String[] args) throws Exception {
//		new HttpClientUtils().httpUrlConnection("http://192.168.100.69:3089/oms-weixin/pushorder/queryOrders.do");
		String syt = HttpClientUtils.do_post("http://192.168.90.201:8117/order-provider/orders/synchro.html", "beiginTime=2016-05-05 00:00:00&endTime=2016-10-15 00:00:00&pageIndex=1&orderStatus=5");
		System.out.println(syt);
	}

}
