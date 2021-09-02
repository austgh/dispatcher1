package com.dispatcher.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.dispatcher.host.WsdlInvoke;
import com.dispatcher.model.*;
import com.dispatcher.util.CommUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.handler.codec.json.JsonObjectDecoder;
import org.dom4j.*;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(HttpServletRequest request){
        String username=request.getParameter("username");
        System.out.println("-------"+username);
        return "hello";
    }

    @RequestMapping(value = "/mortgage-info",method = RequestMethod.POST,consumes = "application/xml",produces = "application/json")
    @ResponseBody
    public static String test(@RequestBody String message){
        //获取通用的信息
        System.out.println("message:"+message);
        Document doc = null;
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        try {
            doc = DocumentHelper.parseText(message);
            JSONObject json = new JSONObject();
            dom4j2Json(doc.getRootElement(), json);
            System.out.println("xml2Json:" + json);

           JSONObject  sysHead=json.getJSONObject("SYS_HEAD");
           JSONObject  body=json.getJSONObject("BODY");
           JSONObject  appHead=json.getJSONObject("APP_HEAD");
           String serviceCode=sysHead.getString("ServiceCode");
           String serviceScene=sysHead.getString("ServiceScene");

        //根据 esb的服务码和场景号来调用不同的服务
            if("02002000039".equals(serviceCode)&&"01".endsWith(serviceScene)){  //责任人校验
                JSONObject  array= body.getJSONObject("array");
                System.out.println(array.getString("RsplPrsnInfo"));
                Object info=  array.get("RsplPrsnInfo");
               List<RsplPrsnInfo> rsplPrsnInfos=new ArrayList<RsplPrsnInfo>();
                if(info instanceof JSONObject){

                    RsplPrsnInfo rsplPrsnInfo=new RsplPrsnInfo();
                    String identNo=((JSONObject) info).getString("IdentNo");
                    String rsplPrsnIdTyp=((JSONObject) info).getString("RsplPrsnIdTyp");
                    String rsplPrsnNm=((JSONObject) info).getString("RsplPrsnNm");
                    rsplPrsnInfo.setIdentNo(identNo);
                    rsplPrsnInfo.setRsplPrsnIdTyp(rsplPrsnIdTyp);
                    rsplPrsnInfo.setRsplPrsnNm(rsplPrsnNm);
                    rsplPrsnInfos.add(rsplPrsnInfo);
                }else if(info instanceof JSONArray){
                    rsplPrsnInfos=JSONObject.parseArray( array.getString("RsplPrsnInfo"),RsplPrsnInfo.class);
                }
//                EsbXmlWepmf01Body   esbXmlBody=   (EsbXmlWepmf01Body)convertXmlStrToObject(EsbXmlWepmf01Body.class,json.toString());
//                System.out.println("------------esbXmlBody"+esbXmlBody);
//                EsbXmlWepmf01Body   esbXmlBody= JSONObject.toJavaObject(json,EsbXmlWepmf01Body.class);
//                        toBean(json, EsbXmlWepmf01Body.class);  //通过JSONObject.toBean()方法进行对象间的转换
                SysHeadBody   sysHeadBody= JSONObject.toJavaObject(sysHead,SysHeadBody.class);
                System.out.println(sysHeadBody);

                AppHeadBody   appHeadBody= JSONObject.toJavaObject(appHead,AppHeadBody.class);
                System.out.println(appHeadBody);

                Wepmf01Body   wepmf01Body= JSONObject.toJavaObject(body,Wepmf01Body.class);
                wepmf01Body.setRsplPrsnInfoList(rsplPrsnInfos);
                System.out.println(wepmf01Body);

                wepmf01Body.setServiceId("Wepmf01Model");
                wepmf01Body.setEntname(wepmf01Body.getEntpNm());
                wepmf01Body.setLegalname(wepmf01Body.getLglPsnNm());
                wepmf01Body.setLegalidno(wepmf01Body.getLglPsnIdentNo());
                wepmf01Body.setUserid(sysHeadBody.getConsumerSeqNo());
                wepmf01Body.setPartBkCd(wepmf01Body.getPartBkCd());
                wepmf01Body.setZoneCd(wepmf01Body.getPartBkCd());
                // toBean(json, EsbXmlWepmf01Body.class);  //通过JSONObject.toBean()方法进行对象间的转换
                ResultBody resultBody=new ResultBody();
                try {
                    String request = mapper.writeValueAsString(wepmf01Body);
                    System.out.println(request);

                    String result = (String) WsdlInvoke.invokeHost(request,
                            WsdlInvoke.RESULT_TYPE_2);
                    System.out.println(result);
                    if(!CommUtils.isEmptyStr(result)){
                        JSONObject jsonObject1 = JSONObject.parseObject(result);
                        final String rescode = jsonObject1.getString("rescode");
                        final String resmsg = jsonObject1.getString("resmsg");
                        resultBody.setReplyCd(rescode);
                        resultBody.setReplyText(resmsg);

                        List<ResultInfo> resultInfos=new ArrayList<ResultInfo>();
                        ResultInfo resultInfo=new ResultInfo();
                        resultInfo.setReturnCode(rescode);
                        resultInfo.setReturnMsg(resmsg);
                        resultInfos.add(resultInfo);
                        sysHeadBody.setResultInfos(resultInfos);
                        sysHeadBody.setReturnStatus("S");
                    }else{
                        resultBody.setReplyCd("999999");
                        resultBody.setReplyText("交易失败");
                        List<ResultInfo> resultInfos=new ArrayList<ResultInfo>();
                        ResultInfo resultInfo=new ResultInfo();
                        resultInfo.setReturnCode("999999");
                        resultInfo.setReturnMsg("交易失败");
                        resultInfos.add(resultInfo);
                        sysHeadBody.setResultInfos(resultInfos);
                        sysHeadBody.setReturnStatus("F");
                    }
                } catch (JsonProcessingException e) {
                    resultBody.setReplyCd("999999");
                    resultBody.setReplyText("交易失败");
                }finally {
                    EsbXmlBody reqXmlBo = new EsbXmlBody(sysHeadBody, appHeadBody, resultBody);
                    Document reqDoc = EsbXmlMapper.toXml(reqXmlBo);
                    String result=reqDoc.asXML().replace("ResultInfos","array").replace("ResultInfo","Ret");
                    System.out.println(reqDoc.asXML());
                    return result;
                }
            }else if("02002000011".equals(serviceCode)&&"03".endsWith(serviceScene)){ //抵押校验
                List<CltGdsInf> cltGdsInfs=new ArrayList<CltGdsInf>();

                JSONObject  array= body.getJSONObject("array");
                System.out.println(array.getString("CltGdsInf"));

                Object info=  array.get("CltGdsInf");

                if(info instanceof JSONObject){
                    //负值
                    CltGdsInf cltGdsInf=new CltGdsInf();
                    String cltzEvltVal= ((JSONObject) info).getString("CltzEvltVal");
                    String valEvltMode=((JSONObject) info).getString("ValEvltMode");
                    String cltNm=((JSONObject) info).getString("CltNm");
                    String cltAdr=((JSONObject) info).getString("CltAdr");
                    String cltTyp=((JSONObject) info).getString("CltTyp");
                    String ownCtfNo=((JSONObject) info).getString("OwnCtfNo");
                    String hsPtyTyp=((JSONObject) info).getString("HsPtyTyp");

                    cltGdsInf.setCltzEvltVal(cltzEvltVal);
                    cltGdsInf.setValEvltMode(valEvltMode);
                    cltGdsInf.setCltNm(cltNm);
                    cltGdsInf.setCltAdr(cltAdr);
                    cltGdsInf.setCltTyp(cltTyp);
                    cltGdsInf.setOwnCtfNo(ownCtfNo);
                    cltGdsInf.setHsPtyTyp(hsPtyTyp);
                    List<AttrInf> attrInfs=new ArrayList<AttrInf>();
                    //获取
                    JSONObject  array1= ((JSONObject) info).getJSONObject("array");
                    Object attrInfObject=  array1.get("AttrInf");
                        if(attrInfObject instanceof JSONObject){
                            AttrInf attrInf=new AttrInf();
                            String attrNm=((JSONObject) attrInfObject).getString("AttrNm");
                            String attrTyp=((JSONObject) attrInfObject).getString("AttrTyp");
                            String identNo1=((JSONObject) attrInfObject).getString("IdentNo");
                            attrInf.setAttrNm(attrNm);
                            attrInf.setAttrTyp(attrTyp);
                            attrInf.setIdentNo(identNo1);
                            attrInfs.add(attrInf);
                        }else if(attrInfObject instanceof JSONArray){
                            attrInfs=JSONObject.parseArray( array1.getString("AttrInf"),AttrInf.class);
                        }
                    cltGdsInf.setAttrInfArray(attrInfs);
                    cltGdsInfs.add(cltGdsInf);
                }else if(info instanceof JSONArray){
//                    cltGdsInfs=JSONObject.parseArray( array.getString("CltGdsInf"),CltGdsInf.class);
                    JSONArray cltGdsInfArray=array.getJSONArray("CltGdsInf");
                    for(int i=0;i<cltGdsInfArray.size();i++){

                        CltGdsInf cltGdsInf=new CltGdsInf();
                        JSONObject jsonObject=cltGdsInfArray.getJSONObject(i);
                        String cltAdr=jsonObject.getString("CltAdr");
                        String cltNm=jsonObject.getString("CltNm");
                        String cltTyp=jsonObject.getString("CltTyp");
                        String cltzEvltVal=jsonObject.getString("CltzEvltVal");
                        String hsPtyTyp=jsonObject.getString("HsPtyTyp");
                        String ownCtfNo=jsonObject.getString("OwnCtfNo");
                        String valEvltMode=jsonObject.getString("ValEvltMode");

                        cltGdsInf.setCltAdr(cltAdr);
                        cltGdsInf.setCltNm(cltNm);
                        cltGdsInf.setCltTyp(cltTyp);
                        cltGdsInf.setCltzEvltVal(cltzEvltVal);
                        cltGdsInf.setHsPtyTyp(hsPtyTyp);
                        cltGdsInf.setOwnCtfNo(ownCtfNo);
                        cltGdsInf.setValEvltMode(valEvltMode);
                        List<AttrInf> attrInfs=new ArrayList<AttrInf>();
                        JSONObject attrInfArray1=  jsonObject.getJSONObject("array");

                        final Object attrInfArray = attrInfArray1.get("AttrInf");

                        if(attrInfArray instanceof JSONObject){
                            AttrInf attrInf=new AttrInf();

//                            JSONObject attrO=jsonObject.getJSONObject("array");
                            JSONObject attrInfObject= (JSONObject) attrInfArray;
                            String AttrNm=attrInfObject.getString("AttrNm");
                            String attrTyp=attrInfObject.getString("AttrTyp");
                            String identNo=attrInfObject.getString("IdentNo");
                            attrInf.setIdentNo(identNo);
                            attrInf.setAttrTyp(attrTyp);
                            attrInf.setAttrNm(AttrNm);
                            attrInfs.add(attrInf);
                        }else if(attrInfArray instanceof JSONArray){
                            attrInfs=JSONObject.parseArray( attrInfArray1.getString("AttrInf"),AttrInf.class);
                        }
                        cltGdsInf.setAttrInfArray(attrInfs);
                        cltGdsInfs.add(cltGdsInf);
                    }
                }
                System.out.println("-------------------------"+cltGdsInfs);
//                {"CltzEvltVal":"300.0","ValEvltMode":"01",
//
//                "array":{"AttrInf":[{"AttrNm":"刘波","AttrTyp":"2","IdentNo":"310101198112128895"},
//                {"AttrNm":"孙超","AttrTyp":"2","IdentNo":"340103198207224458"}]},

//                "CltNm":"fangzi","CltAdr":"110101",
//                "CltTyp":"010010","OwnCtfNo":"Q123",
//                "HsPtyTyp":"01"}
                //开始手工负值



//                CltGdsInf
//                Object info=  array.get("RsplPrsnInfo");
//                List<RsplPrsnInfo> rsplPrsnInfos=new ArrayList<RsplPrsnInfo>();

//                if(info instanceof JSONObject){
//                    RsplPrsnInfo rsplPrsnInfo=new RsplPrsnInfo();
//                    String identNo=((JSONObject) info).getString("IdentNo");
//                    String rsplPrsnIdTyp=((JSONObject) info).getString("RsplPrsnIdTyp");
//                    String rsplPrsnNm=((JSONObject) info).getString("RsplPrsnNm");
//                    rsplPrsnInfo.setIdentNo(identNo);
//                    rsplPrsnInfo.setRsplPrsnIdTyp(rsplPrsnIdTyp);
//                    rsplPrsnInfo.setRsplPrsnNm(rsplPrsnNm);
//                    rsplPrsnInfos.add(rsplPrsnInfo);
//                }else if(info instanceof JSONArray){
//                    rsplPrsnInfos=JSONObject.parseArray( array.getString("RsplPrsnInfo"),RsplPrsnInfo.class);
//                }
                SysHeadBody   sysHeadBody= JSONObject.toJavaObject(sysHead,SysHeadBody.class);
                System.out.println(sysHeadBody);

                AppHeadBody   appHeadBody= JSONObject.toJavaObject(appHead,AppHeadBody.class);
                System.out.println(appHeadBody);

                ReqBody   reqBody= JSONObject.toJavaObject(body,ReqBody.class);
//                reqBody.setCltGdsInfList();
                reqBody.setCltGdsInfList(cltGdsInfs);
                System.out.println(reqBody);

                reqBody.setServiceId("MortgageCheckModel");
                reqBody.setEntname(reqBody.getEntpNm());
                reqBody.setLegalname(reqBody.getLglPsnNm());
                reqBody.setLegalidno(reqBody.getLglPsnIdentNo());
                reqBody.setUserid(sysHeadBody.getConsumerSeqNo());
                reqBody.setCltGdsInfList(cltGdsInfs);
                reqBody.setPartBkCd(reqBody.getPartBkCd());
                reqBody.setZoneCd(reqBody.getPartBkCd());
                // toBean(json, EsbXmlWepmf01Body.class);  //通过JSONObject.toBean()方法进行对象间的转换
                ResultBody resultBody=new ResultBody();
                try {
                    String request = mapper.writeValueAsString(reqBody);
                    System.out.println(request);

                    String result = (String) WsdlInvoke.invokeHost(request,
                            WsdlInvoke.RESULT_TYPE_2);
                    System.out.println(result);
                    if(!CommUtils.isEmptyStr(result)){
                        JSONObject jsonObject1 = JSONObject.parseObject(result);
                        final String rescode = jsonObject1.getString("rescode");
                        final String resmsg = jsonObject1.getString("resmsg");
                        resultBody.setReplyCd(rescode);
                        resultBody.setReplyText(resmsg);

                        List<ResultInfo> resultInfos=new ArrayList<ResultInfo>();
                        ResultInfo resultInfo=new ResultInfo();
                        resultInfo.setReturnCode(rescode);
                        resultInfo.setReturnMsg(resmsg);
                        resultInfos.add(resultInfo);
                        sysHeadBody.setResultInfos(resultInfos);
                        sysHeadBody.setReturnStatus("S");
                    }else{
                        resultBody.setReplyCd("999999");
                        resultBody.setReplyText("交易失败");
                        List<ResultInfo> resultInfos=new ArrayList<ResultInfo>();
                        ResultInfo resultInfo=new ResultInfo();
                        resultInfo.setReturnCode("999999");
                        resultInfo.setReturnMsg("交易失败");
                        resultInfos.add(resultInfo);
                        sysHeadBody.setResultInfos(resultInfos);
                        sysHeadBody.setReturnStatus("F");
                    }
                } catch (JsonProcessingException e) {
                    resultBody.setReplyCd("999999");
                    resultBody.setReplyText("交易失败");
                }finally {
                    EsbXmlBody reqXmlBo = new EsbXmlBody(sysHeadBody, appHeadBody, resultBody);
                    Document reqDoc = EsbXmlMapper.toXml(reqXmlBo);
                    String result=reqDoc.asXML().replace("ResultInfos","array").replace("ResultInfo","Ret");
                    System.out.println(reqDoc.asXML());
                    return result;
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return "hello";
    }


    @RequestMapping(value = "/get-info",method = RequestMethod.GET, produces = "application/xml")
    @ResponseBody
    public Teacher getTest(){
        Student student1 = new Student();
        student1.setId(1);
        student1.setStuName("张三");
        student1.setSex("男");
        Student student2 = new Student();
        student2.setId(2);
        student2.setStuName("李四");
        student2.setSex("男");
        Teacher teacher = new Teacher();
        teacher.setId(11);
        teacher.setTeacherName("杨老师");
        teacher.setStudentList(Arrays.asList(student1,student2));
        return teacher;
    }

    @RequestMapping(value = "/get1-info",method = RequestMethod.GET, produces = "application/xml")
    @ResponseBody
    public EsbXmlBody getEsb(){
        EsbXmlBody  esbXmlBody= new EsbXmlBody();
        //接受参数map 使用工具类转成对应的实体类
        return esbXmlBody;
    }
    @RequestMapping(value = "/post-info",method = RequestMethod.POST, consumes = "application/xml")
    public void postTest(@RequestBody Teacher teacher){
        System.out.println("postman传过来的xml信息转换成实体类如下：==========");
        System.out.println(teacher.toString());
    }
/*    @RequestMapping(value = "/mortgage-info",method = RequestMethod.POST,consumes = "application/xml",produces = "application/json")
    public String esbTest(@RequestBody EsbXmlBody1 esbXmlBody){
        System.out.println("postman传过来的xml信息转换成实体类如下：==========");
        System.out.println(esbXmlBody.toString());
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3、转换
        ReqBody reqBody1=esbXmlBody.getBody();
        reqBody1.setServiceId("MortgageCheckModel");
        reqBody1.setEntname(reqBody1.getEntpNm());
        reqBody1.setLegalname(reqBody1.getLglPsnNm());
        reqBody1.setLegalidno(reqBody1.getLglPsnIdentNo());
        reqBody1.setUserid(esbXmlBody.getSysHeadBody().getConsumerSeqNo());

        ResultBody resultBody=new ResultBody();
        try {
            String json = mapper.writeValueAsString(esbXmlBody.getBody());
            System.out.println(json);

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
    }*/

    @RequestMapping(value = "/mortgage-info1",method = RequestMethod.POST,consumes = "application/xml",produces = "application/json")
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
    /**
     * xml转json
     *
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element, JSONObject json) {
        // 如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (!isEmpty(attr.getValue())) {
                json.put("@" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && !isEmpty(element.getText())) {// 如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for (Element e : chdEl) {// 有子元素
            if (!e.elements().isEmpty()) {// 子元素也有子元素
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                        if (o instanceof JSONObject) {// 如果此元素已存在,则转为jsonArray
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
                    if (!isEmpty(attr.getValue())) {
                        json.put("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }
    public static boolean isEmpty(String str) {

        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    /**
     * 将String类型的xml转换成对象
     */
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }


    @RequestMapping(value = "/20",method = RequestMethod.POST,consumes = "application/xml",produces = "application/json")
    public String toObject(@RequestBody String message){
        System.out.println("接受到的消息:"+message);
        Object o=convertXmlStrToObject(EsbXmlWepmf01Body.class,message);
        System.out.println(o);
        return "hello";
    }


}
