package cz.cvut.fel.vyzkumodolnosti.xls;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsFileExporter {

	public File exportToXlsFile(List<XlsRowDto> sleepXlsRowDtoList) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		// styles
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// font
		XSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 11);
		font.setBold(true);
		headerStyle.setFont(font);

		CellStyle style = workbook.createCellStyle();

		Row row;

		// each sheet
		Sheet sheet = null;
		Row header;
		boolean diffResearchNumeber = true;
		int rowIndex = 0;

		for (int i = 0; i < sleepXlsRowDtoList.size(); i++) {
			XlsRowDto e = sleepXlsRowDtoList.get(i);

			if (diffResearchNumeber) {
				// create new sheet
				sheet = workbook.createSheet(e.getResearchNumberValue());
				sheet.setDefaultColumnWidth(20);
				header = sheet.createRow(0);
				putValuesIntoHeaderRow(header, headerStyle, e);
				rowIndex = 0;
			}

			rowIndex++;
			row = sheet.createRow(rowIndex);

			putValuesIntoRow(style, row, e);

			// the operations out of this would otherwise cause out of bounds error
			if (i + 1 >= sleepXlsRowDtoList.size()) {
				break;
			}

			String nextReseachNumber = sleepXlsRowDtoList.get(i + 1).getResearchNumberValue();
			String currentReseachNumber = e.getResearchNumberValue();
			diffResearchNumeber = !currentReseachNumber.equals(nextReseachNumber);
		}
		 File file = File.createTempFile("templ", ".xlsx");
		 try (FileOutputStream outputStream = new FileOutputStream(file)){
			 workbook.write(outputStream);
			 workbook.close();
			 return file;
		 }
	}

	private void putValuesIntoRow(
			CellStyle style,
			Row row,
			XlsRowDto rowDto
	) {
		Cell cell;
		int index = 0;
		for (XlsDto dto : rowDto.getParameters()) {
			cell = row.createCell(index);
			cell.setCellValue(dto.getValue().toString());
			cell.setCellStyle(style);
			index++;
		}
	}

	private void putValuesIntoHeaderRow(
			Row header,
			CellStyle headerStyle,
			XlsRowDto rowDto
	) {
		Cell headerCell;
		int index = 0;
		for (XlsDto dto : rowDto.getParameters()) {
			headerCell = header.createCell(index);
			headerCell.setCellValue(dto.getName());
			headerCell.setCellStyle(headerStyle);
			index++;
		}
	}
}