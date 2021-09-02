package com.dispatcher.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Auther: yjntue
 * @Date: 2019/1/10 21:10
 * @Description: ESB配置
 */
@Configuration
@ImportResource(value = {"classpath*:business.xml"})
//@ImportResource(value = {"classpath*:*.xml"})
public class EsbConfig {

}
