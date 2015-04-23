/**  
 * @Title: JsonUtils.java 
 * @Package cn.net_show.customer.util 
 * @author 王帅
 * @date 2015年3月13日 下午6:04:28  
 */ 
package cn.net_show.customer.util;

import java.util.ArrayList;

import mark.utils.Logger;

import org.json.JSONObject;
import cn.net_show.customer.model.Doctor;
import cn.net_show.customer.model.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/** 
 * @ClassName: JsonUtils 
 * @author 王帅
 * @date 2015年3月13日 下午6:04:28  
 */
public class JsonUtils {
	private static JsonUtils utils;
	private static Gson gson;
	private final static String TAG = "JSON";
	private JsonUtils(){
		
	}
	
	public static JsonUtils getInstance(){
		if(utils==null){
			utils = new JsonUtils();
			gson = new Gson();
		}
		return utils;
	}
	
	public UserInfo getLoginInfo(String json){
		
		if(json==null || json.trim().equals("")){
			return null;
		}
		try {
			JSONObject  obj = new JSONObject(json);
			int code = obj.getInt("code");
			Logger.e(TAG, obj.getString("message"));
			if(code!=0){
				return null;
			}				
			return gson.fromJson(obj.getString("user"), UserInfo.class);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<Doctor> getDoctorList(String json){
		if(json==null || json.trim().equals("")){
			return null;
		}
		try {
			JSONObject  obj = new JSONObject(json);
			int code = obj.getInt("code");
			Logger.e(TAG, obj.getString("message"));
			if(code!=0){
				return null;
			}
			return gson.fromJson(obj.getString("doctorInfos"),  new TypeToken<ArrayList<Doctor>>(){}.getType());
		}catch(Exception e){
			return null;
		}
		
	}
}
