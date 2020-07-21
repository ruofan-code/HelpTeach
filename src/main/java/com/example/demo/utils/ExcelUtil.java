package com.example.demo.utils;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author ruofan
 * @date 2019/11/16 22:51
 */
public class ExcelUtil {
    /**
     * 方法名：exportExcel
     * 功能：导出Excel
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 16:00
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    public static void exportExcel(HttpServletResponse response, ExcelData data) {
        try {
            //实例化HSSFWorkbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建一个Excel表单，参数为sheet的名字
            HSSFSheet sheet = workbook.createSheet("sheet");
            //设置表头
            setTitle(workbook, sheet, data.getHearer());
            //设置单元格并赋值
            setData(sheet, data.getData(), data.getHearer());
            //设置浏览器下载
            setBrowser(response, workbook, "测试导出");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置表头
     *
     * @param workbook
     * @param sheet
     * @param header
     */
    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, List<String> header) {
        try {
            HSSFRow row = sheet.createRow(0);
            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            for (int i = 0; i <= header.size(); i++) {
                sheet.setColumnWidth(i, 15 * 256);
            }
            //设置为居中加粗,格式化时间格式
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            //创建表头名称
            HSSFCell cell;
            for (int j = 0; j < header.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(header.get(j));
                cell.setCellStyle(style);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 表格赋值
     *
     * @param sheet
     * @param data
     */
    private static void setData(HSSFSheet sheet, List<StudentExcel> data, List<String> header) {
        try {
            int rowNum = 1;
            for (int i = 0; i < data.size(); i++) {
                HSSFRow row = sheet.createRow(rowNum);
//                    row.createCell(j).setCellValue(data.get(i)[j]);
                //设置学生学号和姓名
                row.createCell(0).setCellValue(data.get(i).getUsername());
                row.createCell(1).setCellValue(data.get(i).getName());
                //设置签到情况
                for (int k = 0; k < data.get(i).getCheckList().size(); k++) {
                    row.createCell(k + 2).setCellValue(data.get(i).getCheckList().get(k));
                }
                //设置统计情况，签到总次数，已签到次数和未签到次数
                row.createCell(data.get(i).getCheckList().size() + 2).setCellValue(data.get(i).getCheckTotal());
                row.createCell(data.get(i).getCheckList().size() + 3).setCellValue(data.get(i).getChecked());
                row.createCell(data.get(i).getCheckList().size() + 4).setCellValue(data.get(i).getNocheck());
                rowNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 安卓下载
     *
     * @param response
     * @param workbook
     * @param fileName
     */
    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
        try {
            //清空response
            response.reset();
            //设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
