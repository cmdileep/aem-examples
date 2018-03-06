package com.dil.nit.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.SlingServletException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;

@SlingServlet(paths = "/bin/services/excelread")

public class ExcelFileReaderServelet extends SlingAllMethodsServlet {

	Logger log = LoggerFactory.getLogger(ExcelFileReaderServelet.class);

	@Override
	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp)
			throws IOException, SlingServletException {
		resp.setContentType("text/html");
		resp.getWriter().write("Inside servlet");

		String excelPath = req.getParameter("filepath");

		if (StringUtils.isBlank(excelPath)) {
			resp.getWriter().write("provided dam is path is empty");
			return;

		}
		Resource assetResource = req.getResourceResolver().getResource(excelPath);
		Asset asset = assetResource.adaptTo(Asset.class);
		Rendition original = asset.getOriginal();
		InputStream stream = null;
		if (original != null) {
			stream = original.getStream(); 
		}

	
		try {
			if (stream != null) {
				Workbook workbook = WorkbookFactory.create(stream);
				resp.getWriter().write("The workbook is");
				resp.getWriter().write("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
				Sheet sheet = workbook.getSheetAt(0);

				// Create a DataFormatter to format and get each cell's value as String
				DataFormatter dataFormatter = new DataFormatter();

				// 1. You can obtain a rowIterator and columnIterator and iterate over them
				resp.getWriter().write("\n\nIterating over Rows and Columns using Iterator\n");
				Iterator<Row> rowIterator = sheet.rowIterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();

					// Now let's iterate over the columns of the current row
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						String cellValue = dataFormatter.formatCellValue(cell);
						resp.getWriter().write(cellValue + "&nbsp;&nbsp;&nbsp;");
					}
					resp.getWriter().write("<br/>");
				}

				// 2. Or you can use a for-each loop to iterate over the rows and columns
				resp.getWriter().write("\n\nIterating over Rows and Columns using for-each loop\n");
				for (Row row : sheet) {
					for (Cell cell : row) {
						String cellValue = dataFormatter.formatCellValue(cell);
						resp.getWriter().write(cellValue + "&nbsp;&nbsp;&nbsp;");
					}
					resp.getWriter().write("<br/>");
				}

				// 3. Or you can use Java 8 forEach loop with lambda
				System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");

			}

		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
