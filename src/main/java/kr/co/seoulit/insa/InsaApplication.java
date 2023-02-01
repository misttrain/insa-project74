package kr.co.seoulit.insa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import kr.co.seoulit.insa.sys.filter.SiteMeshFilter;

@SpringBootApplication
public class InsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsaApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<SiteMeshFilter> siteMeshFilter() {
		FilterRegistrationBean<SiteMeshFilter> filter = new FilterRegistrationBean<SiteMeshFilter>();
		filter.setFilter(new SiteMeshFilter());
		return filter;

	}
	
}
//commit test
//수정하고 있잖아
//매장당할듯