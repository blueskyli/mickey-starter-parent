package com.mickey.core.utils.common;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.mickey.core.exception.NoveServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: EasyExcel
 * @author J·K
 * @date 2020/3/23 12:28 上午
 */
@Slf4j
public class EasyExcelUtils {

    /**
     * 读取excel
     * @param file
     * @return
     */
    public static List<Object> readExcel(MultipartFile file){
        return doReadExcel(file,1,1);
    }

    /**
     * @Description 读取excel
     * @Param   file  文件
     * @Param   sheetNo  第几个工作薄
     * @Param   headerLineMun  从哪行开始读
     * @return
     **/
    public static List<Object> readExcel(MultipartFile file, Integer sheetNo, Integer headerLineMun){
        return doReadExcel(file,sheetNo,headerLineMun);
    }

    /**
     * @Description 读取excel
     * @Param   file
     * @Param   sheetNo  第几个工作薄
     * @Param   headerLineMun  从哪行开始读
     * @Param   clazz  实现在BaseRowModel的实体类
     * @return
     **/
    public static <T> List<T> readExcel(MultipartFile file, Integer sheetNo, Integer headerLineMun,Class<? extends BaseRowModel> clazz){
        return doReadExcel(file,sheetNo,headerLineMun,clazz);
    }

    /**
     * @Description 导出excel 有表头
     * @Param data  导出数据集
     * @return
     **/
    public static void writeExcel(HttpServletResponse response, List<? extends BaseRowModel> data, Class<? extends BaseRowModel> clazz,String excelName){
        String fileName = null;
        try {
            fileName = URLEncoder.encode(excelName+".xlsx","UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.info("文件名称转换utf-8导常:"+excelName + e.getMessage());
            throw new NoveServiceException("500","文件名称转换utf-8导常");
        }
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        ExcelWriter writer = null;
        try {
            writer = new ExcelWriter(response.getOutputStream(), ExcelTypeEnum.XLSX);
        } catch (IOException e) {
            log.info("excel读取异常:"+ e.getMessage());
            throw new NoveServiceException("500","excel读取异常");
        }
        Sheet sheet1 = new Sheet(1, 0,clazz);
        sheet1.setSheetName(excelName);
        sheet1.setTableStyle(createTableStyle());
        writer.write(data, sheet1);
        writer.finish();
    }

    /**
     * @Description 读取excel 没有模型
     * @Param
     * @return
     **/
    private static List<Object> doReadExcel(MultipartFile file,Integer sheetNo,Integer headerLineMun){
        //自定义用于暂时存储data。
        List<Object> datas = new ArrayList<>();
        // 解析每行结果在listener中处理
        AnalysisEventListener listener = new AnalysisEventListener() {
            @Override
            public void invoke(Object o, AnalysisContext context) {
                datas.add(o);
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        };
        String fileName = file.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        ExcelReader excelReader = null;
        if (StringUtils.isNotBlank(fileSuffix)) {
            // 2007 xlsx  2003 xls
            if (fileSuffix.equals("xlsx")) {
                try {
                    excelReader = new ExcelReader(file.getInputStream(), ExcelTypeEnum.XLSX, null, listener);
                } catch (IOException e) {
                    log.info("excel--2007解析异常"+e.getMessage());
                    throw new NoveServiceException("500","2007解析异常");
                }
            }else {
                try {
                    excelReader = new ExcelReader(file.getInputStream(), ExcelTypeEnum.XLS, null, listener);
                } catch (IOException e) {
                    log.info("excel--2003解析异常"+e.getMessage());
                    throw new NoveServiceException("500","2003解析异常");
                }
            }
            try {
                excelReader.read(new Sheet(sheetNo, headerLineMun));
            }catch (Exception e){
                log.info("excel导入异常"+e.getMessage());
                throw new NoveServiceException("500","excel导入异常");
            }
        }
        return datas;
    }

    /**
     * @Description 读取excel 没有模型
     * @Param  clazz  返回值为映射模型   传入的实体要继承 BaseRowModel  属性加@ExcelProperty(index=0) index表示excel第几列的值
     * @return
     **/
    private static <T> List<T> doReadExcel(MultipartFile file, Integer sheetNo, Integer headerLineMun, Class<? extends BaseRowModel > clazz){
        //自定义用于暂时存储data。
        List<T> datas = new ArrayList<>();
        // 解析每行结果在listener中处理
        AnalysisEventListener listener = new AnalysisEventListener() {
            @Override
            public void invoke(Object o, AnalysisContext context) {
                datas.add((T)o);
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        };
        String fileName = file.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        ExcelReader excelReader = null;
        if (StringUtils.isNotBlank(fileSuffix)) {
            // 2007 xlsx  2003 xls
            if (fileSuffix.equals("xlsx")) {
                try {
                    excelReader = new ExcelReader(file.getInputStream(), ExcelTypeEnum.XLSX, null, listener);
                } catch (IOException e) {
                    log.info("excel--2007解析异常"+e.getMessage());
                    throw new NoveServiceException("500","2007解析异常");
                }
            }else {
                try {
                    excelReader = new ExcelReader(file.getInputStream(), ExcelTypeEnum.XLS, null, listener);
                } catch (IOException e) {
                    log.info("excel--2003解析异常"+e.getMessage());
                    throw new NoveServiceException("500","2003解析异常");
                }
            }
            try {
                excelReader.read(new Sheet(sheetNo, headerLineMun, clazz));
            }catch (Exception e){
                log.info("excel导入异常"+e.getMessage());
                throw new NoveServiceException("500","excel导入异常");
            }
        }
        return datas;
    }


    /**
     * 设置表格样式
     * @return
     */
    public static TableStyle createTableStyle() {
        TableStyle tableStyle = new TableStyle();
        Font headFont = new Font();
        headFont.setBold(false);
        headFont.setFontHeightInPoints((short) 12);
        headFont.setFontName("黑体");
        tableStyle.setTableHeadFont(headFont);
        tableStyle.setTableHeadBackGroundColor(IndexedColors.WHITE1);

        Font contentFont = new Font();
        contentFont.setFontHeightInPoints((short) 12);
        contentFont.setFontName("黑体");
        tableStyle.setTableContentFont(contentFont);
        tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE1);
        return tableStyle;
    }

}
