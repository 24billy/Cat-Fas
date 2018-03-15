package tw.com.billy.fastcat.core.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import tw.com.billy.fastcat.core.db.model.Response;

public class ExcelUtil {

	public static HSSFWorkbook exportExcel(String title, List<Response> data) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("result");

		int rowCount = 0;

		Row head = worksheet.createRow(0);

		String[] subjectInfo = new String[] { "單位名稱", "受測者病歷號", "受測者姓名", "性別", "出生日期", "年齡", "施測日期", "訪員姓名" };

		for (int i = 0; i < 8; i++) {
			head.createCell(i).setCellValue(subjectInfo[i]);
		}

		String[] statisicTitle = new String[] { "上肢動作_Rasch分數", "上肢動作_原始SE", "上肢動作_T分數", "上肢動作_T分數95%上限",
				"上肢動作_T分數95%下限", "上肢動作_信度", "下肢動作_Rasch分數", "下肢動作_原始SE", "下肢動作_T分數", "下肢動作_T分數95%上限", "下肢動作_T分數95%下限",
				"下肢動作_信度", "平衡_Rasch分數", "平衡_原始SE", "平衡_T分數", "平衡_T分數95%上限", "平衡_T分數95%下限", "平衡_信度", "日常活動_Rasch分數",
				"日常活動_原始SE", "日常活動_T分數", "日常活動_T分數95%上限", "日常活動_T分數95%下限", "日常活動_信度" };

		for (int i = 0; i < 24; i++) {
			head.createCell(i + 8).setCellValue(statisicTitle[i]);
		}

		// String[] recordTitle = new String[] { "施測題數" };

		for (int i = 0; i < data.size(); i++) {
			Row row = worksheet.createRow(++rowCount);

			// 基本資料
			row.createCell(0).setCellValue(data.get(i).getExaminer().getOrganization().getName());
			row.createCell(1).setCellValue(data.get(i).getSubject().getMedicalNumber());
			row.createCell(2).setCellValue(data.get(i).getSubject().getSubjectName());
			row.createCell(3).setCellValue(data.get(i).getSubject().getGender());
			row.createCell(4).setCellValue(data.get(i).getSubject().getBirthday());
			// row.createCell(5).setCellValue("");
			row.createCell(6).setCellValue(data.get(i).getStartDate());
			row.createCell(7).setCellValue(data.get(i).getExaminer().getName());

			// 統計資訊
			ArrayList<Double> ability = JsonUtil.fromJson(data.get(i).getAbility(), ArrayList.class);
			ArrayList<Double> se = JsonUtil.fromJson(data.get(i).getSe(), ArrayList.class);
			ArrayList<Double> tscore = JsonUtil.fromJson(data.get(i).gettScore(), ArrayList.class);
			ArrayList<Double> upper95 = JsonUtil.fromJson(data.get(i).getUpper95(), ArrayList.class);
			ArrayList<Double> lower95 = JsonUtil.fromJson(data.get(i).getLower95(), ArrayList.class);
			ArrayList<Double> reliabiliy = JsonUtil.fromJson(data.get(i).getReliability(), ArrayList.class);

			row.createCell(8).setCellValue(ability.get(0));
			row.createCell(9).setCellValue(se.get(0));
			row.createCell(10).setCellValue(tscore.get(0));
			row.createCell(11).setCellValue(upper95.get(0));
			row.createCell(12).setCellValue(lower95.get(0));
			row.createCell(13).setCellValue(reliabiliy.get(0));

			row.createCell(14).setCellValue(ability.get(1));
			row.createCell(15).setCellValue(se.get(1));
			row.createCell(16).setCellValue(tscore.get(1));
			row.createCell(17).setCellValue(upper95.get(1));
			row.createCell(18).setCellValue(lower95.get(1));
			row.createCell(19).setCellValue(reliabiliy.get(1));

			row.createCell(20).setCellValue(ability.get(2));
			row.createCell(21).setCellValue(se.get(2));
			row.createCell(22).setCellValue(tscore.get(2));
			row.createCell(23).setCellValue(upper95.get(2));
			row.createCell(24).setCellValue(lower95.get(2));
			row.createCell(25).setCellValue(reliabiliy.get(2));

			row.createCell(26).setCellValue(ability.get(3));
			row.createCell(27).setCellValue(se.get(3));
			row.createCell(28).setCellValue(tscore.get(3));
			row.createCell(29).setCellValue(upper95.get(3));
			row.createCell(30).setCellValue(lower95.get(3));
			row.createCell(31).setCellValue(reliabiliy.get(3));
		}

		return workbook;
	}

}
