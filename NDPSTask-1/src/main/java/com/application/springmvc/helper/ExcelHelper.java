package com.application.springmvc.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.application.springmvc.entity.Player;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static String[] HEADERS = {"ID","Name","Email"};
	public static String SHEET = "Player";
	
	public static boolean hasExcelFormat(MultipartFile file)
	{
		log.info(file.getContentType());
		if(!TYPE.equals(file.getContentType()))
			return false;
		return true;
	}
	
	public static List<Player> excelRead(InputStream is)
	{
		try(Workbook workbook = new XSSFWorkbook(is))
		{
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();
			
			List<Player> players = new ArrayList<>();
			
			int rowNumber = 0;
			while(rows.hasNext())
			{
				Row currentRow = rows.next();
				
				if(rowNumber == 0)
				{
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				Player player = new Player();
				int cellIdx = 0;
				while(cellsInRow.hasNext())
				{
					Cell currentCell = cellsInRow.next();
//					if(cellIdx > 2)
//						break;
					switch(cellIdx)
					{
						case 0 -> player.setId((int) currentCell.getNumericCellValue());		
						case 1 -> player.setName(currentCell.getStringCellValue());
						case 2 -> player.setEmail(currentCell.getStringCellValue());
					}
					cellIdx++;
				}
				players.add(player);
			}
//			workbook.close();
			return players;
		}
		catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException("Failed to parse the excel file: "+e.getMessage());
		}
	}
	
	
//	@PostMapping("/upload")
//	public ResponseEntity<List<List<String>>> uploadExcelFile(@RequestParam("excelFile") 
//							MultipartFile excelFile)
//	{
//		if(excelFile.isEmpty())
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		
//		List<List<String>> excelData = new ArrayList<>();
//		//Processing the excel file
//		try
//		{
//			Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
//			Sheet sheet = workbook.getSheetAt(0);
//			Iterator<Row> rows = sheet.iterator();
//			while(rows.hasNext())
//			{
//				Row currentRow = rows.next();
//				Iterator<Cell> cells = currentRow.iterator();
//				List<String> rowData = new ArrayList<>();
//				while(cells.hasNext())
//				{
//					Cell currentCell = cells.next();
//					rowData.add(String.valueOf(currentCell.getNumericCellValue()));
//					rowData.add(currentCell.getStringCellValue());
//					rowData.add(currentCell.getStringCellValue());
//				}
//				excelData.add(rowData);
//			}
//			return new ResponseEntity<>(excelData, HttpStatus.OK);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
}
