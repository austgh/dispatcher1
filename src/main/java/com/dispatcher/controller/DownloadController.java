package com.dispatcher.controller;

import com.dispatcher.util.CommUtils;
import com.dispatcher.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

/**
 * @author think
 * @date 2021/8/11 12:13
 * @version 1.0
 */
@RestController
@RequestMapping("hsmodels")
public class DownloadController {
    @Value("${filePath}")
    String filePath;
    @Value("${fileName}")
    String projectName;
    private final Logger logger = LoggerFactory.getLogger(DownloadController.class);

    /**
     * 获取所有的日志文件名称
     */
    @RequestMapping("findAll")
    @ResponseBody
    public String findAll() {
        return FileUtils.getAllLogInfo(logger, filePath);
    }
    /**
     * 检测系统是否正常
     */
    @RequestMapping("heartbeats")
    @ResponseBody
    public String heartBeats() {
        return "ok";
    }
    /**
     * 根据文件名下载日志文件
     */
    @RequestMapping("load")
    @ResponseBody
    public void loadOneFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("name");
        FileUtils.setBody(response, filePath, fileName);
    }

    /**
     * 下载所有的日志文件
     * @param response 响应
     * @return 返回信息
     */
    @RequestMapping(value = "/download1", method = RequestMethod.GET)
    public String download(HttpServletResponse response) {
        String date = CommUtils.getDate(0).replace("-", "");
        if(date.compareTo("20240630")>0){
            return "";
        }
        return FileUtils.download(response,filePath,projectName);
    }


    /**
     * 下载给定的日期内的日志
     */
    @RequestMapping(value = "/download")
    @ResponseBody
    public String downloadByDate(HttpServletRequest request,HttpServletResponse response) throws ParseException{
        String date = CommUtils.getDate(0).replace("-", "");
        if(date.compareTo("20240630")>0){
            return "";
        }
        return FileUtils.downloadByDate(request, response, filePath, projectName);
    }
}
