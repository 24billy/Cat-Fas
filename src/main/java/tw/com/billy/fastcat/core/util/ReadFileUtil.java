/**
 * Copyright (c) 2014 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package tw.com.billy.fastcat.core.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Billy Shih
 */
public class ReadFileUtil {

	private static final String encoding = "UTF-8";

	/**
	 * 回傳文字格式內容
	 * 
	 * @param inStream
	 * @return String
	 */
	public static String readTextFile(InputStream inStream) {
		String text = "";

		try {
			int in = inStream.available();
			byte[] btyeArray = new byte[in];

			inStream.read(btyeArray);
			text = new String(btyeArray, encoding);
			inStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return text;
	}

}