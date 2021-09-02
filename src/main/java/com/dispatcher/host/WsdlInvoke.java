package com.dispatcher.host;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dispatcher.util.CommUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class WsdlInvoke {
	private static Logger logger = LoggerFactory.getLogger(WsdlInvoke.class);
	private static final String nameSpace = "http://webservice.ahzx.com/";
	private static final String urlStr = "http://localhost:9080/hsmodels/ws/HyService?wsdl";
//	private static final String urlStr = "http://localhost:8980/iifp-mdfc-service/ws/HyService?wsdl";
	//private static final String urlStr = "http://localhost:8080/hsmodels_20190626/ws/HyService?wsdl";
	private static final String service = "HyService";
//	private static final String service = "AXReportService";
	
	private static final String servicePort = "BaseWebServiceImplPort";
//	private static final String servicePort = "ReportWebServiceImplPort";
	
	private static final String method = "invoke";
	private static final String reqTagname = "reqJsonData";
	private static final String resTagname = "rspJsonData";
	public static final String RESULT_TYPE_1 = "1"; //返回Map类型结果
	public static final String RESULT_TYPE_2 = "2"; //返回jsonString类型结果

	public WsdlInvoke(){}

	public static Object invokeHost(Map<String, Object> reqMap){
		return invokeHost(reqMap, RESULT_TYPE_1);
	}

	public static Object invokeHost(Map<String, Object> reqMap, String resultType){
		JSONObject jsonObject = new JSONObject();
		Iterator<String> iterator = reqMap.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			Object value = reqMap.get(key);
			jsonObject.put(key, value);
		}
		if (CommUtils.isEmptyStr(resultType)) {
			return sendHost(jsonObject.toJSONString(), RESULT_TYPE_1);
		}
		return sendHost(jsonObject.toJSONString(), resultType);
	}
	
	public static Object invokeHost(String jsonString){
		return invokeHost(jsonString, RESULT_TYPE_2);
	}
	
	public static Object invokeHost(String jsonString, String resultType){
		if (CommUtils.isEmptyStr(resultType)) {
			return sendHost(jsonString, RESULT_TYPE_2);
		}
		return sendHost(jsonString, resultType);
	}
	
	private static Object sendHost(String jsonString, String resultType) {
		Object result = null;
		try {
			// 1、创建服务(Service)
			URL url = new URL(urlStr);
			QName sname = new QName(nameSpace, service);
			Service service = Service.create(url, sname);
			// 2、创建Dispatch
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(nameSpace, servicePort),
					SOAPMessage.class, Service.Mode.MESSAGE);
			// 3、创建SOAPMessage
			SOAPMessage msg = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL).createMessage();
			SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();
			// 4、创建QName来指定消息中传递数据
			QName ename = new QName(nameSpace, method, "axis");
			SOAPBodyElement ele = body.addBodyElement(ename);
			ele.addChildElement(reqTagname).setValue(jsonString);
			// 传递参数
			msg.writeTo(System.out);
			// 5、通过Dispatch传递消息,会返回响应消息
			SOAPMessage response = dispatch.invoke(msg);
			response.writeTo(System.out);
			// 6、响应消息处理,将响应的消息转换为dom对象
			Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			result = doc.getElementsByTagName(resTagname).item(0).getTextContent();
			logger.info("发送主机：[" + urlStr + "]，返回数据为：\n" + result);
			if (RESULT_TYPE_1.equals(resultType)) {//默认返回Map类型结果
				if (result instanceof String) {
					return jsonStrToMap((String) result);
				}
			} else {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发送主机发生异常!", e);
		} finally {
			
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private static Map<String, Object> jsonStrToMap(String jsonStr){
		Map<String, Object> resultMap = new HashMap<>();
		if (!jsonStr.isEmpty()) {
			JSONObject jsonObject = JSON.parseObject(jsonStr);
			Iterator<String> iterator = jsonObject.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				Object value = jsonObject.get(key);
				if (value instanceof Map) {
					resultMap.putAll((Map<? extends String, ? extends Object>) value);
				} else if (value instanceof JSONObject) {
					resultMap.putAll(jsonObject);
				} else {
					resultMap.put(key, value);
				}
			}
		}
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String jsonString = "{\"serviceId\":\"UserLogin\",\"userId\":\"heiheihei\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\"}";
		Map<String, Object> result = (Map<String, Object>) invokeHost(jsonString);
		System.out.println(result);
	}

}
