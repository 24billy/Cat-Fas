package tw.com.billy.fastcat.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	/**
	 * 將傳入物件轉成JSON字串
	 * 
	 * @param src
	 * @return String
	 * @throws ParseException
	 */
	public static String toJson(Object src) {
		Gson gson = new Gson();
		String jsonStr = gson.toJson(src);

		return jsonStr;
	}

	/**
	 * 將傳JSON字串入轉成物件
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) {
		Gson gsonObj = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();

		return gsonObj.fromJson(json, classOfT);
	}
}
