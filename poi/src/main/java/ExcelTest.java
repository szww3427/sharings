package com.qunar;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author zhangruihao.zhang
 * @version v1.0.0
 * @since 2017/11/21
 */
public class ExcelTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExcelTest.class);

    @Test
    public void testExcel(){
        String excelName = "测试用";
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        wb.setSheetName(0,excelName);

        Row row0 = sheet.createRow(0);

        row0.createCell(0).setCellValue("第一列");
        row0.createCell(1).setCellValue("第二列");
        row0.createCell(2).setCellValue("第三列");
        row0.createCell(3).setCellValue("第四列");

        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("12");
        row1.createCell(1).setCellValue("13");
        row1.createCell(2).setCellValue("14");
        row1.createCell(3).setCellValue("15");


        String localDirectoryPath = "/home/zhangruihao/excel";
        String filename = "excel.xlsx";
        FileOutputStream out = null;
        try {
            LOGGER.info("file 文件位置： {}，文件名： {}", localDirectoryPath, filename);
            out = new FileOutputStream(new File(localDirectoryPath, filename));
        } catch (IOException ioe) {
            LOGGER.error("下载文件出错,请稍后重试!",ioe);
        }
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



//    public HSSFWorkbook getWorkbook(PMStaticsVo pmStaticsVo) {
//        Workbook workbook = new HSSFWorkbook();
//        Sheet sheet = workbook.createSheet();
//        workbook.setSheetName(0, SHEET_NAME);
//
//        createHead(workbook, sheet);
//        creatBody(sheet, pmStaticsVo);
//
//        return (HSSFWorkbook) workbook;
//    }
//
//    private void creatBody(Sheet sheet, PMStaticsVo pmStaticsVo) {
//        List<PMStaticsDetail> items = pmStaticsVo.getItems();
//        int i = 1;
//        for (PMStaticsDetail pmStaticsDetail : items) {
//            Row row = sheet.createRow(i);
//            row.createCell(0).setCellValue(pmStaticsDetail.getBusinessLine());
//            row.createCell(1).setCellValue(pmStaticsDetail.getActivityNo());
//            row.createCell(2).setCellValue(pmStaticsDetail.getActivityName());
//            row.createCell(3).setCellValue(pmStaticsDetail.getMerchantCode());
//            row.createCell(4).setCellValue(pmStaticsDetail.getInvestAmount().toString());
//            row.createCell(5).setCellValue(pmStaticsDetail.getCostAmount().toString());
//            row.createCell(6).setCellValue(pmStaticsDetail.getToBeConfirmedAmount().toString());
//            row.createCell(7).setCellValue(pmStaticsDetail.getNetAmount().toString());
//
//            i++;
//        }
//    }
//
//    private void createHead(Workbook workbook, Sheet sheet) {
//        Row headRow = sheet.createRow(0);
//
//        String[] heads = {"所属业务线", "营销活动编号", "营销活动名称", "商户ID", "投入金额", "使用金额", "待核对金额", "净资金"};
//        int i = 0;
//        for (String headName : heads) {
//            Font font = workbook.createFont();
//            font.setBold(true);
//            RichTextString text = new HSSFRichTextString(headName);
//            text.applyFont(font);
//            headRow.createCell(i).setCellValue(text);
//            i++;
//        }
//    }

}
