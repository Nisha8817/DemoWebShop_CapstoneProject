package pages;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelLoginUtility {
	private static final String FILE_PATH = new java.io.File("src/test/resources/UserCredentials.xlsx").getAbsolutePath();

    public static Map<String, String> getRowData(String rowId) {
        Map<String, String> dataMap = new HashMap<>();
        try (FileInputStream file = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = WorkbookFactory.create(file)) {
            
            Sheet sheet = workbook.getSheet("LoginSheet");
            int rowCount = sheet.getPhysicalNumberOfRows();
            
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0).getStringCellValue().equalsIgnoreCase(rowId)) {
                    Cell userCell = row.getCell(1);
                    Cell passCell = row.getCell(2);
                    
                    // Safety check to handle blank cell formats without throwing NullPointerException
                    dataMap.put("Username", userCell != null ? userCell.getStringCellValue() : "");
                    dataMap.put("Password", passCell != null ? passCell.getStringCellValue() : "");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Excel parsing read exception error: " + e.getMessage());
        }
        return dataMap;
    }

    public static void writeTestStatus(String rowId, String status) {

        try {

            File file = new File(FILE_PATH);

            FileInputStream fis = new FileInputStream(file);

            Workbook workbook = WorkbookFactory.create(fis);

            Sheet sheet = workbook.getSheet("LoginSheet");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                Cell rowIdCell = row.getCell(0);

                if (rowIdCell != null &&
                    rowIdCell.getStringCellValue().trim()
                            .equalsIgnoreCase(rowId)) {

                    Cell resultCell = row.getCell(3);

                    if (resultCell == null) {
                        resultCell = row.createCell(3);
                    }

                    resultCell.setCellValue(status);

                    break;
                }
            }

            fis.close();

            FileOutputStream fos = new FileOutputStream(file);

            workbook.write(fos);

            fos.flush();
            fos.close();

            workbook.close();

            System.out.println(
                    "Excel Updated Successfully => "
                    + rowId
                    + " : "
                    + status);

        }
        catch (Exception e) {

            e.printStackTrace();

            System.out.println(
                    "Excel Write Failed : "
                    + e.getMessage());
        }
    }
}
