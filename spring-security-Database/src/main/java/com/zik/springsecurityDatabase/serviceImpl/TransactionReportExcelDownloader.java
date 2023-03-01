package com.zik.springsecurityDatabase.serviceImpl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import swipe.ng.adminservice.model.Transactions;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public class TransactionReportExcelDownloader {
    public static ByteArrayInputStream generateTransactionExcel(List<Object> list) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String extension = "file.xls";
        Workbook wb = getWorkbook(extension);
        Sheet sheet = wb.createSheet("transactions_file");
        createBody(sheet, list);
        wb.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }
    public static byte[] generateByte(List<Object> list) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String extension = "file.xls";
        Workbook wb = getWorkbook(extension);
        Sheet sheet = wb.createSheet("transactions_file");
        createBody(sheet, list);
        wb.write(out);
        return out.toByteArray();
    }

    public static Workbook getWorkbook(String excelFilePath)
            throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    public static void createHeader(Row row) {
        ReportUtil.createContent(row, null, "Title", 0);
        ReportUtil.createContent(row, null, "First name", 1);
        ReportUtil.createContent(row, null, "Last name", 2);
        ReportUtil.createContent(row, null, "Description", 3);
        ReportUtil.createContent(row, null, "Channels", 4);
        ReportUtil.createContent(row, null, "PurchaseId", 5);
        ReportUtil.createContent(row, null, "Amount", 6);
        ReportUtil.createContent(row, null, "Transaction date", 7);
        ReportUtil.createContent(row, null, "Tran type", 8);
        ReportUtil.createContent(row, null, "Deduction", 9);
        ReportUtil.createContent(row, null, "Currency", 10);
        ReportUtil.createContent(row, null, "TransactionId", 11);
        ReportUtil.createContent(row, null, "Merchant name", 12);
        ReportUtil.createContent(row, null, "Status", 13);
        ReportUtil.createContent(row, null, "Balance", 14);
        ReportUtil.createContent(row, null, "Category", 15);
        ReportUtil.createContent(row, null, "Masked pan", 16);
        ReportUtil.createContent(row, null, "Reference", 17);
        ReportUtil.createContent(row, null, "Pay", 18);
        ReportUtil.createContent(row, null, "Transaction ref", 19);
        ReportUtil.createContent(row, null, "Platform", 20);
        ReportUtil.createContent(row, null, "Tranref type", 21);
        ReportUtil.createContent(row, null, "AccountId", 22);
        ReportUtil.createContent(row, null, "CardId", 23);
        ReportUtil.createContent(row, null, "Tran body", 24);
        ReportUtil.createContent(row, null, "MerchantId", 25);
        ReportUtil.createContent(row, null, "Balance before", 26);
        ReportUtil.createContent(row, null, "Balance after", 27);
    }

    private static void writeReport(Transactions rs, Row row) {
        String title ="";
        if("MALE".equals(rs.getAccountid().getGender().toUpperCase())){
            title ="Mr.";
        }else{
            title ="Mrs.";
        }
        String fullName = rs.getAccountid().getName();
        int idx = fullName.trim().lastIndexOf(' ');
        if (idx == -1) {
            throw new IllegalArgumentException("Only a single name: " + fullName);
        }
        String firstName = fullName.substring(0, idx);
        String lastName = fullName.substring(idx + 1);
        ReportUtil.createContent(row, null, title + "", 0);
        ReportUtil.createContent(row, null, firstName, 1);
        ReportUtil.createContent(row, null, lastName, 2);
        ReportUtil.createContent(row, null, rs.getDescription(), 3);
        ReportUtil.createContent(row, null, rs.getChannels(), 4);
        ReportUtil.createContent(row, null, ReportUtil.toString(rs.getPurchaseid()), 5);
        ReportUtil.createContent(row, null, ReportUtil.toString(rs.getAmount()), 6);
        ReportUtil.createContent(row, null, ReportUtil.toString(rs.getTransactiondate()), 7);
        ReportUtil.createContent(row, null, rs.getType(), 8);
        ReportUtil.createContent(row, null, ReportUtil.toString(rs.getDeduction()), 9);
        ReportUtil.createContent(row, null, rs.getCurrency(), 10);
        ReportUtil.createContent(row, null, rs.getTransactionid(), 11);
        ReportUtil.createContent(row, null, rs.getMerchantname(), 12);
        ReportUtil.createContent(row, null, rs.getStatus(), 13);
        ReportUtil.createContent(row, null, ReportUtil.toString(rs.getBalance()), 14);
        ReportUtil.createContent(row, null,rs.getCategory(), 15);
        ReportUtil.createContent(row, null, rs.getMaskedpan(), 16);
        ReportUtil.createContent(row, null, rs.getReference(), 17);
        ReportUtil.createContent(row, null, ReportUtil.toString(rs.getPay()), 18);
        ReportUtil.createContent(row, null, ReportUtil.toString(rs.getTranref()), 19);
        ReportUtil.createContent(row, null, rs.getPlatform(), 20);
        ReportUtil.createContent(row, null, rs.getTranreftype(), 21);
        ReportUtil.createContent(row, null, ReportUtil.toString(rs.getAccountid().getId()), 22);
        //ReportUtil.createContent(row, null, rs.getCardid().getCardid(), 23);
        ReportUtil.createContent(row, null, rs.getTranbody(), 24);
        ReportUtil.createContent(row, null, rs.getMerchantid(), 25);
        ReportUtil.createContent(row, null, ReportUtil.toString(rs.getBalbefore()), 26);
        ReportUtil.createContent(row, null, ReportUtil.toString(rs.getBalafter()), 27);
    }

    public static void createBody(Sheet sheet, List<Object> list) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        Row rw = sheet.createRow(0);
        createHeader(rw);
        int rowcount = 1;
        for (Object rs : list) {
            Row row = sheet.createRow(rowcount++);
            writeReport(rs, row);
        }

    }


    @Override
    public StreamingResponseBody downloadExcelTransactionByDateRange(HttpServletResponse response, String startDate, String endDate) throws IOException, ParseException {
        List<Transactions> list = transactionsRepo.findByDateRange(dateOfTypeStringToDate(startDate, "yyyy-MM-dd"),dateOfTypeStringToDate(endDate, "yyyy-MM-dd"));
        ByteArrayInputStream bis = TransactionReportExcelDownloader.generateTransactionExcel(list);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment; filename=TransactionReportByDateRange.xls");
        response.setHeader("Cache-Control",
                "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = bis.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            outputStream.flush();
            outputStream.close();
        };

    }

}
