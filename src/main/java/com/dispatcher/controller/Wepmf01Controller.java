package com.dispatcher.controller;

import com.alibaba.fastjson.JSONObject;
import com.dispatcher.host.WsdlInvoke;
import com.dispatcher.model.*;
import com.dispatcher.util.CommUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dom4j.Document;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/wepmf")
public class Wepmf01Controller {
    @RequestMapping(value = "/post-info",method = RequestMethod.POST, consumes = "application/xml")
    public void postTest(@RequestBody Teacher teacher){
        System.out.println("postman传过来的xml信息转换成实体类如下：==========");
        System.out.println(teacher.toString());
    }

    @RequestMapping(value = "/wepmf01-info",method = RequestMethod.POST,consumes = "application/xml",produces = "application/json")
    public String esbTest(@RequestBody EsbXmlWepmf01Body esbXmlBody){

        //自己转实体类  写通用方法
        System.out.println("postman传过来的xml信息转换成实体类如下：==========");
        System.out.println(esbXmlBody.toString());
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3、转换
        Wepmf01Body wepmf01Body=esbXmlBody.getBody();
        wepmf01Body.setServiceId("Wepmf01Model");
        wepmf01Body.setEntname(wepmf01Body.getEntpNm());
        wepmf01Body.setLegalname(wepmf01Body.getLglPsnNm());
        wepmf01Body.setLegalidno(wepmf01Body.getLglPsnIdentNo());
        wepmf01Body.setUserid(esbXmlBody.getSysHeadBody().getConsumerSeqNo());

        ResultBody resultBody=new ResultBody();
        try {
            String json = mapper.writeValueAsString(esbXmlBody.getBody());
            System.out.println("json返回报文："+json);

            String result1 = (String) WsdlInvoke.invokeHost(json,
                    WsdlInvoke.RESULT_TYPE_2);
            System.out.println(result1);
            if(!CommUtils.isEmptyStr(result1)){
                JSONObject jsonObject1 = JSONObject.parseObject(result1);
               final String rescode = jsonObject1.getString("rescode");
               final String resmsg = jsonObject1.getString("resmsg");
                resultBody.setReplyCd(rescode);
                resultBody.setReplyText(resmsg);

                List<ResultInfo> resultInfos=new ArrayList<ResultInfo>();
                ResultInfo resultInfo=new ResultInfo();
                resultInfo.setReturnCode(rescode);
                resultInfo.setReturnMsg(resmsg);
                resultInfos.add(resultInfo);
                esbXmlBody.getSysHeadBody().setResultInfos(resultInfos);
                esbXmlBody.getSysHeadBody().setReturnStatus("S");
            }else{
                resultBody.setReplyCd("999999");
                resultBody.setReplyText("交易失败");
                List<ResultInfo> resultInfos=new ArrayList<ResultInfo>();
                ResultInfo resultInfo=new ResultInfo();
                resultInfo.setReturnCode("999999");
                resultInfo.setReturnMsg("交易失败");
                resultInfos.add(resultInfo);
                esbXmlBody.getSysHeadBody().setResultInfos(resultInfos);
                esbXmlBody.getSysHeadBody().setReturnStatus("F");
            }
        } catch (JsonProcessingException e) {
            resultBody.setReplyCd("999999");
            resultBody.setReplyText("交易失败");
        }finally {
            EsbXmlBody reqXmlBo = new EsbXmlBody(esbXmlBody.getSysHeadBody(), esbXmlBody.getAppHeadBody(), resultBody);
            Document reqDoc = EsbXmlMapper.toXml(reqXmlBo);
            String result=reqDoc.asXML().replace("ResultInfos","array").replace("ResultInfo","Ret");
            System.out.println(reqDoc.asXML());
            return result;
        }
    }
}
