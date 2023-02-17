package com.dispatcher.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dispatcher.host.WsdlInvoke;
import com.dispatcher.model.*;
import com.dispatcher.util.CommUtils;
import com.dispatcher.xml.XMLUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author think
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(HttpServletRequest request) {
        String username = request.getParameter("username");
        log.info("-------" + username);
        return "hello";
    }

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @RequestMapping(value = "/mortgage-info", method = RequestMethod.POST, consumes = "application/xml", produces =
            "application/json")
    @ResponseBody
    public String mortgage(@RequestBody String message) {
        try {
            log.info("接受的请求报文:{}",message);
            Document doc = DocumentHelper.parseText(message);
            JSONObject json = new JSONObject();
            dom4j2Json(doc.getRootElement(), json);
            log.info("xml2Json:{}",json);

            JSONObject sysHead = json.getJSONObject("SYS_HEAD");
            JSONObject body = json.getJSONObject("BODY");
            JSONObject appHead = json.getJSONObject("APP_HEAD");
            String serviceCode = sysHead.getString("ServiceCode");
            String serviceScene = sysHead.getString("ServiceScene");

            SysHeadBody sysHeadBody = JSONObject.toJavaObject(sysHead, SysHeadBody.class);
            log.info(String.valueOf(sysHeadBody));

            AppHeadBody appHeadBody = JSONObject.toJavaObject(appHead, AppHeadBody.class);
            log.info(String.valueOf(appHeadBody));
            //根据 esb的服务码和场景号来调用不同的服务
            if ("02002000039".equals(serviceCode) && "01".equals(serviceScene)) {
                //责任人校验
                Wepmf01Body wepmf01Body = getWepmf01Body(body, sysHeadBody);
                return dispatcher(sysHeadBody, appHeadBody, wepmf01Body);
            } else if ("02002000011".equals(serviceCode) && "03".equals(serviceScene)) {
                //抵押校验
                ReqBody reqBody = getReqBody0(body, sysHeadBody);
                return dispatcher(sysHeadBody, appHeadBody, reqBody);
            } else if ("02002000001".equals(serviceCode) && "31".equals(serviceScene)) {
                ReqBody reqBody = getReqBody(body, sysHeadBody);
                return dispatcher(sysHeadBody, appHeadBody, reqBody);
            }else if ("02002000001".equals(serviceCode) && "29".equals(serviceScene)) {
                Wepmf03ReqBody reqBody = getWepmf03ReqBody(body, sysHeadBody);
                return dispatcher(sysHeadBody, appHeadBody, reqBody);
            }else if("12003000025".equals(serviceCode) && "05".equals(serviceScene)) {
                Wepmf05ReqBody reqBody = getWepmf05ReqBody(body);
                return dispatcher0(sysHeadBody, appHeadBody, reqBody);
            }else if("000".equals(serviceCode) && "00".equals(serviceScene)){
                WepmfHelpMe reqBody = getWepmfHelpMe(body);
                return dispatcher1(sysHeadBody, appHeadBody, reqBody);
            }else if("12002000004".equals(serviceCode) && "55".equals(serviceScene)){
                Wepmf06ReqBody reqBody = getWepmf06ReqBody(body, sysHeadBody);
                return dispatcher(sysHeadBody, appHeadBody, reqBody);
            }else if("12003000002".equals(serviceCode) && "21".equals(serviceScene)){
                Wepmf07ReqBody reqBody = getWepmf07ReqBody(body, sysHeadBody);
                return dispatcher2(sysHeadBody, appHeadBody, reqBody);
            }else if("13003000001".equals(serviceCode) && "34".equals(serviceScene)){
                Wepmf08ReqBody reqBody = getWepmf08ReqBody(body, sysHeadBody);
                return dispatcher3(sysHeadBody, appHeadBody, reqBody);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "hello";
    }

    private ReqBody getReqBody0(JSONObject body, SysHeadBody sysHeadBody) {
        List<CltGdsInf> cltGdsInfs = new ArrayList<>();
        List<RsplPrsnInfo> rsplPrsnInfos = new ArrayList<>();
        //三种情况  两个都有值
        Object data = body.get("array");
        if (data instanceof JSONArray) {
            JSONArray dataArray = (JSONArray) data;
            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject jsonObject = dataArray.getJSONObject(i);
                Object cltGds = jsonObject.get("CltGdsInf");
                if (cltGds != null) {
                    resolveCltGdsInfs(cltGdsInfs, cltGds);
                }
                Object rsplPrsn = jsonObject.get("RsplPrsnInfo");
                if (rsplPrsn != null) {
                    resolveRsplPrsnInfos(rsplPrsnInfos, rsplPrsn);
                }
            }
        } else if (data instanceof JSONObject) {
            resolveCltGdsInfs(cltGdsInfs, ((JSONObject) data).get("CltGdsInf"));
            resolveRsplPrsnInfos(rsplPrsnInfos, ((JSONObject) data).get("RsplPrsnInfo"));
        }
        ReqBody reqBody = JSONObject.toJavaObject(body, ReqBody.class);
        reqBody.setCltGdsInfList(cltGdsInfs);
        reqBody.setServiceId("MortgageCheckModel");
        reqBody.setEntname(reqBody.getEntpNm());
        reqBody.setLegalname(reqBody.getLglPsnNm());
        reqBody.setLegalidno(reqBody.getLglPsnIdentNo());
        reqBody.setUserid(sysHeadBody.getConsumerSeqNo());
        reqBody.setCltGdsInfList(cltGdsInfs);
        reqBody.setRsplPrsnInfoList(rsplPrsnInfos);
        reqBody.setPartBkCd(reqBody.getPartBkCd());
        reqBody.setZoneCd(reqBody.getPartBkCd());
        reqBody.setUvslSocCrCd(reqBody.getUvslSocCrCd());
        return reqBody;
    }

    private ReqBody getReqBody(JSONObject body, SysHeadBody sysHeadBody) {
        List<CltGdsInf> cltGdsInfs = new ArrayList<>();

        ReqBody reqBody = JSONObject.toJavaObject(body, ReqBody.class);
        JSONObject data = body.getJSONObject("array");
        if(data!=null){
            Object cltObj = data.get("CltGdsInf");
            if(cltObj!=null){
                if (cltObj instanceof JSONObject) {
                    CltGdsInf cltGdsInf = JSON.toJavaObject((JSONObject) cltObj, CltGdsInf.class);
                    cltGdsInfs.add(cltGdsInf);
                } else if (cltObj instanceof JSONArray) {
                    cltGdsInfs = JSONObject.parseArray(data.getString("CltGdsInf"), CltGdsInf.class);
                }
                reqBody.setCltGdsInfList(cltGdsInfs);
            }
        }

        reqBody.setServiceId("BEDYearApproveModel");
        reqBody.setEntname(reqBody.getEntpNm());
        reqBody.setLegalidno(reqBody.getLglPsnIdentNo());
        reqBody.setLegalname(reqBody.getLglPsnNm());
        reqBody.setUserid(sysHeadBody.getConsumerSeqNo());
        reqBody.setPartBkCd(reqBody.getPartBkCd());
        reqBody.setZoneCd(reqBody.getZoneCd());
        log.info("请求报文:{}",reqBody);
        return reqBody;
    }
    private Wepmf03ReqBody getWepmf03ReqBody(JSONObject body, SysHeadBody sysHeadBody) {
        Wepmf03ReqBody reqBody = JSONObject.toJavaObject(body, Wepmf03ReqBody.class);
        reqBody.setServiceId("CEDApproveModel");
        reqBody.setEntname(reqBody.getEntpNm());
        reqBody.setUserid(sysHeadBody.getConsumerSeqNo());
        log.info("请求报文:{}",reqBody);
        return reqBody;
    }
    private Wepmf05ReqBody getWepmf05ReqBody(JSONObject body) {
        Wepmf05ReqBody reqBody = JSONObject.toJavaObject(body, Wepmf05ReqBody.class);
        reqBody.setServiceId("queryEntRelationship");
        log.info("请求报文:{}",reqBody);
        return reqBody;
    }
    private Wepmf06ReqBody getWepmf06ReqBody(JSONObject body, SysHeadBody sysHeadBody) {
        Wepmf06ReqBody reqBody = JSONObject.toJavaObject(body, Wepmf06ReqBody.class);
        reqBody.setServiceId("AMNoTaxModel");
        reqBody.setEntName(reqBody.getEntpNm());
        reqBody.setEntpNm(reqBody.getEntpNm());
        reqBody.setUvslSocCrCd(reqBody.getUvslSocCrCd());
        reqBody.setCusMngr(reqBody.getCusMngr());
        reqBody.setUserid(sysHeadBody.getConsumerSeqNo());
        log.info("请求报文:{}",reqBody);
        return reqBody;
    }
    private Wepmf07ReqBody getWepmf07ReqBody(JSONObject body, SysHeadBody sysHeadBody) {
        Wepmf07ReqBody reqBody = JSONObject.toJavaObject(body, Wepmf07ReqBody.class);
        reqBody.setServiceId("queryAMNoTaxModel");
        reqBody.setEntName(reqBody.getEntpNm());
        reqBody.setEntpNm(reqBody.getEntpNm());
        reqBody.setUvslSocCrCd(reqBody.getUvslSocCrCd());
        reqBody.setCusMngr(reqBody.getCusMngr());
        reqBody.setBgnDt(reqBody.getBgnDt());
        reqBody.setEndDt(reqBody.getEndDt());
        reqBody.setUserid(sysHeadBody.getConsumerSeqNo());
        log.info("请求报文:{}",reqBody);
        return reqBody;
    }
    private Wepmf08ReqBody getWepmf08ReqBody(JSONObject body, SysHeadBody sysHeadBody) {
        Wepmf08ReqBody reqBody = new Wepmf08ReqBody();
        if(body!=null){
            reqBody = JSONObject.toJavaObject(body, Wepmf08ReqBody.class);
        }
        reqBody.setServiceId("QueryTechNameService");
        reqBody.setEntName(reqBody.getEntpNm());
        reqBody.setEntpNm(reqBody.getEntpNm());
        reqBody.setUvslSocCrCd(reqBody.getUvslSocCrCd());
        reqBody.setUserid(sysHeadBody.getConsumerSeqNo());
        log.info("请求报文:" + reqBody);
        return reqBody;
    }
    private WepmfHelpMe getWepmfHelpMe(JSONObject body) {
        WepmfHelpMe reqBody = JSONObject.toJavaObject(body, WepmfHelpMe.class);
        reqBody.setServiceId("helpme");
        log.info("请求报文:{}",reqBody);
        return reqBody;
    }
    /*
    *
     *
     * @date 2022/9/1 15:31
     * @param sysHeadBody
     * @param appHeadBody
     * @param object 
     * @return java.lang.String
     */
    private String dispatcher2(SysHeadBody sysHeadBody, AppHeadBody appHeadBody,
                               Object object) {
        String result;
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        CmpnModlRsltInfoResultBody resultBody = new CmpnModlRsltInfoResultBody();
        try {
            String request = mapper.writeValueAsString(object);
            log.info(request);
            String wsdlResult = (String) WsdlInvoke.invokeHost(request,
                    WsdlInvoke.RESULT_TYPE_2);
            log.info("wsdlResult---->:{}",wsdlResult);
            if (!CommUtils.isEmptyStr(wsdlResult)) {
                JSONObject jsonObject1 = JSONObject.parseObject(wsdlResult);
                final String rescode = jsonObject1.getString("rescode");
                final String resmsg = jsonObject1.getString("resmsg");
                final String rcrdCnt = jsonObject1.getString("totRcrdCnt");

                List<CmpnModlRsltInfo> cmpnModlRsltInfos;
                cmpnModlRsltInfos = JSONObject.parseArray(jsonObject1.getString("CmpnModlRsltInfo"), CmpnModlRsltInfo.class);
                resultBody.setReplyCd(rescode);
                resultBody.setReplyText(resmsg);
                resultBody.setTotRcrdCnt(rcrdCnt);
                resultBody.setCmpnModlRsltInfos(cmpnModlRsltInfos);

                getResultInfos(sysHeadBody, rescode, resmsg);
            } else {
                resultBody.setReplyCd("999999");
                resultBody.setReplyText("交易失败");
                List<ResultInfo> resultInfos = new ArrayList<>();
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setReturnCode("999999");
                resultInfo.setReturnMsg("交易失败");
                resultInfos.add(resultInfo);
                sysHeadBody.setResultInfos(resultInfos);
                sysHeadBody.setReturnStatus("F");
            }
        } catch (JsonProcessingException e) {
            resultBody.setReplyCd("999999");
            resultBody.setReplyText("交易失败");
        } finally {
            EsbXmlBody reqXmlBo = new EsbXmlBody(sysHeadBody, appHeadBody, resultBody);
            Document reqDoc = EsbXmlMapper.toXml(reqXmlBo);
            result = reqDoc.asXML().replace("ResultInfos", "array").replace("ResultInfo", "Ret").replace("EntpInfos",
                    "array").replace("CmpnModlRsltInfos","array");
            log.info(result);
        }
        return result;
    }
    /*
    *
     * 返回WEPMF08的結果
     * @date 2023/2/17 14:56
     * @param sysHeadBody
     * @param appHeadBody
     * @param object
     * @return java.lang.String
     */
    private String dispatcher3(SysHeadBody sysHeadBody, AppHeadBody appHeadBody,
                               Object object) {
        String result;
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        TechNameModlResultBody resultBody = new TechNameModlResultBody();
        try {
            String request = mapper.writeValueAsString(object);
            log.info(request);
            String wsdlResult = (String) WsdlInvoke.invokeHost(request,
                    WsdlInvoke.RESULT_TYPE_2);
            log.info("wsdlResult---->:{}",wsdlResult);
            if (!CommUtils.isEmptyStr(wsdlResult)) {
                JSONObject jsonObject1 = JSONObject.parseObject(wsdlResult);
                final String rescode = jsonObject1.getString("rescode");
                final String resmsg = jsonObject1.getString("resmsg");
                final String techCmpFlg = jsonObject1.getString("techCmpFlg");

                resultBody.setReplyCd(rescode);
                resultBody.setReplyText(resmsg);
                resultBody.setTechCmpFlg(techCmpFlg);

                getResultInfos(sysHeadBody, rescode, resmsg);
            } else {
                resultBody.setReplyCd("999999");
                resultBody.setReplyText("交易失败");
                List<ResultInfo> resultInfos = new ArrayList<>();
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setReturnCode("999999");
                resultInfo.setReturnMsg("交易失败");
                resultInfos.add(resultInfo);
                sysHeadBody.setResultInfos(resultInfos);
                sysHeadBody.setReturnStatus("F");
            }
        } catch (JsonProcessingException e) {
            resultBody.setReplyCd("999999");
            resultBody.setReplyText("交易失败");
        } finally {
            EsbXmlBody reqXmlBo = new EsbXmlBody(sysHeadBody, appHeadBody, resultBody);
            Document reqDoc = EsbXmlMapper.toXml(reqXmlBo);
            result = reqDoc.asXML().replace("ResultInfos", "array").replace("ResultInfo", "Ret").replace("EntpInfos",
                    "array");
            log.info(result);
        }
        return result;
    }
    private Wepmf01Body getWepmf01Body(JSONObject body, SysHeadBody sysHeadBody) {
        JSONObject array = body.getJSONObject("array");
        log.info(array.getString("RsplPrsnInfo"));
        Object info = array.get("RsplPrsnInfo");
        List<RsplPrsnInfo> rsplPrsnInfos = new ArrayList<>();
        if (info instanceof JSONObject) {
            RsplPrsnInfo rsplPrsnInfo = JSON.toJavaObject((JSONObject) info, RsplPrsnInfo.class);
            rsplPrsnInfos.add(rsplPrsnInfo);
        } else if (info instanceof JSONArray) {
            rsplPrsnInfos = JSONObject.parseArray(array.getString("RsplPrsnInfo"), RsplPrsnInfo.class);
        }
        Wepmf01Body wepmf01Body = JSONObject.toJavaObject(body, Wepmf01Body.class);
        wepmf01Body.setRsplPrsnInfoList(rsplPrsnInfos);
        log.info(String.valueOf(wepmf01Body));
        wepmf01Body.setServiceId("Wepmf01Model");
        wepmf01Body.setEntname(wepmf01Body.getEntpNm());
        wepmf01Body.setLegalname(wepmf01Body.getLglPsnNm());
        wepmf01Body.setLegalidno(wepmf01Body.getLglPsnIdentNo());
        wepmf01Body.setUserid(sysHeadBody.getConsumerSeqNo());
        wepmf01Body.setPartBkCd(wepmf01Body.getPartBkCd());
        wepmf01Body.setZoneCd(wepmf01Body.getPartBkCd());
        wepmf01Body.setUvslSocCrCd(wepmf01Body.getUvslSocCrCd());
        return wepmf01Body;
    }

    private String dispatcher(SysHeadBody sysHeadBody, AppHeadBody appHeadBody,
                              Object object) {
        String result;
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        ResultBody resultBody = new ResultBody();
        try {
            String request = mapper.writeValueAsString(object);
            log.info(request);
            String wsdlResult = (String) WsdlInvoke.invokeHost(request,
                    WsdlInvoke.RESULT_TYPE_2);
            log.info(wsdlResult);
            if (!CommUtils.isEmptyStr(wsdlResult)) {
                JSONObject jsonObject1 = JSONObject.parseObject(wsdlResult);
                final String rescode = jsonObject1.getString("rescode");
                final String resmsg = jsonObject1.getString("resmsg");
                resultBody.setReplyCd(rescode);
                resultBody.setReplyText(resmsg);
                getResultInfos(sysHeadBody, rescode, resmsg);
            } else {
                resultBody.setReplyCd("999999");
                resultBody.setReplyText("交易失败");
                List<ResultInfo> resultInfos = new ArrayList<>();
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setReturnCode("999999");
                resultInfo.setReturnMsg("交易失败");
                resultInfos.add(resultInfo);
                sysHeadBody.setResultInfos(resultInfos);
                sysHeadBody.setReturnStatus("F");
            }
        } catch (JsonProcessingException e) {
            resultBody.setReplyCd("999999");
            resultBody.setReplyText("交易失败");
        } finally {
            EsbXmlBody reqXmlBo = new EsbXmlBody(sysHeadBody, appHeadBody, resultBody);
            Document reqDoc = EsbXmlMapper.toXml(reqXmlBo);
            result = reqDoc.asXML().replace("ResultInfos", "array").replace("ResultInfo", "Ret");
            log.info(result);
        }
        return result;
    }
    private String dispatcher1(SysHeadBody sysHeadBody, AppHeadBody appHeadBody,
                              Object object) {
        String result;
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        ResultBody1 resultBody = new ResultBody1();
        try {
            String request = mapper.writeValueAsString(object);
            log.info(request);
            String wsdlResult = (String) WsdlInvoke.invokeHost(request,
                    WsdlInvoke.RESULT_TYPE_2);
            log.info(wsdlResult);
            if (!CommUtils.isEmptyStr(wsdlResult)) {
                JSONObject jsonObject1 = JSONObject.parseObject(wsdlResult);
                final String rescode = jsonObject1.getString("rescode");
                final String resmsg = jsonObject1.getString("resmsg");
                final String result1 = jsonObject1.getString("result");
                if(!CommUtils.isEmptyStr(result1)){
                    resultBody.setResult(result1);
                }
                resultBody.setReplyCd(rescode);
                resultBody.setReplyText(resmsg);
                getResultInfos(sysHeadBody, rescode, resmsg);
            } else {
                resultBody.setReplyCd("999999");
                resultBody.setReplyText("交易失败");
                List<ResultInfo> resultInfos = new ArrayList<>();
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setReturnCode("999999");
                resultInfo.setReturnMsg("交易失败");
                resultInfos.add(resultInfo);
                sysHeadBody.setResultInfos(resultInfos);
                sysHeadBody.setReturnStatus("F");
            }
        } catch (JsonProcessingException e) {
            resultBody.setReplyCd("999999");
            resultBody.setReplyText("交易失败");
        } finally {
            EsbXmlBody reqXmlBo = new EsbXmlBody(sysHeadBody, appHeadBody, resultBody);
            Document reqDoc = EsbXmlMapper.toXml(reqXmlBo);
            result = reqDoc.asXML().replace("ResultInfos", "array").replace("ResultInfo", "Ret");
            log.info(result);
        }
        return result;
    }
    private void getResultInfos(SysHeadBody sysHeadBody, String rescode, String resmsg) {
        List<ResultInfo> resultInfos = new ArrayList<>();
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setReturnCode(rescode);
        resultInfo.setReturnMsg(resmsg);
        resultInfos.add(resultInfo);
        sysHeadBody.setResultInfos(resultInfos);
        sysHeadBody.setReturnStatus("S");
    }

    private String dispatcher0(SysHeadBody sysHeadBody, AppHeadBody appHeadBody,
                              Object object) {
        String result;
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        EntpInfoResultBody resultBody = new EntpInfoResultBody();
        try {
            String request = mapper.writeValueAsString(object);
            log.info(request);
            String wsdlResult = (String) WsdlInvoke.invokeHost(request,
                    WsdlInvoke.RESULT_TYPE_2);
            log.info("wsdlResult---->:{}",wsdlResult);
            if (!CommUtils.isEmptyStr(wsdlResult)) {
                JSONObject jsonObject1 = JSONObject.parseObject(wsdlResult);
                final String rescode = jsonObject1.getString("rescode");
                final String resmsg = jsonObject1.getString("resmsg");
                final String rcrdCnt = jsonObject1.getString("totRcrdCnt");

                List<EntpInf> entpInfos;
                entpInfos = JSONObject.parseArray(jsonObject1.getString("EntpInf"), EntpInf.class);
                resultBody.setReplyCd(rescode);
                resultBody.setReplyText(resmsg);
                resultBody.setRcrdCnt(rcrdCnt);
                resultBody.setEntpInfos(entpInfos);

                getResultInfos(sysHeadBody, rescode, resmsg);
            } else {
                resultBody.setReplyCd("999999");
                resultBody.setReplyText("交易失败");
                List<ResultInfo> resultInfos = new ArrayList<>();
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setReturnCode("999999");
                resultInfo.setReturnMsg("交易失败");
                resultInfos.add(resultInfo);
                sysHeadBody.setResultInfos(resultInfos);
                sysHeadBody.setReturnStatus("F");
            }
        } catch (JsonProcessingException e) {
            resultBody.setReplyCd("999999");
            resultBody.setReplyText("交易失败");
        } finally {
            EsbXmlBody reqXmlBo = new EsbXmlBody(sysHeadBody, appHeadBody, resultBody);
            Document reqDoc = EsbXmlMapper.toXml(reqXmlBo);
            result = reqDoc.asXML().replace("ResultInfos", "array").replace("ResultInfo", "Ret").replace("EntpInfos",
                    "array");
            log.info(result);
        }
        return result;
    }
    @RequestMapping(value = "/post-info", method = RequestMethod.POST, consumes = "application/xml")
    public void postTest(@RequestBody Teacher teacher) {
        log.info("postman传过来的xml信息转换成实体类如下：==========");
        log.info(teacher.toString());
    }

    /**
     * xml转json
     */
    public static void dom4j2Json(Element element, JSONObject json) {
        // 如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (notEmpty(attr.getValue())) {
                json.put("@" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && notEmpty(element.getText())) {
            // 如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for (Element e : chdEl) {
            // 有子元素
            if (!e.elements().isEmpty()) {
                // 子元素也有子元素
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject) {
                        // 如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject) o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray) o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                    }
                }

            } else {// 子元素没有子元素
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (notEmpty(attr.getValue())) {
                        json.put("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

    public static boolean notEmpty(String str) {
        return str != null && !str.trim().isEmpty() && !"null".equals(str);
    }

    @RequestMapping(value = "/20", method = RequestMethod.POST, consumes = "application/xml", produces = "application" +
            "/json")
    public String toObject(@RequestBody String message) {
        log.info("接受到的消息:{}",message);
        Object o = XMLUtil.convertXmlStrToObject(EsbXmlWepmf01Body.class, message);
        log.info((String) o);
        return "hello";
    }

    /**
     * 解析责任人数组
     */
    private void resolveRsplPrsnInfos(List<RsplPrsnInfo> rsplPrsnInfos, Object rsplPrsnInfo) {
        if (rsplPrsnInfo instanceof JSONObject) {
            rsplPrsnInfos.add(JSON.toJavaObject((JSONObject) rsplPrsnInfo, RsplPrsnInfo.class));
        } else if (rsplPrsnInfo instanceof JSONArray) {
            JSONArray rsplPrsnInfoArray = (JSONArray) rsplPrsnInfo;
            for (int i = 0; i < rsplPrsnInfoArray.size(); i++) {
                JSONObject jsonObject = rsplPrsnInfoArray.getJSONObject(i);
                rsplPrsnInfos.add(JSON.toJavaObject(jsonObject, RsplPrsnInfo.class));
            }
        }
    }

    private void resolveCltGdsInfs(List<CltGdsInf> cltGdsInfs, Object cltGds) {
        if (cltGds instanceof JSONObject) {
            resolveCltGdsJSONObject(cltGdsInfs, (JSONObject) cltGds);
        } else if (cltGds instanceof JSONArray) {
            JSONArray cltGdsInfArray = (JSONArray) cltGds;
            for (int i = 0; i < cltGdsInfArray.size(); i++) {
                resolveCltGdsJSONObject(cltGdsInfs, cltGdsInfArray.getJSONObject(i));
            }
        }
        log.info("-------------------------:{}",cltGdsInfs);
    }

    /**
     * 单独解析JSONObject 对象CltGds
     */
    public void resolveCltGdsJSONObject(List<CltGdsInf> cltGdsInfs, JSONObject cltGdsInf2) {
        CltGdsInf cltGdsInf = JSON.toJavaObject(cltGdsInf2, CltGdsInf.class);
        List<AttrInf> attrInfs = new ArrayList<>();
        Object attrInfObject = cltGdsInf2.getJSONObject("array").get("AttrInf");
        if (attrInfObject instanceof JSONObject) {
            AttrInf attrInf = JSON.toJavaObject((JSONObject) attrInfObject, AttrInf.class);
            attrInfs.add(attrInf);
        } else if (attrInfObject instanceof JSONArray) {
            attrInfs = JSONObject.parseArray(cltGdsInf2.getJSONObject("array").getString("AttrInf"), AttrInf.class);
        }
        cltGdsInf.setAttrInfArray(attrInfs);
        cltGdsInfs.add(cltGdsInf);
    }

}
