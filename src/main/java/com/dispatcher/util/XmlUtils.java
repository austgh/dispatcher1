package com.dispatcher.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class XmlUtils {
	public static Map<String, Object> document2Map(Document document){
		Map<String, Object> map = new HashMap<>();
		if (null == document) {
			return null;
		}
		Element root = document.getRootElement();
		Iterator<?> iterator = root.elementIterator();
		while(iterator.hasNext()){
			Element element = (Element) iterator.next();
			List<?> list = element.elements();
			if (!list.isEmpty()) {
				map.put(element.getName().toLowerCase(), element2Map(element));
			} else {
				map.put(element.getName().toLowerCase(), element.getText());
			}
		}
		return map;
	}
	
	public static Map<String, Object> element2Map(Element element){
		Map<String, Object> map = new HashMap<>();
		List<?> list = element.elements();
		if (!list.isEmpty()) {
			for(int i = 0; i < list.size(); i++){
				Element ele = (Element) list.get(i);
				String eleName = ele.getName().toLowerCase();
				List<Object> tempList = new ArrayList<>();
				if (ele.elements().size() > 0) {
					Map<String, Object> temp = element2Map(ele);
					if (null != map.get(eleName)) {
						Object object = map.get(eleName);
						if (object instanceof ArrayList<?>) {
							tempList.add(temp);
						} else {
							tempList.add(temp);
							tempList.add(object);
						}
						map.put(eleName, tempList);
					} else {
						map.put(eleName, temp);
					}
				} else {
					if (null != map.get(eleName)) {
						Object object = map.get(eleName);
						if (object instanceof ArrayList<?>) {
							tempList.add(ele.getText());
						} else {
							tempList.add(ele.getText());
							tempList.add(object);
						}
						map.put(eleName, tempList);
					} else {
						map.put(eleName, ele.getText());
					}
				}
			}
		} else {
			map.put(element.getName().toLowerCase(), element.getText());
		}
		return map;
	}
	
	public static String map2Xml(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<config>");
		mapToXml(sb, map);
		sb.append("</config>");
		return sb.toString();
					
	}
	
	@SuppressWarnings("unchecked")
	private static void mapToXml(StringBuffer sb, Map<String, Object> map){
		Set<String> set = map.keySet();
		for(Iterator<String> iterator = set.iterator();iterator.hasNext();){
			String key = iterator.next();
			Object value = map.get(key);
			if (null == value) {
				value = "";
			}
			if (value instanceof ArrayList<?>) {
				ArrayList<?> list = (ArrayList<?>) map.get(key);
				sb.append("<" + key + ">");
				for(int i =0; i<list.size();i++){
					Map<String, Object> map2 = (Map<String, Object>) list.get(i);
					mapToXml(sb, map2);
				}
				sb.append("</" + key +">");
			} else {
				if (value instanceof Map) {
					sb.append("<" + key+">");
					mapToXml(sb, (Map<String, Object>) value);
					sb.append("</" + key +">");
				} else {
					sb.append("<" + key + ">" + value + "</" +key+">");
				}
			}
		}
	}
	public static void main(String[] args) throws IOException, DocumentException {
		FileInputStream fileInputStream = new FileInputStream("U://req.txt");
		byte[] b = new byte[fileInputStream.available()];
		fileInputStream.read(b);
		String string = new String(b);
		Document document = DocumentHelper.parseText(string);
		System.out.println(document.asXML());
		long begintime = System.currentTimeMillis();
		Map<String, Object> map = XmlUtils.document2Map(document);
		System.out.println(map.toString());
		System.out.println(System.currentTimeMillis() - begintime);
		fileInputStream.close();
	}
}
