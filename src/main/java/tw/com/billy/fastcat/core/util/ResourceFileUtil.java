package tw.com.billy.fastcat.core.util;

import java.io.InputStream;

public enum ResourceFileUtil {

	SQL("sql"); // SQL檔案

	private String target; // 指定資料夾

	private static String rootDir = "template";

	/** separator */
	private static String separator = "/";

	private ResourceFileUtil(String target) {
		this.target = target;
	}

	/**
	 * 根據指定的[路徑/檔案名稱]回傳[檔案]內容(文字). <br>
	 * 
	 * @param params
	 * @return String
	 */
	public final String getResource(String[] params, String fileNm) {
		String[] cpParams = new String[params.length + 1];

		System.arraycopy(params, 0, cpParams, 0, params.length);
		cpParams[cpParams.length - 1] = fileNm;

		return getResource(cpParams);
	}

	/**
	 * 根據指定的[路徑/檔案名稱]回傳[檔案]內容(文字). <br>
	 * 
	 * @param params
	 * @return String
	 */
	public final String getResource(String... params) {
		InputStream inStr = getResourceAsInputStream(params);

		if (inStr != null) {
			return ReadFileUtil.readTextFile(inStr);
		}

		return "";
	}

	/**
	 * 根據指定的[路徑/檔案名稱]回傳[檔案]內容(InputStream). <br>
	 * 
	 * @param params
	 * @return InputStream
	 */
	public final InputStream getResourceAsInputStream(String... params) {
		if (params != null && params.length != 0) {
			StringBuilder sb = new StringBuilder(separator + rootDir + separator + target + separator);

			int len = params.length;
			int count = 0;

			for (String index : params) {
				sb.append(index);
				count++;
				if (count < len) {
					sb.append(separator);
				}
			}

			sb.append("." + target);

			// 找到sql檔案
			InputStream inStr = ResourceFileUtil.class.getResourceAsStream(sb.toString());

			return inStr;
		}

		return null;
	}

}
