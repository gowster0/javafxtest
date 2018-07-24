package application;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import javafx.scene.control.Alert;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostServer {
	// json格式
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//	private JSONObject returnJSON;
	
	public static JSONObject doPostServer(String servlet, Map map) throws IOException{
		JSONObject returnJSON = null;
		OkHttpClient client = new OkHttpClient();
		// 初始化请求体
	    okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, JSONObject.toJSON(map).toString());
	    // 初始化request
	    Request request = new Request.Builder()
	        .url(servlet)
	        .post(body)
	        .build();
	    try {
	      // 发起请求
	      Response response = client.newCall(request).execute();
	      // 提取返回json
	      returnJSON = JSONObject.parseObject(response.body().string());
	      
	    } catch (IOException e) {
	    	Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("服务器错误");
			alert.show();
//	      e.printStackTrace();
	    	throw e;
	    }	
	    return returnJSON;
	}
	
//	public JSONObject getReturn(){
////		System.out.println(returnJSON.getString(key));
//		
//	}
	
}
