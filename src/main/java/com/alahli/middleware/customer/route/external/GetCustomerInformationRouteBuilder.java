package com.alahli.middleware.customer.route.external;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.alahli.middleware.customer.models.GetCustomerInformation;
import com.alahli.middleware.customer.models.backends.ods.CustomerInformationRequestBackend;

@Component
public class GetCustomerInformationRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
		.bindingMode(RestBindingMode.json);
		
		rest("/api/customer")
		.post("/v1/GetCustomerInformation")
		.type(GetCustomerInformation.class)
		.consumes("application/json")
		.produces("application/json")
		.to("direct:GetCustomerInformation")
		.outType(GetCustomerInformation.class);
		
		onException(Exception.class)
		.to("bean:oUtils?method=onException(${exchange},\"CustomerInformationResponse\",${header.system})")
		.handled(true);
		
		from("direct:GetCustomerInformation").routeId("GetCustomerInformation")
		.setHeader("system",constant("MW"))
		.to("bean:getCustomerInformationService?method=setGetCustomerInformationRequestIn")
		.to("bean:getCustomerInformationService?method=prepareCustomerInformationRequestBackend")
		.choice()
			.when().simple("${body.customerInformationRequest.emailAddress.length()} == 0")
				.to("bean:oUtils?method=prepareFaultNodeStr(\"CustomerInformationResponse\",\"INCORRECTVALUE\",\"Not a valid request\",\"\",\"\",\"validationsCust\",${exchange})")
			.otherwise()
				.marshal(new JacksonDataFormat(CustomerInformationRequestBackend.class))
				.setHeader("system",constant("ODS"))
				.to("{{ODSDBConnector.host}}{{ODSDBConnector.contextPath}}"+"/v1/GetCustomerMainInfo?bridgeEndpoint=true")
				.choice()
					.when().jsonpath("$.GetCustomerInformationResponse[?(@.success != null && @.success.size()>0)]")
						.to("bean:getCustomerInformationService?method=prepareGetCustomerInformationFinalResponse")
						.setHeader("Content-Type", constant("application/json"))
					.otherwise()
						.to("bean:oUtils?method=prepareFaultNodeStr(\"CustomerInformationResponse\",\"RECORDNOTFOUND_ODS\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})");
		
	}

}
