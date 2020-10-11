package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by liuliang on 2019/2/19.
 * excel 导出
 */
@Service
@Slf4j
public class ExcelUtil {


    public static String getCellStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }











    public static  Integer DEFAULT_ROW_MAX = 50000;

    public static  Integer ROW_MAX = 65535;

    public String buildExcel(LinkedHashMap<String,String> map, List list,Integer rowMax, HttpServletResponse response) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        int i = 1;
        if(!CollectionUtils.isEmpty(list)){
            if(rowMax==null || rowMax ==0 || rowMax>=ROW_MAX){
                rowMax = DEFAULT_ROW_MAX;
            }
            i = list.size()/rowMax + 1; //i代表页签
        }

        for(int j=0;j<i;j++){
            //sheet页
            HSSFSheet sheet = workbook.createSheet("第"+j+"页");
            //创建表头
            createTitle(workbook,sheet,map);

            //设置日期格式
            HSSFCellStyle style = workbook.createCellStyle();
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd HH:mm:ss"));

            //新增数据行，并且设置单元格数据
            int rowNum=1;

            //标题
            Set<String> titles = map.keySet();
            for(int w=rowMax*j;w<list.size();w++){
//            log.info("titles:{}",titles);
                Object object = list.get(w);
                Map<String, Object> objectMap = objectToMap(object);
                HSSFRow row = sheet.createRow(rowNum);
                for(int m=0;m<titles.size();m++){
                    HSSFCell cell = row.createCell(m);
                    String col = map.get(titles.toArray()[m]);
//                log.info(w+"row,"+m+"col:"+col);
                    Object o = objectMap.get(col);
                    //判断当前object类型并且返回一个String todo 待细分每一个类型，如date等
                    String result = ObjectUtils.isEmpty(o)?"":o.toString();
//                log.info(w+"row,"+m+"col,result："+result);
                    cell.setCellValue(result);
//                log.info("第"+rowNum+"行,第"+m+"列写入完毕！");
                }
//            log.info("第"+rowNum+"行写入完毕！");
                rowNum++;

                if(rowNum > rowMax){
                    break;
                }
            }
        }

        String fileName = UUID.randomUUID().toString();

        //生成excel文件
        buildExcelFile(fileName, workbook);

        //浏览器下载excel
        buildExcelDocument(fileName,workbook,response);

        return "success";
    }



    //生成excel文件
    protected void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }


    //浏览器下载excel
    protected void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


    public String Object2String(Object object){

        Class<?> clazz = object.getClass();

        return "";
    }



    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

    public static<T> T convert(Object obj, Class<T> type) {
        if (obj != null && StringUtils.isNotBlank(obj.toString())) {
            if (type.equals(Integer.class)||type.equals(int.class)) {
                return (T)new Integer(obj.toString());
            } else if (type.equals(Long.class)||type.equals(long.class)) {
                return (T)new Long(obj.toString());
            } else if (type.equals(Boolean.class)||type.equals(boolean.class)) {
                return (T) new Boolean(obj.toString());
            } else if (type.equals(Short.class)||type.equals(short.class)) {
                return (T) new Short(obj.toString());
            } else if (type.equals(Float.class)||type.equals(float.class)) {
                return (T) new Float(obj.toString());
            } else if (type.equals(Double.class)||type.equals(double.class)) {
                return (T) new Double(obj.toString());
            } else if (type.equals(Byte.class)||type.equals(byte.class)) {
                return (T) new Byte(obj.toString());
            } else if (type.equals(Character.class)||type.equals(char.class)) {
                return (T)new Character(obj.toString().charAt(0));
            } else if (type.equals(String.class)) {
                return (T) obj;
            } else if (type.equals(BigDecimal.class)) {
                return (T) new BigDecimal(obj.toString());
            } else if (type.equals(LocalDateTime.class)) {
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return (T) LocalDateTime.parse(obj.toString());
            } else if (type.equals(Date.class)) {
                try
                {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    return (T) formatter.parse(obj.toString());
                }
                catch (ParseException e)
                {
                    throw new RuntimeException(e.getMessage());
                }

            }else{
                return null;
            }
        } else {
            if (type.equals(int.class)) {
                return (T)new Integer(0);
            } else if (type.equals(long.class)) {
                return (T)new Long(0L);
            } else if (type.equals(boolean.class)) {
                return (T)new Boolean(false);
            } else if (type.equals(short.class)) {
                return (T)new Short("0");
            } else if (type.equals(float.class)) {
                return (T) new Float(0.0);
            } else if (type.equals(double.class)) {
                return (T) new Double(0.0);
            } else if (type.equals(byte.class)) {
                return (T) new Byte("0");
            } else if (type.equals(char.class)) {
                return (T) new Character('\u0000');
            }else {
                return null;
            }
        }
    }

    /**
     * 创建表头
     * @param workbook
     * @param sheet
     */
    public void createTitle(HSSFWorkbook workbook, HSSFSheet sheet, LinkedHashMap<String,String> map){
        Set<String> titles = map.keySet();//获取标题
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1,12*256);
        sheet.setColumnWidth(3,17*256);
        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        HSSFCell cell;
        for(int i=0;i<titles.size();i++){
            cell = row.createCell(i);
            cell.setCellValue(titles.toArray()[i].toString());
            cell.setCellStyle(style);
        }
    }



}
