package tw.com.billy.fastcat;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tw.com.billy.fastcat.core.business.service.IResponseService;
import tw.com.billy.fastcat.core.db.model.Response;
import tw.com.billy.fastcat.core.db.model.Subject;
import tw.com.billy.fastcat.core.util.ExcelUtil;

public class ExcelExportTest extends SpringTest {

	@Autowired
	IResponseService responseService;

	@Test
	public void generateExcel() {
		// All
//		List<Response> data = responseService.getAllCompletedResponse();
		
		// by ExaminerId
//		Subject subject = new Subject();
//		subject.setCreateMemberId(1);
//		
//		List<Response> data = responseService.getAllCompletedResponseByExaminerId(subject);
		
		Subject subject = new Subject();
		subject.setSubjectId(1);
		
		List<Response> data = responseService.getAllCompletedResponseBySubjectId(subject);
				
		for (Response response : data) {
			System.out.println(response);
		}

		try {
			Workbook workbook = ExcelUtil.exportExcel("CAT-FAS_輸出", data);
			String filename = "E:\\CHT\\NewExcelFile.xlsx";
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
