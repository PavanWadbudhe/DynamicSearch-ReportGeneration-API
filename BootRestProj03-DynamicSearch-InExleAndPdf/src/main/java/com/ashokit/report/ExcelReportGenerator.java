package com.ashokit.report;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ashokit.binding.SearchOutput;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelReportGenerator {
	public void export(List<SearchOutput> listOutput , HttpServletResponse resp) throws IOException {
		//create WorkBook obj for excel
		XSSFWorkbook workbook=new XSSFWorkbook();
		//create sheet in workbook
		XSSFSheet sheet= workbook.createSheet("Plans");
		
		//create rows for sheet
		XSSFRow headerRow=sheet.createRow(0);
		headerRow.createCell(0).setCellValue("PLAN ID");
		headerRow.createCell(1).setCellValue("PLAN NAME");
		headerRow.createCell(2).setCellValue("HOLDER NAME");
		headerRow.createCell(3).setCellValue("HOLDER SSN");
		headerRow.createCell(4).setCellValue("PLAN STATUS");
		//create no of rows base on the data available in the list
		
		for(int i=0; i<listOutput.size(); i++) {   
			//get one by one plan recors from list
			SearchOutput plan=listOutput.get(i);
			XSSFRow dataRow=sheet.createRow(i+1);
			
			dataRow.createCell(0).setCellValue(plan.getPlanId());
			dataRow.createCell(1).setCellValue(plan.getPlanName());
			dataRow.createCell(2).setCellValue(plan.getHolderName());
			dataRow.createCell(3).setCellValue(plan.getHolderSsn());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
		}
		ServletOutputStream outputStream=resp.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
