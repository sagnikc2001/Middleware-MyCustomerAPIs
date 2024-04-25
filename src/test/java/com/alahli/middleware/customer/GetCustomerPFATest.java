package com.alahli.middleware.customer;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Configuration;
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
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alahli.middleware.customer.models.GetCustomerPFA;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@CamelSpringBootTest
@SpringBootApplication
@WebAppConfiguration

@MockEndpointsAndSkip("http://localhost:8085/api/connector/db/odsdb/v1/CallProcedureToGetPFADetails.*|http://localhost:8082/api/connector/configstore.*")

@UseAdviceWith
@ImportResource({ "classpath:spring/camel-context.xml" })
@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan("com.alahli.middleware.customer.*")
public class GetCustomerPFATest {

	@Autowired
	CamelContext camelContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProducerTemplate producerTemplate;

	@Autowired
	ApplicationContext applicationContext;

	@EndpointInject("mock://http:localhost:8085/api/connector/db/odsdb/v1/CallProcedureToGetPFADetails")
	private MockEndpoint cdmockEndpoint;

	@EndpointInject("mock://http:localhost:8082/api/connector/configstore")
	private MockEndpoint cdmockEndpoint2;

	@Test
	public void getCustomerPFA_Success() throws Exception {

		String getCustomerPFARequest = Resources.toString(
				Resources.getResource("mock/frontend/GetCustomerPFA/GetCustomerPFARequest.json"), Charsets.UTF_8);

		String getCustomerPFAResponseBknd = Resources.toString(
				Resources.getResource("mock/backend/GetCustomerPFA/GetCustomerPFAResponseBknd.json"), Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetCustomerPFA", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:GetCustomerPFA");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getCustomerPFAResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();

		GetCustomerPFA oGetCustomerPFARequest = objectMapper.readValue(getCustomerPFARequest, GetCustomerPFA.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{\"channelId\":\"5\",\"branchId\":\"00880\",\"tellerId\":\"\"}");

		GetCustomerPFA oGetCustomerPFAResponse = producerTemplate.requestBodyAndHeaders("direct:GetCustomerPFA",
				oGetCustomerPFARequest, headers, GetCustomerPFA.class);

		System.out.println("Response : "
				+ oGetCustomerPFAResponse.getGetCustomerPFAResponse().getSuccess().getBalanceAverageMonthly());

		Assertions.assertTrue(
				null != oGetCustomerPFAResponse.getGetCustomerPFAResponse().getSuccess().getBalanceAverageMonthly());
	}

	@Test
	public void getCustomerPFA_Fault() throws Exception {

		String getCustomerPFARequest_Fault = Resources.toString(
				Resources.getResource("mock/frontend/GetCustomerPFA/GetCustomerPFARequest_Fault.json"), Charsets.UTF_8);

		String getCustomerPFAResponseBknd_Fault = Resources.toString(
				Resources.getResource("mock/backend/GetCustomerPFA/GetCustomerPFAResponseBknd_Fault.json"),
				Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetCustomerPFA", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:GetCustomerPFA");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getCustomerPFAResponseBknd_Fault);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();

		GetCustomerPFA oGetCustomerPFARequest_Fault = objectMapper.readValue(getCustomerPFARequest_Fault,
				GetCustomerPFA.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{\"channelId\":\"5\",\"branchId\":\"00880\",\"tellerId\":\"\"}");

		String faultResponse = producerTemplate.requestBodyAndHeaders("direct:GetCustomerPFA",
				oGetCustomerPFARequest_Fault, headers, String.class);

		System.out.println("Response: " + faultResponse);

		Assertions.assertTrue(faultResponse.contains("Record not found"));

	}

	@Test
	public void getCustomerPFA_shortCIF_Validation() throws Exception {

		String getCustomerPFARequest_shortCIF_Fault = Resources.toString(
				Resources.getResource("mock/frontend/GetCustomerPFA/GetCustomerPFARequest_shortCIF_Fault.json"),
				Charsets.UTF_8);

		String getCustomerPFAResponseBknd_Fault = Resources.toString(
				Resources.getResource("mock/backend/GetCustomerPFA/GetCustomerPFAResponseBknd_Fault.json"),
				Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetCustomerPFA", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:GetCustomerPFA");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getCustomerPFAResponseBknd_Fault);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();

		GetCustomerPFA oGetCustomerPFARequest_shortCIF_Fault = objectMapper.readValue(getCustomerPFARequest_shortCIF_Fault,
				GetCustomerPFA.class);
		
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{\"channelId\":\"5\",\"branchId\":\"00880\",\"tellerId\":\"\"}");

		String faultResponse = producerTemplate.requestBodyAndHeaders("direct:GetCustomerPFA",
				oGetCustomerPFARequest_shortCIF_Fault, headers, String.class);

		System.out.println("Response: " + faultResponse);

		Assertions.assertTrue(faultResponse.contains("shortCIF length not equal to 8"));
	}
}
