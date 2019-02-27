package lianxi;



import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 本类来实现excel的导入和导出数据
 * 测试类
 *
 * 需要的依赖
 *
 *         <dependency>
 *             <groupid>org.apache.poi</groupid>
 *             <artifactid>poi</artifactid>
 *             <version>3.6</version>
 *         </dependency>
 *
 *         <dependency>
 *             <groupid>org.apache.poi</groupid>
 *             <artifactid>poi-ooxml</artifactid>
 *             <version>3.6</version>
 *         </dependency>
 */
public class Poitest {
    //定义Excel的版本
    private final static String EXCEL2003=".xls";
    private final static String EXCEL2007=".xlsx";
    //读取Excel里的字段
    public static void main(String[] args) {
        //注释的内容是创建一个2003版本的Excel表
        /*  // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("学生表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("学号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("年龄");
        cell.setCellStyle(style);
     */
        //创建一个2007版本以后的Excel表
        XSSFWorkbook wb = new XSSFWorkbook();
        //命名工作簿
        XSSFSheet sheet = wb.createSheet("学生表");
        //创建第0行 用于表头
        XSSFRow row = sheet.createRow(0);
        //创建一个样式
        XSSFCellStyle style = wb.createCellStyle();
        //设置样式居中
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中格式
        //创建一个单元格
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("学号");//设置单元格数据
        cell.setCellStyle(style);//设置单元格风格
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell( 2);
        cell.setCellValue("年龄");
        cell.setCellStyle(style);


        //提取一个Excel表的数据，生产中应该是从数据库得到数据
        Poitest poitest = new Poitest();
        File file = new File("C:\\Users\\11014\\Desktop\\test.xlsx");
        List<List<Object>> dataList=null;
        try {
            dataList = poitest.importExcel(file); //得到数据
        } catch (Exception e) {
            e.printStackTrace();
        }
        //这里将得到的数据赋值给一个新的Excel表格
        for (List<Object> objects : dataList) {
            int num = sheet.getLastRowNum(); //获取第一行数据
            row = sheet.createRow(num+1); //从第二行开始创建，因为第一行已经有表头了
            for (int j = 0; j < objects.size(); j++) {//为每一个单元格赋值
                // 第四步，创建单元格，并设置值
                row.createCell(j).setCellValue(objects.get(j).toString());
            }
        }
        try
        {
            FileOutputStream fout = new FileOutputStream("E:/Members.xlsx");
            wb.write(fout);//将建好的表格写到输出流中
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     * @param file
     * @return
     * @throws Exception
     */
    public  List<List<Object>> importExcel(File file) throws Exception{
        List<List<Object>> list = null;

        //创建Excel表
        Workbook work = this.getWorkbook(file);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;//创建爱你工作簿
        Row row = null;  //创建行
        Cell cell = null; //创建格

        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i <work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}

            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                //row.getFirstCellNum()==j  屏蔽表头
                if(row==null||row.getFirstCellNum()==j){continue;}

                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y <row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    if(cell!=null)
                    li.add(this.getCellValue(cell));
                }
                if(row!=null)
                list.add(li);
            }
        }
        return list;
    }


    /**
     * 对表格中的数据进行格式化
     * @param cell
     * @return
     */
    private Object getCellValue(Cell cell) {
        String value=null;
        DecimalFormat df = new DecimalFormat("0");//格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //格式化日期类型
        DecimalFormat df2=new DecimalFormat("0.00");//格式化数字
        switch (cell.getCellType()){
            //string 类型
            case Cell.CELL_TYPE_STRING:value=cell.getStringCellValue();break;
            //数值类型
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value=df.format(cell.getNumericCellValue());
                }else if(HSSFDateUtil.isCellDateFormatted(cell)){
                    value=sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                }else{
                    value=df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value=String.valueOf(cell.getBooleanCellValue());break;
            case Cell.CELL_TYPE_FORMULA:
                value=String.valueOf(cell.getCellFormula());break;
            case Cell.CELL_TYPE_BLANK:  //空，不知道何时算空
                value = "";
                break;

            default:
                value = "";
                break;
        }
        if(value.equals("")||value==null){
            value = "";
        }
        if (cell == null) {
            return "";
        }
        return value;
    }

    /**
     * 根据文件后缀，自适应上传文件的版本
     * @param file
     * @return
     */
    private Workbook getWorkbook(File file) throws Exception {
        Workbook wb=null;
        String fileType = file.getName().substring(file.getName().lastIndexOf("."));
        if(EXCEL2003.equals(fileType)){
            wb=new HSSFWorkbook(new FileInputStream(file)); //2003版本
        }else if(EXCEL2007.equals(fileType)){
            wb=new XSSFWorkbook(new FileInputStream(file));//2007以上版本
        }else{
            throw new Exception("解析文件格式有误");
        }
        return wb;
    }
}
