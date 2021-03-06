package tw.com.billy.fastcat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;

/**
 * 抽象測試類別物件
 * 
 * @author VJChou
 */
public class AbstractTest {

    private Long startTime; // 測試啟動時間
    private Long endTime; // 測試結束時間
    private static final String getOpKeyword = "get";
    
	@Before
	public void setUp() throws Exception {
        startTime = new Date().getTime();
        System.out.println("====================== initialization =====================");
	}

	@After
	public void tearDown() throws Exception {
        System.out.println("======================== completed ========================");
        endTime = new Date().getTime();

        System.out.println("Spend time = " + (endTime - startTime) + " ms");
	}
	
	/**
	 * 打印封裝物件(Map<String, Object>)的內容物
	 * 
	 * @param dataMap
	 */
	public void showDataMap(Map<String, Object> dataMp) {
		if (dataMp != null && !dataMp.isEmpty()) {
			for (String key : dataMp.keySet()) {
				System.out.println("key = " + key + ", value = " + dataMp.get(key));
			}
		}
	}
	
	/**
	 * 打印封裝物件(List<Map<String, Object>>)的內容物
	 * 
	 * @param dataList
	 */
	public void showDataList(List<Map<String, Object>> dataList) {
		if (dataList != null && !dataList.isEmpty()) {
			for (Map<String, Object> dataMp : dataList) {

				for (String key : dataMp.keySet()) {
					System.out.println("key = " + key + ", value = " + dataMp.get(key));
				}
				System.out.println();
			}
		}
	}

	/**
	 * 打印封裝物件(Value Object)的內容物
	 * 
	 * @param pojo
	 */
	public void showPojoContent(Object pojo) {
		if (pojo != null) {
			Class<?> clazz = pojo.getClass();
			Method methods[] = clazz.getDeclaredMethods();

			try {
				for (Method index : methods) {
					String operation = null;
					Method method = null;

					if ((operation = index.getName()).startsWith(getOpKeyword)) {
						method = clazz.getMethod(operation, new Class[0]);
						method.setAccessible(true);
						Object result = method.invoke(pojo, new Object[0]); // 無參數的方法

                        String field = operation.substring(getOpKeyword.length());

						System.out.println(field + " = " + String.valueOf(result));
					}
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}		
		}
	}

}
