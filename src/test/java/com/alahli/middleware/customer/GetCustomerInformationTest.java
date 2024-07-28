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

import com.alahli.middleware.customer.models.GetCustomerInformation;
import com.alahli.middleware.customer.models.GetCustomerPFA;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@CamelSpringBootTest
@SpringBootApplication
@WebAppConfiguration

@MockEndpointsAndSkip("http://localhost:8085/api/connector/db/odsdb/v1/GetCustomerMainInfo.*|http://localhost:8082/api/connector/configstore.*")

@UseAdviceWith
@ImportResource({ "classpath:spring/camel-context.xml" })
@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan("com.alahli.middleware.customer.*")
public class GetCustomerInformationTest {

	@Autowired
	CamelContext camelContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProducerTemplate producerTemplate;

	@Autowired
	ApplicationContext applicationContext;

	@EndpointInject("mock://http:localhost:8085/api/connector/db/odsdb/v1/GetCustomerMainInfo")
	private MockEndpoint cdmockEndpoint;

	@EndpointInject("mock://http:localhost:8082/api/connector/configstore")
	private MockEndpoint cdmockEndpoint2;

	@Test
	public void getCustomerInformation_Success() throws Exception {

		String customerInformationRequest = Resources.toString(
				Resources.getResource("mock/frontend/GetCustomerInformation/CustomerInformationRequest.json"),
				Charsets.UTF_8);

		String customerInformationResponseBknd = Resources.toString(
				Resources.getResource("mock/backend/GetCustomerInformation/CustomerInformationResponseBknd.json"),
				Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetCustomerInformation", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:GetCustomerInformation");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(customerInformationResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();

		GetCustomerInformation oGetCustomerInformationRequest = objectMapper.readValue(customerInformationRequest,
				GetCustomerInformation.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{\"channelId\":\"5\",\"branchId\":\"00880\",\"tellerId\":\"\"}");

		GetCustomerInformation oGetCustomerInformationResponse = producerTemplate.requestBodyAndHeaders(
				"direct:GetCustomerInformation", oGetCustomerInformationRequest, headers, GetCustomerInformation.class);

		Assertions.assertTrue(null != oGetCustomerInformationResponse.getGetCustomerInformationResponse().getSuccess()
				.getPartyNumber());

	}

	@Test
	public void getCustomerInformation_Fault() throws Exception {

		String customerInformationRequest_Fault = Resources.toString(
				Resources.getResource("mock/frontend/GetCustomerInformation/CustomerInformationRequest_Fault.json"),
				Charsets.UTF_8);

		String customerInformationResponseBknd_Fault = Resources.toString(
				Resources.getResource("mock/backend/GetCustomerInformation/CustomerInformationResponseBknd_Fault.json"),
				Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetCustomerInformation", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:GetCustomerInformation");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(customerInformationResponseBknd_Fault);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();

		GetCustomerInformation oGetCustomerInformationRequest_Fault = objectMapper
				.readValue(customerInformationRequest_Fault, GetCustomerInformation.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{\"channelId\":\"5\",\"branchId\":\"00880\",\"tellerId\":\"\"}");

		String faultResponse = producerTemplate.requestBodyAndHeaders("direct:GetCustomerInformation",
				oGetCustomerInformationRequest_Fault, headers, String.class);
		
		Assertions.assertTrue(faultResponse.contains("Record not found"));
	}
	
	@Test
	public void getCustomerInformation_emailAddress_Validation() throws Exception{
		
		String customerInformationRequest_emailAddress_Validation = Resources.toString(
				Resources.getResource("mock/frontend/GetCustomerInformation/CustomerInformationRequest_emailAddress_Validation.json"),
				Charsets.UTF_8);

		String customerInformationResponseBknd_Fault = Resources.toString(
				Resources.getResource("mock/backend/GetCustomerInformation/CustomerInformationResponseBknd_Fault.json"),
				Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetCustomerInformation", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:GetCustomerInformation");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(customerInformationResponseBknd_Fault);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		GetCustomerInformation oGetCustomerInformationRequest_emailAddress_Validation = objectMapper
				.readValue(customerInformationRequest_emailAddress_Validation, GetCustomerInformation.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{\"channelId\":\"5\",\"branchId\":\"00880\",\"tellerId\":\"\"}");
		
		String faultResponse = producerTemplate.requestBodyAndHeaders("direct:GetCustomerInformation",
				oGetCustomerInformationRequest_emailAddress_Validation, headers, String.class);
		
		Assertions.assertTrue(faultResponse.contains("Not a valid request"));
	}

}
