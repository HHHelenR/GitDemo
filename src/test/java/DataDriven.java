import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public static void main(String[] args) throws IOException {
		
		//can you see?

	}

	public ArrayList<String> getData(String testcaseName) throws IOException {
	
		//for git hub demo purpose
	ArrayList<String> a = new ArrayList<String>();
	
	FileInputStream fis = new FileInputStream("C:\\Users\\hhele\\OneDrive\\Desktop\\Udemy\\DataDriven\\Book1.xlsx");

	XSSFWorkbook workbook = new XSSFWorkbook(fis);

	// get the number of sheets in Excel
	int sheets = workbook.getNumberOfSheets();

	for (int i = 0; i < sheets; i++) {
		if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
			XSSFSheet sheet = workbook.getSheetAt(i);

			// identify test cases column by scanning the entire 1st row
			Iterator<Row> rows = sheet.iterator(); // sheet is a collection of rows

			// get access to first row
			Row firstrow = rows.next();
			
			Iterator<Cell> ce = firstrow.cellIterator(); // ce is collection of cells
			
			int k = 0;
			int column = 0;
			
			// look for row that is called Testcases
			while (ce.hasNext()) {
				Cell value = ce.next();
				if (value.getStringCellValue().equalsIgnoreCase("testcases")) {
					column = k;			
				}
				k++;
			}
			System.out.println(column);
			
			
			// once column is identified then scan the entire test case column to identify
			while(rows.hasNext()) {
				Row r = rows.next();
				
				// purchase test case row
				if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
					// after you grab purchase test case row = pull all the data of that row and feed into test
					Iterator<Cell> cv = r.cellIterator();
					
					while(cv.hasNext()) {
						
						//check if the cell is numerical
						Cell c = cv.next();
						if(c.getCellTypeEnum()==CellType.STRING) {
							//adding string cell value to array list
							a.add(c.getStringCellValue());
						}else {
							//adding numerical cell value to array list
							//first convert number to string then add to array list
							a.add( NumberToTextConverter.toText(c.getNumericCellValue()));
							
						}
						
						
					}
				}
			}
			
		}
	}
	return a;
	
	}	
}
