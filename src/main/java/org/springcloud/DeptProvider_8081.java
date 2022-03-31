package org.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

// 启动类
@SpringBootApplication
// 开启eureka 在服务启动后自动将服务注册到eureka中
@EnableEurekaClient
// 服务发现及DeptController中的discovery方法
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class DeptProvider_8081 {

	public static void main(String[] args) {
		SpringApplication.run(DeptProvider_8081.class, args);
	}

	// 增加一个servlet
	@Bean
	public ServletRegistrationBean hystrixMetricsStreamServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
		// Single Hystrix应用程序： http://hystrix-app:port/actuator/hystrix.stream
		registrationBean.addUrlMappings("/actuator/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		registrationBean.setLoadOnStartup(1);
		return registrationBean;
	}

}
