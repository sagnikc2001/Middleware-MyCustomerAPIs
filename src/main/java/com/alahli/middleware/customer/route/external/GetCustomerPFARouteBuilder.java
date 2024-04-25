package com.alahli.middleware.customer.route.external;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import org.apache.camel.component.jackson.JacksonDataFormat;

import com.alahli.middleware.customer.models.GetCustomerPFA;
import com.alahli.middleware.customer.models.backends.GetCustomerPFARequestBackend;

@Component
public class GetCustomerPFARouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
		.bindingMode(RestBindingMode.json);
		
		rest("/api/customer/v1")
		.post("/GetCustomerPFA")
		.type(GetCustomerPFA.class)
		.consumes("application/json")
		.produces("application/json")
		.to("direct:GetCustomerPFA")
		.outType(GetCustomerPFA.class);
		
		onException(Exception.class) 
		.log("inside exception")
		.to("bean:utils?method=onException(${exchange},\"GetCustomerPFAResponse\",\"MW\")")
		.log("Exception"+"${exception}").handled(true)
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500));
		
		from("direct:GetCustomerPFA").routeId("GetCustomerPFA")
		.to("bean:getCustomerPFAService?method=setGetCustomerPFARequestIn")
		.to("bean:getCustomerPFAService?method=prepareGetCustomerPFARequestBackend")
		.choice()
		.when().simple("${body.getGetCustomerPFARequest.getShortCIF.length()} != 8")
			.to("bean:utils?method=prepareFaultNodeStr(\"GetCustomerPFAResponse\",\"INCORRECTVALUE\",\"shortCIF length not equal to 8\",\"\",\"\",\"validationsCust\",${exchange})")
		.otherwise()
			.marshal(new JacksonDataFormat(GetCustomerPFARequestBackend.class))
			.to("{{ODSDBConnector.host}}{{ODSDBConnector.contextPath}}"+"/v1/CallProcedureToGetPFADetails?bridgeEndpoint=true")
			.choice()
				.when().jsonpath("$.GetCustomerPFAResponse[?(@.success != null && @.success.size()>0)]")
					.to("bean:getCustomerPFAService?method=prepareGetCustomerPFAFinalResponse")
					.setHeader("Content-Type", constant("application/json"))
				.otherwise()
					.to("bean:utils?method=prepareFaultNodeStr(\"GetCustomerPFAResponse\",\"RECORDNOTFOUND_ODS\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})");
	}

	
}
