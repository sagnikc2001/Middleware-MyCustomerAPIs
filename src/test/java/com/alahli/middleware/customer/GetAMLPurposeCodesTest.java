package com.alahli.middleware.customer;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpointsAndSkip;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alahli.middleware.customer.models.GetAMLPurposeCodesRequestType;
import com.alahli.middleware.customer.models.GetAMLPurposeCodesResponseType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@CamelSpringBootTest
@SpringBootApplication
@WebAppConfiguration

@MockEndpointsAndSkip("http://localhost:8085/api/connector/db/bancsdb/v1/BancsAMLPurposeCodeStoreProcedure.*|http://localhost:8082/api/connector/configstore.*")

@UseAdviceWith
@ImportResource({ "classpath:spring/camel-context.xml" })
@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan("com.alahli.middleware.customer.*")

public class GetAMLPurposeCodesTest {

	@Autowired
	CamelContext camelContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProducerTemplate producerTemplate;

	@Autowired
	ApplicationContext applicationContext;

	@EndpointInject("mock://http:localhost:8085/api/connector/db/bancsdb/v1/BancsAMLPurposeCodeStoreProcedure")
	private MockEndpoint cdmockEndpoint;

	@EndpointInject("mock://http:localhost:8082/api/connector/configstore")
	private MockEndpoint cdmockEndpoint2;

	@Test
	public void getAMLPurposeCodes_Success() throws Exception {

		String getAMLPurposeCodesRequest = Resources.toString(
				Resources.getResource("mock/frontend/GetAMLPurposeCodes/request/GetAMLPurposeCodesRequest.json"),
				Charsets.UTF_8);

		String getAMLPurposeCodesResponseBknd = Resources.toString(
				Resources.getResource("mock/backend/GetAMLPurposeCodes/GetAMLPurposeCodesResponseBknd.json"),
				Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetAMLPurposeCodes", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:invokeGetAMLPurposeCodes");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getAMLPurposeCodesResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();

		GetAMLPurposeCodesRequestType oGetAMLPurposeCodesRequestType = objectMapper.readValue(getAMLPurposeCodesRequest,
				GetAMLPurposeCodesRequestType.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{\"channelId\":\"5\",\"branchId\":\"00880\",\"tellerId\":\"\"}");

		GetAMLPurposeCodesResponseType oGetAMLPurposeCodesResponseType = producerTemplate.requestBodyAndHeaders(
				"direct:invokeGetAMLPurposeCodes", oGetAMLPurposeCodesRequestType, headers,
				GetAMLPurposeCodesResponseType.class);

		System.out.println("Response" + oGetAMLPurposeCodesResponseType.getGetAMLPurposeCodesResponse().getSuccess()
				.getRecord().get(0).getCategory());

		Assertions.assertTrue(null != oGetAMLPurposeCodesResponseType.getGetAMLPurposeCodesResponse().getSuccess()
				.getRecord().get(0).getCategory());

	}

	@Test
	public void getAMLPurposeCodes_Fault() throws Exception {

		String getAMLPurposeCodesRequest = Resources.toString(
				Resources.getResource("mock/frontend/GetAMLPurposeCodes/request/GetAMLPurposeCodesRequest_Fault.json"),
				Charsets.UTF_8);

		String getAMLPurposeCodesResponse_Fault = Resources.toString(
				Resources.getResource("mock/backend/GetAMLPurposeCodes/GetAMLPurposeCodesResponseBknd_Fault.json"),
				Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetAMLPurposeCodes", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:invokeGetAMLPurposeCodes");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getAMLPurposeCodesResponse_Fault);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();

		GetAMLPurposeCodesRequestType oGetAMLPurposeCodesRequestType = objectMapper.readValue(getAMLPurposeCodesRequest,
				GetAMLPurposeCodesRequestType.class);
		
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{\"channelId\":\"5\",\"branchId\":\"00880\",\"tellerId\":\"\"}");

		String faultResponse = producerTemplate.requestBodyAndHeaders("direct:invokeGetAMLPurposeCodes",
				oGetAMLPurposeCodesRequestType, headers, String.class);
		
		System.out.println(faultResponse);

		Assertions.assertTrue(faultResponse.contains("Record not found"));
	}

}
