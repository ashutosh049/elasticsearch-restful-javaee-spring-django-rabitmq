package com.iscas.dsgroup.securitysearch.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.iscas.dsgroup.securitysearch.pojo.CommonParam;

/***
 * 
 * HTTP 处理相关的临时先放在这里，后期进行完善和提炼 现在仅满足post发送到文档处理服务器
 * 
 */

public class HttpCommonHelper {
	public static boolean sendNewFileTasks(String fileIndex, String fileType, CommonParam commonParam) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://127.0.0.1:8000/core/newfile/");
		// 拼接参数
        JsonArray arry = new JsonArray();  
        JsonObject j = new JsonObject();  
        j.addProperty("fileIndex", fileIndex);  
        j.addProperty("fileType", fileType);  
        j.addProperty("fileId", commonParam.getFilePojo().getFileID());  
        j.addProperty("fileMd5", "1234567890");  //暂时不做校验，以后接口添加
        j.addProperty("fileRemotePath", commonParam.getFilePojo().getFilePath()+commonParam.getFilePojo().getFileName());  
        arry.add(j);  

		CloseableHttpResponse response;
		try {
			
			httpPost.addHeader("Content-type","application/json; charset=utf-8");  
			httpPost.setHeader("Accept", "application/json");  
			httpPost.setEntity(new StringEntity(arry.toString(), Charset.forName("UTF-8")));   

			response = httpclient.execute(httpPost);
             int statusCode = response.getStatusLine().getStatusCode();  
               
             if (statusCode != HttpStatus.SC_OK) {  
                 return true;
             }else{
            	 return false;
             }

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}