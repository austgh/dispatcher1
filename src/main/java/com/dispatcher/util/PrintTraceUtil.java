package com.dispatcher.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Auther: yjntue
 * @Date: 2019/1/15 12:27
 * @Description:
 */
public class PrintTraceUtil {
    public static String getStackTraceInfo(Exception e) {

        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);//将出错的栈信息输出到printWriter中
            pw.flush();
            sw.flush();

            return sw.toString();
        } catch (Exception ex) {

            return "发生错误";
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }

    }

}
