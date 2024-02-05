package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class TestDataReader {

	private static Sheet sheet;

	static {
		System.out.println(1);
		try (FileInputStream fis = new FileInputStream("src\\test\\resources\\OneLocal_testdata.xlsx")) {
			Workbook wb = WorkbookFactory.create(fis);
			System.out.println(wb);
			sheet = wb.getSheetAt(0);
			System.out.println(sheet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider(name = "onelocaldata")
    public static String[][] dataReader(Method tcname) {
        int dataRowsCount = sheet.getPhysicalNumberOfRows();
        ArrayList<String[]> similar_testcases_data = new ArrayList<>();
        
        try {
        for (int rowIndex = 0; rowIndex < dataRowsCount; rowIndex++) {
            Row r = sheet.getRow(rowIndex);
            String testCaseName = r.getCell(1).getStringCellValue();
            String testRunStatus = r.getCell(2).getStringCellValue();
            if (testCaseName.equalsIgnoreCase(tcname.getName()) 
            		&& testRunStatus.equalsIgnoreCase("yes")) {
                ArrayList<String> each_testcase_data = new ArrayList<>();
                int cellCount = r.getPhysicalNumberOfCells();
                for (int dataCellIndex = 3; dataCellIndex < cellCount; dataCellIndex++) {
                    each_testcase_data.add(r.getCell(dataCellIndex).getStringCellValue());
                }
                each_testcase_data.add(rowIndex + "");
                similar_testcases_data.add(each_testcase_data.toArray(new String[]{}));
            }
        }
        }catch (Exception e) {
        	System.out.println(similar_testcases_data);
			e.printStackTrace();
		}
        return similar_testcases_data.toArray(new String[][]{});
    }
}