package common;//package common;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.*;
//
//
//import java.io.*;
//
//
//public class ExcelMethods {
//    private static int cols =100; // to verify column number
//
//    public String getDataForFirstLine(String file, String sheetName, String columnName) throws IOException, InvalidFormatException {
//        return getDataForLineByOtherData(file, sheetName, columnName, null, null);
//      }
//
//    public String getDataForLineByOtherData(String file, String sheetName, String columnNameToSearchBy, String valueToSearchBy, String columnNameToGetDataFrom) throws IOException, InvalidFormatException {
//        InputStream inp = new FileInputStream(file);
//        Workbook wb = WorkbookFactory.create(inp);
//        Sheet sheet = wb.getSheet(sheetName);
//        if(sheet != null) {
//            Row row = null;
//            Cell cell = null;
//            String result = "";
//            int rows = sheet.getPhysicalNumberOfRows();
//            int columnNameToSearchByPosition = getColumnIndex(sheet, row, cell, columnNameToSearchBy);
//            if (columnNameToSearchByPosition != -1) {
//                if (valueToSearchBy =valueToSearchBy= null){
//                    row = sheet.getRow(1);
//                    result = getCellContent(sheet, row, cell, columnNameToSearchByPosition);
//                }else {
//                    row = sheet.getRow(getCellRowIndex(sheet, row, cell, columnNameToSearchByPosition, valueToSearchBy));
//                    int columnNameTOGetDataFromPosition = getColumnIndex(sheet, row, cell, columnNameToGetDataFrom);
//                    result = getCellContent(sheet, row, cell, columnNameTOGetDataFromPosition);
//                }
//                return result;
//            } else {
//                return "Position not found";
//            }
//        }else{
//            return "Sheet not found";
//        }
//    }
//
//    public  void updateExistingExcelCellByColumn(String file, String sheetName, String columnNameToSearchBy, String valueToSearchBy, String columnNameToUpdate, String setContent) throws IOException, InvalidFormatException { //update excel to finish
//        InputStream input = null;
//        File excel = new File(file);
//        input = new FileInputStream(excel);
//        Workbook wb = WorkbookFactory.create(input);
//        Sheet sheet = wb.getSheet(sheetName);
//        if(sheet != null) {
//            Row row = sheet.getRow(0);
//            Cell cell = null;
//            int rows;
//            int columnNameToSearchByPosition = getColumnIndex(sheet, row, cell, columnNameToSearchBy);
//            rows = valueToSearchBy == null ? sheet.getLastRowNum()+1 : sheet.getLastRowNum(); // rows = sheet.getLastRowNum()+1;
////            if (valueToSearchBy == null){null
//                row = sheet.createRow(rows);
//                cell = row.createCell(columnNameToSearchByPosition);
//
//            }else{
//                row =sheet.getRow(getCellRowIndex(sheet, row, cell, columnNameToSearchByPosition, valueToSearchBy));
//                int columnNameToUpdatePosition = getColumnIndex(sheet, row, cell, columnNameToUpdate);
//                cell = row.getCell(columnNameToUpdatePosition);
//                if(cell == null) {
//                    cell = row.createCell(columnNameToUpdatePosition);
//                }
//            }
//            cell.setCellValue(setContent);
//            wb.write(new FileOutputStream(excel));
//            System.out.println("Data updated in file: "+file+"in column: "+columnNameToUpdate+ " with value: "+setContent+" ; Key coulmn: "+columnNameToSearchBy+" by value: "+valueToSearchBy);
//        }
//
//
//    }
//
//    public void inputDataInNewRowByColumnName(String file, String sheetName, String columnName, String setContent) throws IOException, InvalidFormatException {
//        updateExistingExcelCellByColumn(file, sheetName, columnName, null, null, setContent);
//        System.out.println("New row added to file: "+file+" - Column: "+columnName+" ; Inserted value: "+ setContent);
//    }
//
//    private int getColumnIndex(Sheet sheet, Row row, Cell cell, String columnName){
//        row = sheet.getRow(0);
//        int columnNamePosition = -1;
//        for (int a = 0; a < cols; a++) {
//            cell = row.getCell((short) a);
//            if (cell != null) {
//                if (cell.toString().toLowerCase().trim().equals(columnName.toLowerCase().trim())) {
//                    columnNamePosition = cell.getColumnIndex();
//                    break;
//                }
//            }
//        }
//        return columnNamePosition;
//    }
//
//    private String getCellContent(Sheet sheet, Row row, Cell cell, int columnPosition){
//        String result="";
//        if (row != null) {
//            cell = row.getCell((short) columnPosition);
//            if (cell != null) {
//                result = cell.toString();
//            }
//            return result;
//        } else {
//           return "Empty row";
//        }
//     }
//
//    private int getCellRowIndex(Sheet sheet,Row row,Cell cell, int columnPosition, String valueToSearchBy){
//        int index = 0;
//        for(int r = 0; r < sheet.getPhysicalNumberOfRows(); r++){
//            row = sheet.getRow(r);
//            if (row.getCell((short) columnPosition).toString().toLowerCase().trim().contains(valueToSearchBy.toLowerCase().trim())){
//                index = r;
//                break;
//            }
//        }
//        return index;
//    }
//
//
//}
