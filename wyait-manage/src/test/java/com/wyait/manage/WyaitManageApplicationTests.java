package com.wyait.manage;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WyaitManageApplicationTests {

	/**
	 * 65536行数据耗时：2.815s
	 * 最多只能创建65536行
	 */

	@Test
	public void testRead07() throws Exception {
		FileInputStream fis = new FileInputStream( "D:\\data.xls");



		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(1);
		Cell cell0 = row.getCell(0);
		double value0 = cell0.getNumericCellValue();
		System.out.println(value0);
		Cell cell1 = row.getCell(1);
		String value1 = cell1.getStringCellValue();
		System.out.println(value1);
		fis.close();


	}

}
