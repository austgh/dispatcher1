package com.dispatcher.execute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dispatcher.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.*;


/**
 * @author newcityman
 * @date 2019/9/17 - 23:12
步骤1、导入jackson的三个包
2、创建一个user对象，并赋值
3、创建jackson的核心对象ObjectMapper
4、调用ObjectMapper的writevalue等相关方法
 */
public class TestJson {
    public static void main(String[] args) {
//        User user = new User();
//        user.setName("中国移动");
//        user.setId(10086);
//        //2、创建jackson的核心对象 ObjectMapper
//        ObjectMapper mapper = new ObjectMapper();
//        //3、转换
//        try {
//            String json = mapper.writeValueAsString(user);
//            System.out.println(json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }


        String jsondata="{\"contend\":[{\"bid\":\"22\",\"carid\":\"0\"},{\"bid\":\"22\",\"carid\":\"0\"}],\"result\":100,\"total\":2}";
        JSONObject obj= JSON.parseObject(jsondata);
//map对象
        Map<String, Object> data =new HashMap<>();
//循环转换
        Iterator it =obj.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            data.put(entry.getKey(), entry.getValue());
        }
        System.out.println("map对象:"+data.toString());

    }
    @Test
    public void test1() throws IOException {
        //1、创建对象
        User user = new User();
        user.setName("中国移动");
        user.setId(10086);
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3、转换
        try {
            String json = mapper.writeValueAsString(user);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //将数据写到d://a.txt文件中
//        mapper.writeValue(new File("d://a.txt"),user);
        //将数据关联到writer中
        mapper.writeValue(new FileWriter("d://b.txt"),user);

    }

    @Test
    public void test2() throws IOException {
        //1、创建对象
        User user = new User();
        user.setName("宫贺");
        user.setId(10086);
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);

    }

    @Test
    public void test3() throws IOException {
        //1、创建user和list对象
        User user = new User();
        user.setName("中国移动");
        user.setId(10086);
        User user1 = new User();
        user1.setName("中国联通");
        user1.setId(10010);
        User user2 = new User();
        user2.setId(10000);
        user2.setName("中国电信");

        List<User> ps = new ArrayList<User>();
        ps.add(user);
        ps.add(user1);
        ps.add(user2);
        //2、创建jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(ps);
        System.out.println(json);

    }

    @Test
    public void test4() throws IOException {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","zmy");
        map.put("age",15);
        map.put("gender","男");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println(json);
    }

    //演示 json字符串转换为java对象
    @Test
    public void test5() throws IOException {
        //初始化json字符串
        String json="{\"name\":\"zmy\",\"id\":15}";
        //创建ObjectMapper对象
        ObjectMapper mapper = new ObjectMapper();
        //转换json字符串为java对象
        User user = mapper.readValue(json, User.class);
        System.out.println(user);
    }
}