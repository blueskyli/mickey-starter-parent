package com.mickey.core.utils.common;

import com.google.common.collect.Lists;
import com.mickey.core.exception.NoveServiceException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author J·K
 * @Description: Excel工具类
 * @date 2018/7/28 16:21
 */
@Slf4j
public class ExcelUtils
{
    /**
     * 通用导出方法，利用反射机制
     * @param headersName 导出excel列名称集合
     * @param propsName bean中对应列名字段
     * @param beanList 需要导出的数据集合
     * @param title excel标题
     */
    public static void exportBeanExcel(String title,List<String> headersName,List<String> propsName,List beanList,HttpServletResponse response)
    {
        if(headersName.size()!=propsName.size()) throw new NoveServiceException("500","属性名和表头数据不符");

        // 表头
        Map<Integer,String> headersNameMap = list2Map(headersName);

        // 字段
        Map<Integer,String> propsNameMap = list2Map(propsName);

        // 声明一个工作薄：包括构建工作簿、表格、样式
        XSSFWorkbook book;

        book = new XSSFWorkbook();
        if(StringUtils.isBlank(title))
            title = DateUtils.getCurrentDateStr("yyyyMMddHHmmss");
        else
            title = title + DateUtils.getCurrentDateStr("yyyyMMddHHmmss");
        XSSFSheet xssfSheet = book.createSheet(title);
        XSSFRow xssfRow = xssfSheet.createRow(0);
        XSSFCell cell;

        Collection c = headersNameMap.values(); // 拿到标题的value集合
        Iterator<String> it = c.iterator(); //表格标题的迭代器

        // 数据导出
        short size = 0;
        while(it.hasNext()){
            cell = xssfRow.createCell(size);
            cell.setCellValue(it.next().toString());
            size++;
        }
        // bean prop 集合
        Collection zdC = propsNameMap.values();
        Iterator<T> beanIt = beanList.iterator();//总记录的迭代器
        int zdRow = 0; //列序号
        while(beanIt.hasNext()){//beanlist迭代器，遍历所有数据
            final int[] zdCell = {0};
            zdRow++;
            xssfRow = xssfSheet.createRow(zdRow);
            Object bean = beanIt.next();
            // 利用反射根据prop从list中读取数据
//            Field[] fields = bean.getClass().getDeclaredFields();

//            List<Field> fields = Stream.of(bean.getClass().getDeclaredFields())
//                    .collect(Collectors.toList());
//
//            if(!bean.getClass().getSuperclass().getName().toLowerCase().equals("java.lang.object")) {
//                fields.addAll(Stream.of(bean.getClass().getSuperclass().getDeclaredFields())
//                        .collect(Collectors.toList()));
//            }

            Class<?> _clazz = bean.getClass();
            List<Field> fields = Lists.newArrayList();
            while (_clazz!=null && !_clazz.getName().toLowerCase().equals("java.lang.object")){
                fields.addAll(Stream.of(_clazz.getDeclaredFields())
                        .collect(Collectors.toList()));
                _clazz = _clazz.getSuperclass();
            }

            XSSFRow finalXssfRow = xssfRow;
            Iterator<String> zdIt = zdC.iterator();//需要导出属性的迭代器
            while(zdIt.hasNext()){
                val propName = zdIt.next();
                List<Field> collect = fields.stream().filter(x -> x.getName().equals(propName)).collect(Collectors.toList());
                if(collect.size()>0){
                    String fieldName = collect.get(0).getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);//拿到属性的get方法
                    Class clazz = bean.getClass();//拿到JavaBean对象
                    try
                    {
                        Method getMethod = clazz.getMethod(getMethodName,new Class[]{});
                        Object value = getMethod.invoke(bean,new Object[] {});
                        if(value instanceof Date)
                        {
                            finalXssfRow.createCell((short) zdCell[0]).setCellValue(DateUtils.getFullFormatDate((Date)value));
                        }
                        else if(value instanceof Float || value instanceof Double)
                        {
                            //如果值为Float或者Double，则使用DecimalFormat来格式化，防止转变成科学计数法表示方式
                            DecimalFormat df = new DecimalFormat("#.##");
                            finalXssfRow.createCell((short) zdCell[0]).setCellType(Cell.CELL_TYPE_STRING);
                            finalXssfRow.createCell((short) zdCell[0]).setCellValue(df.format(value));
                        }
                        else
                        {
                            value = null == value ? "" : value;
                            finalXssfRow.createCell((short) zdCell[0]).setCellValue(value.toString());
                        }
                    }
                    catch(Exception e)
                    {
                        log.error("导出Excel出错：{}", e);
                        throw new NoveServiceException("500","excel导出异常");
                    }
                }
                else {//没有找到对应的属性
                    finalXssfRow.createCell((short) zdCell[0]).setCellValue("--");
                }
                zdCell[0]++;
            }
        }
        try
        {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(title.getBytes("utf-8"), "iso-8859-1") + ".xlsx");
            book.write(response.getOutputStream());
        }
        catch(IOException e)
        {
            log.error("导出Excel出错：{}", e);
            throw new NoveServiceException("500","excel导出异常");
        }
    }

    /**
     * 通用导出方法，利用反射机制
     * @param headersName 导出excel列名称集合
     * @param propsName bean中对应列名字段
     * @param beanList 需要导出的数据集合
     * @param title excel标题
     */
    public static void exportBeanExcelStyle(String title,List<String> headersName,List<String> propsName,List beanList,HttpServletResponse response)
    {
        if(headersName.size()!=propsName.size()) throw new NoveServiceException("500","属性名和表头数据不符");

        // 表头
        Map<Integer,String> headersNameMap = list2Map(headersName);

        // 字段
        Map<Integer,String> propsNameMap = list2Map(propsName);

        // 声明一个工作薄：包括构建工作簿、表格、样式
        XSSFWorkbook book;

        book = new XSSFWorkbook();

        if(StringUtils.isBlank(title))
            title = DateUtils.getCurrentDateStr("yyyyMMddHHmmss");
        else
            title = title + DateUtils.getCurrentDateStr("yyyyMMddHHmmss");
        XSSFSheet xssfSheet = book.createSheet(title);
        XSSFRow xssfRow = xssfSheet.createRow(0);
        XSSFCell cell = null;

        Collection c = headersNameMap.values(); // 拿到标题的value集合
        Iterator<String> it = c.iterator(); //表格标题的迭代器

        // 数据导出
        short size = 0;
        while(it.hasNext()){
            cell = xssfRow.createCell(size);
            cell.setCellValue(it.next().toString());
            size++;
        }
        //设置文本格式
        XSSFCellStyle cellStyle = book.createCellStyle();
        XSSFDataFormat dataFormat = book.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("@"));
        cell.setCellStyle(cellStyle);

        xssfSheet.setDefaultColumnStyle(1, cellStyle);
        // bean prop 集合
        Collection zdC = propsNameMap.values();
        Iterator<T> beanIt = beanList.iterator();//总记录的迭代器
        int zdRow = 0; //列序号
        while(beanIt.hasNext()){//beanlist迭代器，遍历所有数据
            final int[] zdCell = {0};
            zdRow++;
            xssfRow = xssfSheet.createRow(zdRow);
            Object bean = beanIt.next();
            // 利用反射根据prop从list中读取数据
            Field[] fields = bean.getClass().getDeclaredFields();
            XSSFRow finalXssfRow = xssfRow;
            Iterator<String> zdIt = zdC.iterator();//需要导出属性的迭代器
            while(zdIt.hasNext()){
                val propName = zdIt.next();
                List<Field> collect = Arrays.stream(fields).filter(x -> x.getName().equals(propName)).collect(Collectors.toList());
                if(collect.size()>0){
                    String fieldName = collect.get(0).getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);//拿到属性的get方法
                    Class clazz = bean.getClass();//拿到JavaBean对象
                    try
                    {
                        Method getMethod = clazz.getMethod(getMethodName,new Class[]{});
                        Object value = getMethod.invoke(bean,new Object[] {});
                        if(value instanceof Date)
                        {
                            finalXssfRow.createCell((short) zdCell[0]).setCellValue(DateUtils.getFullFormatDate((Date)value));
                        }
                        else if(value instanceof Float || value instanceof Double)
                        {
                            //如果值为Float或者Double，则使用DecimalFormat来格式化，防止转变成科学计数法表示方式
                            DecimalFormat df = new DecimalFormat("#.##");
                            finalXssfRow.createCell((short) zdCell[0]).setCellType(Cell.CELL_TYPE_STRING);
                            finalXssfRow.createCell((short) zdCell[0]).setCellValue(df.format(value));
                        }
                        else
                        {
                            value = null == value ? "" : value;
                            finalXssfRow.createCell((short) zdCell[0]).setCellValue(value.toString());
                        }
                    }
                    catch(Exception e)
                    {
                        log.error("导出Excel出错：{}", e);
                        throw new NoveServiceException("500","excel导出异常");
                    }
                }
                else {//没有找到对应的属性
                    finalXssfRow.createCell((short) zdCell[0]).setCellValue("--");
                }
                zdCell[0]++;
            }
        }
        try
        {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(title.getBytes("utf-8"), "iso-8859-1") + ".xlsx");
            book.write(response.getOutputStream());
        }
        catch(IOException e)
        {
            log.error("导出Excel出错：{}", e);
            throw new NoveServiceException("500","excel导出异常");
        }
    }

    /**
     * list 转换为 map
     * @param list
     * @return
     */
    private static Map<Integer,String> list2Map(List<String> list)
    {
        Map<Integer,String> map = new HashMap<>();
        final int[] key = {0};
        list.forEach(x->{
            if(StringUtils.isNotBlank(x)){
                map.put(key[0],x);
                key[0]++;
            }
        });
        return map;
    }

    /**
     * excel导入
     * @param file MultipartFile
     */
    public static List<List<Object>> readExcel(MultipartFile file){
        return readExcel(file, 0, 0, 0);
    }

    /**
     * excel导入
     * @param file MultipartFile
     * @param sheetIndex sheet索引
     * @param beginReadRow row索引
     * @param beginReadCol col索引
     * @return
     */
    public static List<List<Object>> readExcel(MultipartFile file, int sheetIndex, int beginReadRow, int beginReadCol){
        InputStream inputStream = null;
        try
        {
            inputStream = file.getInputStream();
        }
        catch(IOException e)
        {
            throw new NoveServiceException("500","MultipartFile转换失败",e);
        }
        return readExcel(inputStream, file.getOriginalFilename().split("\\.")[1], sheetIndex, beginReadRow, beginReadCol);
    }

    /**
     * excel导入
     * @param input 输入流
     * @param excelType excel类型
     * @param sheetIndex sheet索引
     * @param beginReadRow row索引
     * @param beginReadCol col索引
     * @return
     */
    public static List<List<Object>> readExcel(InputStream input, String excelType, int sheetIndex, int beginReadRow, int beginReadCol){
        List<List<Object>> list = Lists.newArrayList();
        Workbook wb = null;
        try
        {
            if("xls".equals(excelType))
                wb = new HSSFWorkbook(input);
            else
                wb = new XSSFWorkbook(input);

            // 获取Sheet（工作薄）
            Sheet sheet = wb.getSheetAt(sheetIndex);
            // 开始行数
            int firstRow = sheet.getFirstRowNum();
            // 结束行数
            int lastRow = sheet.getLastRowNum();
            // 表头列数
            int rowCellCount = sheet.getRow(firstRow).getLastCellNum();
            // 判断该Sheet（工作薄）是否为空
            if(firstRow == lastRow || beginReadRow > lastRow)
                return list;
            else{
                for(int j = beginReadRow; j <= lastRow; j++) // 循环 行
                {
                    // 获取一行
                    Row row = sheet.getRow(j);
                    // 判断该行是否为空
                    if(row == null || isBlankRow(row))
                    {
                        list.add(null);
                        continue;
                    }
                    List<Object> rowData = new ArrayList<Object>();
                    if(beginReadCol != rowCellCount)
                    {
                        for(int k = beginReadCol; k < rowCellCount; k++) //循环列
                        {
                            // 获取一个单元格
                            Cell cell = row.getCell(k);
                            if(cell == null)
                            {
                                rowData.add("");
                                continue;
                            }
                            Object value = null;

                            int cellType = cell.getCellType();
                            if(cellType == HSSFCell.CELL_TYPE_NUMERIC)
                            {
                                if(HSSFDateUtil.isCellDateFormatted(cell))
                                {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date date = cell.getDateCellValue();
                                    value = sdf.format(date);

                                }
                                else
                                {
                                    value = doubleTrans(cell.getNumericCellValue());
                                }
                            }
                            else if(cellType == HSSFCell.CELL_TYPE_STRING)
                            {
                                value = cell.getStringCellValue();
                            }
                            else if(cellType == HSSFCell.CELL_TYPE_BOOLEAN)
                            {
                                value = cell.getBooleanCellValue();
                            }
                            else if(HSSFDateUtil.isCellDateFormatted(cell))
                            {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Date theDate = cell.getDateCellValue();
                                if(theDate == null)
                                {
                                    value = "";
                                }
                                else
                                {
                                    value = sdf.format(theDate);
                                }
                            }
                            else
                            {
                                value = "";
                            }
                            rowData.add(value);
                        }
                    }
                    list.add(rowData);
                }
            }
        }
        catch(IOException e)
        {
            log.error("导入Excel出错：{}", e);
            throw new NoveServiceException("500","excel导入异常");
        }
        finally
        {
            if(wb != null)
            {
                wb.cloneSheet(0);
            }
            if(input != null)
            {
                try
                {
                    input.close();
                }
                catch(IOException e)
                {
                    log.error("导入Excel出错：{}", e);
                    throw new NoveServiceException("500","excel导入异常");
                }
            }
        }
        return list;
    }
    /**
     * 判断是否是空行
     *
     * @param row
     * @return
     */
    private static boolean isBlankRow(Row row)
    {
        Iterator<Cell> cellIterator = row.cellIterator();
        do
        {
            if(cellIterator.next().getCellType() != HSSFCell.CELL_TYPE_BLANK)
            {
                return false;
            }
        } while(cellIterator.hasNext());
        return true;
    }

    private static String doubleTrans(double d)
    {
        if(Math.round(d) - d == 0)
        {
            return String.valueOf((long) d);
        }
        return String.valueOf(d);
    }

    public static void main(String[] args) throws IOException
    {
        File file = new File("C:\\Users\\Administrator\\Desktop\\用户数据20181015145836.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        val list = readExcel(inputStream,"xlsx",0,1,0);
        System.out.println(list);
    }
}
