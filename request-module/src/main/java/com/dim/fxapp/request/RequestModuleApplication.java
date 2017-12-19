package com.dim.fxapp.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableEurekaClient
@SpringBootApplication
public class RequestModuleApplication {
	@Value("${currency.mainfinam}")
	String MAIN;

	@Value("${currency.mainfinamrequest}")
	String MAIN_FOR_REQUEST;

	public static void main(String[] args) {
		SpringApplication.run(RequestModuleApplication.class, args);
	}

	/*@Bean(name = "today")
	public Criteria<Criteria> getToday(){
		return new DateCriteria();
	}

	@Bean(name = "date")
	@Scope("prototype")
	public Criteria<Criteria> getDate(LocalDateTime date){
		return new DateCriteria(date);
	}


	@Bean(name = "dateFromTo")
	@Scope("prototype")
	public Criteria<Criteria> getFromTo(LocalDateTime from, LocalDateTime to){
		return new DateFromToCriteria(from,to);
	}*/
}
