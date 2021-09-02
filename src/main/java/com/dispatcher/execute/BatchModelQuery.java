package com.dispatcher.execute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.dispatcher.host.WsdlInvoke;
import com.dispatcher.util.CommUtils;

import com.alibaba.fastjson.JSONObject;

public class BatchModelQuery {
	public static void main(String[] args) throws Exception {
		test();
	}

	public static void test() throws Exception {
		BufferedReader br = null;
		try {
			// File inputfile = new File("U://batch/batch_3.txt");//线下审批
			File inputfile = new File("U://batch/entname1.csv");// 线上审批
			// File inputfile = new
			// File("U://batch/entname_8000_xx.csv");//线下精准营销含税
			br = new BufferedReader(new FileReader(inputfile));
			String entname = "";
			int i = 110;
			while ((entname = br.readLine()) != null) {

				String[] params = entname.split(",");
				entname = params[0];
				if (entname.length() < 3 || CommUtils.isEmptyStr(params[2]) || CommUtils.isEmptyStr(params[1])
						|| CommUtils.isEmptyStr(params[0])) {
					continue;
				}
				String lerep = params[1];
				String cerno = params[2];

				
				/*-----------------------------以上部分为公用代码------------------------------*/
				// 线上审批发下面这个 先发datatype=2 ,再发datatype=1
				// entname,lerep,cerno
				 JSONObject jObject = new JSONObject();
				 jObject.put("serviceId", "DataFallNotice");
				 jObject.put("entName", entname);
				 jObject.put("userid", "test2018_8_16_1"+i);
				 jObject.put("datatype", "1");
				 jObject.put("aprvFlwNo", "XED2018122400000"+i);
				 jObject.put("lglpsnnm", lerep);
				 jObject.put("lglpsnidentno", cerno);
				 jObject.put("lmtbal", "100");
				 jObject.put("aplamt", "100");
				 String jsonString = jObject.toJSONString();
				 String result1 = (String) WsdlInvoke.invokeHost(jsonString,
				 WsdlInvoke.RESULT_TYPE_2);
				 System.out.println(result1);
			}
			br.close();
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}
}
