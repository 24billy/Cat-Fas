package tw.com.billy.fastcat.core.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import tw.com.billy.fastcat.core.db.model.Response;

public class ExcelUtil {

	public static Workbook exportExcel(String title, List<Response> data) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet worksheet = workbook.createSheet("result");
		worksheet.setDefaultColumnWidth(15);

		Row head = worksheet.createRow(0);
		// CellStyle headStyle = head.getRowStyle();
		// headStyle.setAlignment(HorizontalAlignment.LEFT);

		try {
			// 基本資料
			String[] subjectInfo = new String[] { "單位名稱", "受測者病歷號", "受測者姓名", "性別", "出生日期", "年齡", "施測日期", "訪員姓名" };

			for (int i = 0; i < subjectInfo.length; i++) {
				head.createCell(i).setCellValue(subjectInfo[i]);

			}

			// 統計結果
			String[] statisicTitle = new String[] { "上肢動作_Rasch分數", "上肢動作_原始SE", "上肢動作_T分數", "上肢動作_T分數95%上限",
					"上肢動作_T分數95%下限", "上肢動作_信度", "下肢動作_Rasch分數", "下肢動作_原始SE", "下肢動作_T分數", "下肢動作_T分數95%上限", "下肢動作_T分數95%下限",
					"下肢動作_信度", "平衡_Rasch分數", "平衡_原始SE", "平衡_T分數", "平衡_T分數95%上限", "平衡_T分數95%下限", "平衡_信度", "日常活動_Rasch分數",
					"日常活動_原始SE", "日常活動_T分數", "日常活動_T分數95%上限", "日常活動_T分數95%下限", "日常活動_信度" };

			for (int i = 0; i < statisicTitle.length; i++) {
				head.createCell(i + 8).setCellValue(statisicTitle[i]);
			}

			// 作答紀錄
			String[] recordTitle = new String[] { "施測題數", "上肢第1題", "上肢第2題", "上肢第3題", "上肢第4題", "上肢第5題", "上肢第6題", "上肢第7題",
					"上肢第8題", "上肢第9題", "上肢第10題", "上肢第11題", "上肢第12題", "上肢第13題", "上肢第14題", "上肢第15題", "上肢第16題", "上肢第17題",
					"上肢第18題", "上肢第19題", "上肢第20題", "上肢第21題", "上肢第22題", "上肢第23題", "上肢第24題", "上肢第25題", "上肢第26題", "下肢第1題",
					"下肢第2題", "下肢第3題", "下肢第4題", "下肢第5題", "下肢第6題", "下肢第7題", "下肢第8題", "下肢第9題", "下肢第10題", "下肢第11題", "平衡第1題",
					"平衡第2題", "平衡第3題", "平衡第4題", "平衡第5題", "平衡第6題", "平衡第7題", "平衡第8題", "平衡第9題", "平衡第10題", "平衡第11題", "平衡第12題",
					"日常活動第1題", "日常活動第2題", "日常活動第3題", "日常活動第4題", "日常活動第5題", "日常活動第6題", "日常活動第7題", "日常活動第8題", "日常活動第9題" };

			for (int i = 0; i < recordTitle.length; i++) {
				head.createCell(i + 32).setCellValue(recordTitle[i]);
				
			}

			head.createCell(91).setCellValue("刪除與否");
			head.createCell(92).setCellValue("作答總時間(毫秒)");
			
			int rowCount = 1;

			for (int i = 0; i < data.size(); i++) {
				Row row = worksheet.createRow(rowCount);

				// 基本資料
				row.createCell(0).setCellValue(data.get(i).getExaminer().getOrganization().getName());
				row.createCell(1).setCellValue(data.get(i).getSubject().getMedicalNumber());
				row.createCell(2).setCellValue(data.get(i).getSubject().getSubjectName());
				row.createCell(3).setCellValue(data.get(i).getSubject().getGender());

				String birthday = data.get(i).getSubject().getBirthday();
				
				if (birthday != null && !StringUtils.isEmpty(birthday)) {
					int age = 0;
					
					try {
						age = getAge(birthday);
						row.createCell(4).setCellValue(birthday);
						row.createCell(5).setCellValue(age);
					} catch (Exception e) {
						row.createCell(4).setCellValue(birthday);
						row.createCell(5).setCellValue("年齡格式有誤");
					}
				}
				row.createCell(6).setCellValue(data.get(i).getStartDate());
				row.createCell(7).setCellValue(data.get(i).getExaminer().getName());

				// 統計資訊
				if (data.get(i).getAbility() != null && !StringUtils.isEmpty(data.get(i).getAbility())) {
					ArrayList<Double> ability = JsonUtil.fromJson(data.get(i).getAbility(), ArrayList.class);
					row.createCell(8).setCellValue(ability.get(0));
					row.createCell(14).setCellValue(ability.get(1));
					row.createCell(20).setCellValue(ability.get(2));
					row.createCell(26).setCellValue(ability.get(3));
				}

				if (data.get(i).getSe() != null && !StringUtils.isEmpty(data.get(i).getSe())) {
					ArrayList<Double> se = JsonUtil.fromJson(data.get(i).getSe(), ArrayList.class);
					row.createCell(9).setCellValue(se.get(0));
					row.createCell(15).setCellValue(se.get(1));
					row.createCell(21).setCellValue(se.get(2));
					row.createCell(27).setCellValue(se.get(3));
				}

				if (data.get(i).gettScore() != null && !StringUtils.isEmpty(data.get(i).gettScore())) {
					ArrayList<Double> tscore = JsonUtil.fromJson(data.get(i).gettScore(), ArrayList.class);
					row.createCell(10).setCellValue(tscore.get(0));
					row.createCell(16).setCellValue(tscore.get(1));
					row.createCell(22).setCellValue(tscore.get(2));
					row.createCell(28).setCellValue(tscore.get(3));
				}

				if (data.get(i).getUpper95() != null && !StringUtils.isEmpty(data.get(i).getUpper95())) {
					ArrayList<Double> upper95 = JsonUtil.fromJson(data.get(i).getUpper95(), ArrayList.class);
					row.createCell(11).setCellValue(upper95.get(0));
					row.createCell(17).setCellValue(upper95.get(1));
					row.createCell(23).setCellValue(upper95.get(2));
					row.createCell(29).setCellValue(upper95.get(3));
				}

				if (data.get(i).getLower95() != null && !StringUtils.isEmpty(data.get(i).getLower95())) {
					ArrayList<Double> lower95 = JsonUtil.fromJson(data.get(i).getLower95(), ArrayList.class);
					row.createCell(12).setCellValue(lower95.get(0));
					row.createCell(18).setCellValue(lower95.get(1));
					row.createCell(24).setCellValue(lower95.get(2));
					row.createCell(30).setCellValue(lower95.get(3));
				}

				if (data.get(i).getReliability() != null && !StringUtils.isEmpty(data.get(i).getReliability())) {
					ArrayList<Double> reliabiliy = JsonUtil.fromJson(data.get(i).getReliability(), ArrayList.class);
					row.createCell(13).setCellValue(reliabiliy.get(0));
					row.createCell(19).setCellValue(reliabiliy.get(1));
					row.createCell(25).setCellValue(reliabiliy.get(2));
					row.createCell(31).setCellValue(reliabiliy.get(3));
				}

				ArrayList<Double> choosedItem = JsonUtil.fromJson(data.get(i).getChoosedItem(), ArrayList.class);
				ArrayList<Double> answer = JsonUtil.fromJson(data.get(i).getAnswer(), ArrayList.class);

				// 施測題數
				row.createCell(32).setCellValue(choosedItem.size());

				for (int itemIndex = 0; itemIndex < choosedItem.size(); itemIndex++) {
					Integer rowPosition = 32 + choosedItem.get(itemIndex).intValue();
					row.createCell(rowPosition).setCellValue(answer.get(itemIndex).intValue());
				}

				boolean isDelete = (boolean) data.get(i).getIsDelete();
				
				if (isDelete) {
					row.createCell(91).setCellValue("已刪除");
				}
				
				Long reactionTime = data.get(i).getReactionTime();

				if (data.get(i).getReactionTime() != null && reactionTime != 0) {
					row.createCell(92).setCellValue(reactionTime.intValue());
				}
				
				rowCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return workbook;
	}

	private static int getAge(String birthday) {
		String[] dateStr = birthday.split("-");

		if (!StringUtils.isEmpty(birthday) && dateStr.length > 0) {
			LocalDate birthDate = LocalDate.of(Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]),
					Integer.parseInt(dateStr[2]));
			int actual = calculateAge(birthDate, LocalDate.now());

			return actual;
		}

		return 0;
	}

	private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}

}
