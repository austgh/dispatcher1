package com.dispatcher.model;



import java.lang.reflect.Field;
import java.util.List;

import com.dispatcher.util.CaseUtils;
import com.dispatcher.util.RefUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class EsbXmlMapper {
	public static Document toXml(Object bean) {
		Document doc = DocumentHelper.createDocument();
		toXml(bean, doc.addElement("service"));
		return doc;
	}

	@SuppressWarnings("unchecked")
	private static void toXml(Object bean, Element el) {
		if (bean instanceof List) {
			for (Object subObj : (List<Object>) bean) {
				toXml(subObj, addElement(el, subObj));
			}
		} else if (isSimpleBean(bean)) {
			setText(el, bean);
		} else {
			Field[] fields = RefUtils.getInheritedFields(bean.getClass());
			//sortFieldsOfESBXmlBo(bean, fields);
			for (Field field : fields) {
				toXml(RefUtils.get(field, bean), addElement(el, field));
			}
		}
	}

	/**
	 * 调整ESBXmlBo属性的顺序
	 * <service>
     * 	  <SYS_HEAD>
	 *    </SYS_HEAD>
	 *    <APP_HEAD>
	 *    </APP_HEAD>
	 *    <BODY>
	 *    </BODY>		
	 * </service>
	 * @param bean
	 * @param fields
	 */
	@SuppressWarnings("unused")
	private static void sortFieldsOfESBXmlBo(Object bean, Field[] fields) {
		if(bean instanceof EsbXmlBody && fields.length >= 2) {
			Field fieldTmp = fields[1];
			fields[1] = fields[0];
			fields[0] = fieldTmp;
		}
	}
	
	private static Element addElement(Element parentEl, Object bean) {
		ParamsCheck ParamsCheckAnn = bean.getClass().getAnnotation(ParamsCheck.class);
		return addElement(parentEl, ParamsCheckAnn, bean.getClass().getSimpleName());
	}

	private static Element addElement(Element parentEl, Field field) {
		ParamsCheck ParamsCheckAnn = field.getAnnotation(ParamsCheck.class);
		return addElement(parentEl, ParamsCheckAnn, field.getName());
	}

	private static Element addElement(Element parentEl, ParamsCheck ParamsCheckAnn,
			String simpleName) {
		String ParamsCheckName = ParamsCheckAnn != null && StringUtils.isNotEmpty(ParamsCheckAnn.value()) ? ParamsCheckAnn
				.value() : CaseUtils.toUpperCaseFirstChar(simpleName);
		return parentEl.addElement(ParamsCheckName);
	}

	private static void setText(Element el, Object bean) {
		el.setText(bean == null ? "" : bean.toString());
	}

	private static boolean isSimpleBean(Object bean) {
		return bean instanceof String || bean instanceof Number
				|| bean instanceof Boolean || bean instanceof Character
				|| bean == null;
	}
}
