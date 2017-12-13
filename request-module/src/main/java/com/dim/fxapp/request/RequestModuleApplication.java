package com.dim.fxapp.request;

import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.finam.ExecuteRequestQuotesImpl;
import com.dim.fxapp.request.execute.finam.ExecuteRequestQuotesLiveImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	/*@Bean(name = "LiveQuotes")
	public ExecuteRequestAbstract<QuotesLive> getLiveQuotes(){
		return new ExecuteRequestQuotesLiveImpl();
	}

	@Bean(name = "Quotes")
	public ExecuteRequestAbstract<Quotes> getQuotes(){
		return new ExecuteRequestQuotesImpl(MAIN,MAIN_FOR_REQUEST);
	}*/
}
