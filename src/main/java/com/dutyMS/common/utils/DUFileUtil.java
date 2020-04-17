package com.dutyMS.common.utils;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * 上传下载文件工具类
 *
 * @author Mela.S
 * @date 2020/1/10
 */
public class DUFileUtil {

    /**
     * 下载指定文件
     * @param res HttpServletResponse
     * @param fileName 文件名
     */
	public static void downloadFile(HttpServletResponse res, String path ,String fileName){
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(path + fileName)));
            int i = bis.read(buff);

            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 上传指定文件，只支持单文件上传
     * @param file 要保存的文件
     */
    public static String uploadingFile(MultipartFile file,String filePath,String fileName){
        File uploadDir = new File(filePath);
        // 创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!uploadDir.exists()) uploadDir.mkdirs();
        File tempFile = new File(filePath + File.separator + fileName);
        try {
            file.transferTo(tempFile);
            return null;
        } catch (IOException e) {
            return e.toString();
        }
    }

    /**
     * 保存一个文件并返回一个 Workbook
     * @return
     */
    public static Workbook uploadingFileAndReturnWorkBook(MultipartFile file, String filePath){
        // 判断保存上传文件的目录是否存在
        File uploadDir = new File(filePath);
        // 创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!uploadDir.exists())
            uploadDir.mkdirs();
        // 新建一个文件
        File tempFile = new File(filePath + new Date().getTime() + ".xls");
        // 初始化输入流
        InputStream is;
        // 根据版本选择创建Workbook的方式
        Workbook workbook = null;
        // 将上传的文件写入新建的文件中
        try {
            file.transferTo(tempFile);
            // 根据新建的文件实例化输入流
            is = new FileInputStream(tempFile);
            // 判断文件是2003版本还是2007版本
            if (!is.markSupported()) {
                is = new PushbackInputStream(is, 8);
            }
            if (POIXMLDocument.hasOOXMLHeader(is)) {
//                System.out.println("2007及以上");
                workbook = new XSSFWorkbook(is);
            } else {
//                System.out.println("2003及以下");
                workbook = new HSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
