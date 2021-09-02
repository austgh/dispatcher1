package com.dispatcher.util;

import com.dispatcher.model.FileBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author think
 * @date 2021/8/19 19:11
 * @version 1.0
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void setBody(HttpServletResponse response, String filePath, String fileName) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        //读取文件
        try {
            @SuppressWarnings("resource")
            InputStream inputStream = new FileInputStream(filePath + fileName);
            OutputStream os = response.getOutputStream();
            int length;
            byte[] fileByte = new byte[1024 * 1024];
            while ((length = inputStream.read(fileByte)) != -1) {
                os.write(fileByte, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getAllLogInfo(Logger logger, String filePath) {
        logger.info("当前文件目录:{}", filePath);
        File file = new File(filePath);
        File[] tempFile = file.listFiles();
        StringBuilder result = new StringBuilder();
        assert tempFile != null;
        int num = tempFile.length;
        for (int i = 0; i < num - 1; i++) {
            result.append(tempFile[i].getName()).append(",");
        }
        if (num > 1) {
            result.append(tempFile[num - 1].getName());
        }
        return result.toString();
    }
    public static String downloadFile(HttpServletResponse response, String dateStr, ArrayList<FileBean> fileList, String projectName) {
        //设置压缩包的名字
        //解决不同浏览器压缩包名字含有中文时乱码的问题

        String ip= IPUtils.getLocalIP();
        String zipName = ip+"_"+projectName+"_" + dateStr + ".zip";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
        //设置压缩流：直接写入response，实现边压缩边下载
        ZipOutputStream zip = null;
        try {
            zip = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            //设置压缩方法
            zip.setMethod(ZipOutputStream.DEFLATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataOutputStream os = null;
        //循环将文件写入压缩流
        for (FileBean fileBean : fileList) {
            String filePath = fileBean.getFilePath();
            String fileName = fileBean.getFileName();
            //要下载文件的路径
            File file = new File(filePath + "/" + fileName);
            try {
                //添加ZipEntry，并ZipEntry中写入文件流
                //这里，加上i是防止要下载的文件有重名的导致下载失败
                assert zip != null;
                zip.putNextEntry(new ZipEntry(fileName));
                os = new DataOutputStream(zip);
                InputStream is = new FileInputStream(file);
                byte[] b = new byte[100];
                int length;
                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                is.close();
                zip.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //关闭流
        try {
            assert os != null;
            os.flush();
            os.close();
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "ok";
        try {
            response.getWriter().print("<h1>download Over!</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
    public static String download(HttpServletResponse response,String filePath,String projectName) {
        Date date = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final String dateStr = format.format(date);
        File file0 = new File(filePath);
        File[] tempFile = file0.listFiles();
        ArrayList<FileBean> fileList = new ArrayList<>();
        assert tempFile != null;
        for (File value : tempFile) {
            if(value.isFile()){
                addFileToList(fileList, value,filePath);
            }
        }
        return FileUtils.downloadFile(response, dateStr, fileList,projectName);
    }
    public static  void addFileToList(ArrayList<FileBean> fileList, File value, String filePath) {
        FileBean fileBean = new FileBean();
        fileBean.setFileName(value.getName());
        fileBean.setFilePath(filePath);
        fileList.add(fileBean);
    }
    public static String downloadByDate(HttpServletRequest request, HttpServletResponse response,String filePath,String projectName) throws ParseException {
        Date date = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final String dateStr = format.format(date);

        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        if(endDate==null||"".equals(endDate)){
            endDate=dateStr;
        }
        if(beginDate==null||"".equals(beginDate)){
            beginDate=endDate;
        }
        logger.info("beginDate:{}\nendDate:{}" , beginDate, endDate);
        Date start = new Date(format.parse(beginDate).getTime());
        Date end = new Date(format.parse(endDate).getTime());
        if(start.compareTo(end)>0){
            return "输入日期不正确,确保开始日期小于结束日期!!!";
        }

        File file0 = new File(filePath);
        File[] tempFile = file0.listFiles();
        ArrayList<FileBean> fileList = new ArrayList<>();
        assert tempFile != null;
        for (File value : tempFile) {
            if(value.isFile()&&value.getName().contains(projectName)&&!value.getName().contains(".swp")){
                if (value.getName().length() < 20) {
                    addFileToList(fileList, value,filePath);
                }else{
                    String[] params = value.getName().split("\\.");
                    if(params.length>2){
                        Date temp = new Date(format.parse(params[1]).getTime());
                        if (temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0) {
                            addFileToList(fileList, value,filePath);
                        }
                    }
                }
            }
        }
        //设置压缩包的名字
        //解决不同浏览器压缩包名字含有中文时乱码的问题
        return downloadFile(response, dateStr, fileList,projectName);
    }
}
