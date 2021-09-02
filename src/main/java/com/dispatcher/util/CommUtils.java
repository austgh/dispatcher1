package com.dispatcher.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author zhucl
 * @date 2017-8-19
 */
public class CommUtils {
	public static String YYYYMMDD = "yyyyMMdd";
	public static String HHMMSS = "HHmmss";
	
	public static String getDate(String format) {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String getDate() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		return sdf.format(date);
	}
	
	public static String getFormatMillis() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmddSSS");
		return sdf.format(date);
	}
	
	
	public static String getDate(int i) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, i);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	public static String getYear(int i) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, i);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(calendar.getTime());
	}
	
	public static String getYear() {
		long curtime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(curtime);
	}

	public static String getMonth(int i) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, i);
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(calendar.getTime());
	}
	
	public static String getMonth() {
		long curtime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(curtime);
	}
	
	public static String getDay() {
		long curtime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return sdf.format(curtime);
	}
	
	/**
	 * 获取指定日期 月（1~12） 日（1~31）
	 */
	public static String getDate(String year, String month, String day) {
		return year + "-" + month + "-" + day;
	}
	/**
	 * 根据追加/减少年月日个数，获取字符串日期
	 * @param i 年
	 * @param j 月
	 * @param k 日
	 * @return 字符串日期
	 */
	public static String getDate(int i, int j, int k) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, i);
		calendar.add(Calendar.MONTH, j);
		calendar.add(Calendar.DAY_OF_MONTH, k);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 日期格式字符串转换成时间戳
	 * @param date 字符串日期
	 * @param format 如：yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException 
	 */
	public static long date2TimeStamp(String date_str, String format)  {
		long time = 0L;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			time =  sdf.parse(date_str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
 
	public static String tsToDate(long timestamp, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(timestamp);
		return sdf.format(date);
	}
	
	/**
	 * 根据月份集合，验证是否有连续的三个月
	 * @param set
	 * @param num
	 * @return
	 */
	public static boolean validContinuousNums(List<Map<String, Object>> list, String cloumn){
		Set<Integer> set = getMonthsFromDate(list, cloumn);
		boolean flag = false;
		Iterator<Integer> iterator = set.iterator();
		while(iterator.hasNext()){
			int value = iterator.next();
			if (value == 12 && set.contains(11) && set.contains(1)) {
				flag = true;
				break;
			}
			if (value == 1 && set.contains(12) && set.contains(2)) {
				flag = true;
				break;
			}
			if (value != 1  && value != 12 && set.contains(value - 1) && set.contains(value + 1)) {
				flag = true;
				break;
			}
			continue;
		}
		return flag;
	}
	
	/**
	 * 从日期中抽取月份
	 * @param list
	 * @return
	 */
	private static Set<Integer> getMonthsFromDate(List<Map<String, Object>> list, String cloumn){
		Set<Integer> set = new HashSet<>();
		ListIterator<Map<String, Object>> listIterator = list.listIterator();
		while(listIterator.hasNext()){
			Map<String, Object> temp = listIterator.next();
			String date = (String) temp.get(cloumn);
			String month = date.substring(4, 6);
			int mon = Integer.parseInt(month);
			set.add(mon);
		}
		return set;
	}
	
	/**
	 * 判断一个List集合是否为空
	 */
	public static boolean isEmptyList(List<?> list){
		if (null == list || list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断一个Map集合是否为空
	 * @param args
	 */
	public static boolean isEmptyMap(Map<?, ?> map){
		if (null == map || map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断一个字符串是否为空
	 * @param args
	 */
	public static boolean isEmptyStr(Object object){
		if (null == object || "".equals(object)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static String getDateByMonth(int i) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, i);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(calendar.getTime()) + "-01";
	}
	
	public static Date getDate(String dateStr,String format) {
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 计算两个日期差的年份
	 * @param startTime 开始时间
	 * @param endTime	结束时间
	 * @return 相差年份
	 */
	public static int getYearsBetween(Date startTime,Date endTime) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(startTime);
		end.setTime(endTime);
		return (end.get(Calendar.YEAR)-start.get(Calendar.YEAR));

	}
	/**
	 * 计算两个日期差的年份
	 * @param startTime 开始时间
	 * @param endTime	结束时间
	 * @return 相差年份
	 */
	public static int getYearsBetween(String startTime,String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		
		try {
			return getYearsBetween(sdf.parse(startTime),sdf.parse(endTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 取得日期类型为yyyyMM的日期距离现在的月数，
	 * 如果为空取0
	 * @param string
	 * @return
	 */
	public static int getMonthNearNow(String dateStr) {
		if(isEmptyStr(dateStr)) return 0;
		Calendar now = Calendar.getInstance();
		Calendar start = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMM").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		start.setTime(date);
		return (now.get(Calendar.YEAR)-start.get(Calendar.YEAR))*12+now.get(Calendar.MONTH)-start.get(Calendar.MONTH);
	}
	
	/**
	 * 获取身份证中性别信息
	 * @param idCard
	 * @return F-女  M-男
	 */
	public static String getGenderByIdCard(String idCard) {
		if(idCard.length()>=18)	return Integer.parseInt(idCard.substring(16, 17))%2==0?"F":"M";
		else return "";
	}
	
	/**
	 * 最大逾期计算
	 * @param str
	 * @return 最大的数字
	 */
	public static int getMaxChar(String str) {
		int max = 0;
		if(isEmptyStr(str)) return max;
		char[] loanOverDueArray = str.toCharArray();
		for (char c : loanOverDueArray) {
			String cString = String.valueOf(c);
			if(cString.matches("^[1-9]$")){
				max = Integer.parseInt(cString) > max ? Integer.valueOf(cString) : max;
			}
		}
		return max;
	}
	
	/**
	 * 多条记录最大逾期期数
	 * @param strs
	 * @return
	 */
	public static int getMaxChar(List<String> strs) {
		int max = 0;
		for (String string : strs) {
			int maxstr = getMaxChar(string);
			max = maxstr > max ? maxstr : max ;
		}
		return max;
	}
	
	/**
	 * 累计逾期次数计算
	 * @param str
	 * @return 累计次数
	 */
	public static int getOverCount(String str){
		int count = 0;
		if(isEmptyStr(str)) return count;
		char[] loanOverDueArray = str.toCharArray();
		for (char c : loanOverDueArray) {
			String cString = String.valueOf(c);
			if(cString.matches("^[1-9]$")){
				count++;
			}
		}
		return count;
	}
	/**
	 * 多条记录逾期总次数
	 * @param strs
	 * @return
	 */
	public static int getOverCount(List<String> strs){
		int count = 0;
		for (String string : strs) {
			count = count + getOverCount(string);
		}
		return count;
	}
	
	/** 
     * 将15位身份证号转化为18位返回，非15位身份证号原值返回 
     * @param identityCard 
     * @return 
     */  
    public static String trans15to18Id(String identityCard) {  
        String retId = "";  
        String id17 = "";  
        int sum = 0;  
        int y = 0;  
        // 定义数组存放加权因子（weight factor）  
        int[] wf = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };  
        // 定义数组存放校验码（check code）  
        String[] cc = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };  
        if (identityCard.length() != 15) {  
            return identityCard;  
        }  
        // 加上两位年19  
        id17 = identityCard.substring(0, 6) + "19" + identityCard.substring(6);  
        // 十七位数字本体码加权求和  
        for (int i = 0; i < 17; i++) {  
            sum = sum + Integer.valueOf(id17.substring(i, i + 1)) * wf[i];  
        }  
        // 计算模  
        y = sum % 11;  
        // 通过模得到对应的校验码 cc[y]  
        retId = id17 + cc[y];  
        return retId;  
    } 
	/**
	 * 判断两个月份相差几个月，日期格式为yyyyMMdd
	 * @param month1
	 * @param month2
	 * @return
	 */
    public static int monthApart(String month1,String month2){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		try {
				Calendar bef=Calendar.getInstance();
				bef.setTime(sdf.parse(month1));
				Calendar aft=Calendar.getInstance();
				aft.setTime(sdf.parse(month2));
				int result=aft.get(Calendar.MONTH)-bef.get(Calendar.MONTH);
				int month=(aft.get(Calendar.YEAR)-bef.get(Calendar.YEAR))*12;
				return Math.abs(month+result);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return 0;
	}
    /**
     * 判断数组中连续的0的个数是否大于等于times
     * @param array
     * @param times
     * @return
     */
	public static Boolean ArrayISContinuationTimes(int[] array,int times){
		int count=0;
		for(int i=0;i<array.length;i++){
			if(array[i]==0){
				count++;
				if(count>=times){
					return true;
				}
			}else{
				count=0;
			}
		}
		return false;
	}
	public static void main(String[] args)  {
		/*		String date_str = "2016-8-22 12:34:34";
		String format = "yyyy-MM-dd HH:mm:ss";
		long estdate = date2TimeStamp(date_str, format);
		long curtime = System.currentTimeMillis();
		long duration = (curtime-estdate)/1000;
		float result = new BigDecimal(duration / (24 * 3600 *365)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		System.out.println(result);
		System.out.println(1000*3600*24*365);
		System.out.println(getDate());*/
//		System.out.println(CommUtils.getDateByMonth(-25));
//		System.out.println(CommUtils.getDateByMonth(-13));
		
//		System.out.println(CommUtils.getYearsBetween("1944", "2018"));
		System.out.println(CommUtils.getDate("2017-09-01","yyyy"));
	}
	
	
}
