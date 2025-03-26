package datas;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcelReader {
    private static final Logger logger = Logger.getLogger(ExcelReader.class.getName());

    // This method retrieves the value based on row name (e.g., "A") and column name (e.g., "B")
    public static String getValueByRowAndColumnName(String filePath, String sheetName, String rowName, String colName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);  // Load the Excel file
        Sheet sheet = workbook.getSheet(sheetName);  // Get the sheet by name
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }

        // Finding the column index based on column name (e.g., column name contains "B")
        Row headerRow = sheet.getRow(2); // The row contains headers
        int colIndex = -1;
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            String cellValue = headerRow.getCell(i).getStringCellValue();
            if (cellValue != null && cellValue.contains(colName)) {  // Check if column name contains the provided string
                colIndex = i;
                break;
            }
        }

        if (colIndex == -1) {
            throw new IllegalArgumentException("Column not found that contains: " + colName);
        }
        // Finding the row index based on row name (e.g., "A")
        int rowIndex = -1;
        // Flag to check if we found the rowName
        boolean foundRow = false;

        // Iterate through each row
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {  // Start from row 1 (assuming row 0 is header)
            Row row = sheet.getRow(i);

            if (row != null) {
                // Iterate through each cell in the row (checking all columns)
                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                    Cell cell = row.getCell(j);

                    // Ensure we handle different cell types
                    if (cell != null) {
                        String cellValue = "";
                        switch (cell.getCellType()) {
                            case STRING:
                                cellValue = cell.getStringCellValue().trim();  // Trim spaces
                                break;
                            case NUMERIC:
                                cellValue = String.valueOf(cell.getNumericCellValue()).trim();  // Trim spaces after converting to string
                                break;
                            case BOOLEAN:
                                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  // Trim spaces for boolean
                                break;
                            default:
                                cellValue = "";  // If the cell type is something else (like FORMULA), return empty
                                break;
                        }

                        // Check if the cell value contains the rowName (partial match, case insensitive)
                        if (cellValue.toLowerCase().contains(rowName.trim().toLowerCase())) {
                            foundRow = true;  // Mark that we found the rowName in this cell
                            rowIndex = i;  // Store the row index where the match was found
                            break;  // Exit the loop once the row is found
                        }
                    }
                }
            }

            // If we found the row, exit the outer loop as well
            if (foundRow) {
                break;
            }
        }

        // Retrieve the value from the specific cell
        Row targetRow = sheet.getRow(rowIndex);
        Cell targetCell = targetRow.getCell(colIndex);
        String cellValue = targetCell != null ? targetCell.toString() : "";

        workbook.close();
        fis.close();

        return cellValue;
    }

    public void getVoucherData(String filePath, String sheetName, String rowName, String colName) {
        try {
            String cellValue = getValueByRowAndColumnName(filePath, sheetName, rowName, colName);
            System.out.println("The value in row '" + rowName + "' and column '" + colName + "' is: " + cellValue);
        } catch (IOException e) {
            // Log the exception with a severity level of SEVERE
            logger.log(Level.SEVERE, "Error reading Excel data", e);
        }
    }
}
