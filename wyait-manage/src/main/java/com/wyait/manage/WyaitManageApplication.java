package com.wyait.manage;

import com.wyait.manage.config.CrosFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan(basePackages = "com.wyait.manage.dao")
public class WyaitManageApplication {

	public static void main(String[] args) {
		SpringApplication sa=new SpringApplication(WyaitManageApplication.class);
//		 禁用devTools热部署
		System.setProperty("spring.devtools.restart.enabled", "false");
		// 禁用命令行更改application.properties属性
		sa.setAddCommandLineProperties(false);
		sa.run(args);
	}


	/**
	 * 配置跨域访问的过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean registerFilter(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.addUrlPatterns("/*");
		bean.setFilter(new CrosFilter());
		return bean;
	}
}
