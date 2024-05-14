package com.alahli.middleware.customer.route.external;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import org.apache.camel.component.jackson.JacksonDataFormat;

import com.alahli.middleware.customer.models.GetCustomerPFA;
import com.alahli.middleware.customer.models.backends.ods.GetCustomerPFARequestBackend;

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
		.to("bean:oUtils?method=onException(${exchange},\"GetCustomerPFAResponse\",${header.system})")
		.handled(true);
		
		from("direct:GetCustomerPFA").routeId("GetCustomerPFA")
		.setHeader("system",constant("MW"))
		.to("bean:getCustomerPFAService?method=setGetCustomerPFARequestIn")
		.to("bean:getCustomerPFAService?method=prepareGetCustomerPFARequestBackend")
		.choice()
		.when().simple("${body.getGetCustomerPFARequest.getShortCIF.length()} != 8")
			.to("bean:oUtils?method=prepareFaultNodeStr(\"GetCustomerPFAResponse\",\"INCORRECTVALUE\",\"shortCIF length not equal to 8\",\"\",\"\",\"validationsCust\",${exchange})")
		.otherwise()
			.marshal(new JacksonDataFormat(GetCustomerPFARequestBackend.class))
			.setHeader("system",constant("ODS"))
			.to("{{ODSDBConnector.host}}{{ODSDBConnector.contextPath}}"+"/v1/CallProcedureToGetPFADetails?bridgeEndpoint=true")
			.choice()
				.when().jsonpath("$.GetCustomerPFAResponse[?(@.success != null && @.success.size()>0)]")
					.to("bean:getCustomerPFAService?method=prepareGetCustomerPFAFinalResponse")
					.setHeader("Content-Type", constant("application/json"))
				.otherwise()
					.to("bean:oUtils?method=prepareFaultNodeStr(\"GetCustomerPFAResponse\",\"RECORDNOTFOUND_ODS\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})");
	}

	
}
