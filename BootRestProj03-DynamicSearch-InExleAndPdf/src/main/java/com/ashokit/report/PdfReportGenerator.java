package com.ashokit.report;

import java.awt.Color;
import java.util.List;

import com.ashokit.binding.SearchOutput;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class PdfReportGenerator {
	public void exportPdf(List<SearchOutput> listOutput , HttpServletResponse resp) throws Exception {
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document, resp.getOutputStream());
		document.open();
		
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setStyle(18);
		font.setColor(Color.BLUE);
		
		Paragraph p=new Paragraph("List of Plans" , font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		
		PdfPTable table=new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f, 1.5f, 3.0f, 2.5f, 2.5f});   //colmn width
		table.setSpacingBefore(10);
		
		PdfPCell cell=new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font font1=FontFactory.getFont(FontFactory.HELVETICA);
		font1.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("Plan Id" , font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan Name" , font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Holder Name" , font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Holder Ssn" , font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan Status" , font1));
		table.addCell(cell);
		
		for(SearchOutput output:listOutput) {
			table.addCell(String.valueOf(output.getPlanId()));
			table.addCell(output.getPlanName());
			table.addCell(output.getHolderName());
			table.addCell(String.valueOf(output.getHolderSsn()));
			table.addCell(output.getPlanStatus());
		}
		
		document.add(table);
		document.close();
	}

}
