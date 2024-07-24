package com.ashokit.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.SearchOutput;
import com.ashokit.binding.SearchRequest;
import com.ashokit.report.ExcelReportGenerator;
import com.ashokit.report.PdfReportGenerator;
import com.ashokit.service.IDynamicSearchService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/search-api")
@CrossOrigin(origins = "http://localhost:4200")
public class DynamicSearchOperationController {
	@Autowired
	private IDynamicSearchService service;
	
	@PostMapping("/search")
	public ResponseEntity<List<SearchOutput>> getPlans(@RequestBody SearchRequest request){
		//use service
		List<SearchOutput> list=service.searchPlans(request);
		return new ResponseEntity<List<SearchOutput>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/planname")
	public ResponseEntity<List<String>> showPlanNames(){
		//user service
		return new ResponseEntity<List<String>>(service.getUniquePlanNames(), HttpStatus.OK);
	}
	
	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> showPlanStatus(){
		//use service
		return new ResponseEntity<List<String>>(service.getUniquePlanStatuses(), HttpStatus.OK);
	}
	
	@GetMapping("/excelreport")
	public void generateExcelReport(HttpServletResponse response ) {
		try {
			response.setContentType("application/octet-stream");
			DateFormat formater=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime=formater.format(new Date());
			
			String headerKey="Content-Disposition";
			String headerValue="attachment; filename= plans_"+currentDateTime+ ".xlsx";
			response.setHeader(headerKey, headerValue);
			
			List<SearchOutput> listOutput=service.searchPlans(null);
			ExcelReportGenerator reportGenerator=new ExcelReportGenerator();
			reportGenerator.export(listOutput, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/pdfreport")
	public void generatePdfReport(HttpServletResponse response) {
		try {
			response.setContentType("application/pdf");
			DateFormat formater=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime=formater.format(new Date());
			
			String headerKey="Content-Disposition";
			String headerValue="attachment; filename= plans_"+currentDateTime+ ".pdf";
			response.setHeader(headerKey, headerValue);
			
			List<SearchOutput> listOutput=service.searchPlans(null);
			PdfReportGenerator reportGenerator=new PdfReportGenerator();
			reportGenerator.exportPdf(listOutput, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
