package com.dim.fxapp.request;

import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.ExecuteRequestQuotesImpl;
import com.dim.fxapp.request.execute.ExecuteRequestQuotesLiveImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

//@EnableEurekaClient
@SpringBootApplication
public class RequestModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestModuleApplication.class, args);
	}

	@Bean(name = "LiveQuotes")
	public ExecuteRequestAbstract<QuotesLive> getLiveQuotes(){
		return new ExecuteRequestQuotesLiveImpl();
	}

	@Bean(name = "Quotes")
	public ExecuteRequestAbstract getQuotes(){
		return new ExecuteRequestQuotesImpl();
	}
}
