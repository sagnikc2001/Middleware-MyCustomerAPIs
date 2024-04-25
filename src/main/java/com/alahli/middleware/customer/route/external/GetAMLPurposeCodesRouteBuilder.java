package com.alahli.middleware.customer.route.external;

import org.apache.camel.Exchange;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.alahli.middleware.customer.models.GetAMLPurposeCodesRequestType;
import com.alahli.middleware.customer.models.GetAMLPurposeCodesResponseType;
import com.alahli.middleware.customer.models.backends.GetAMLPurposeCodesRequestBackendType;

@Component
public class GetAMLPurposeCodesRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
		.bindingMode(RestBindingMode.json);
		
		rest("/api/customer")
		.post("v1/GetAMLPurposeCodes")
		.type(GetAMLPurposeCodesRequestType.class)
		.consumes("application/json")
		.produces("application/json")
		.to("direct:invokeGetAMLPurposeCodes")
		.outType(GetAMLPurposeCodesResponseType.class);
		
		onException(Exception.class) 
		.log("inside exception")
		.to("bean:utils?method=onException(${exchange},\"GetAMLPurposeCodesResponse\",\"MW\")")
		.log("Exception"+"${exception}").handled(true)
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500));
		
		
		from("direct:invokeGetAMLPurposeCodes").routeId("GetAMLPurposeCodes")
	    .to("bean:getAMLPurposeCodesService?method=setGetAMLPurposeCodesRequest")
	    .to("bean:getAMLPurposeCodesService?method=prepareGetAMLPurposeCodesRequestBackend")
	    .marshal(new JacksonDataFormat(GetAMLPurposeCodesRequestBackendType.class))
	    .to("{{BANCSDBConnector.host}}{{BANCSDBConnector.contextPath}}"+"/v1/BancsAMLPurposeCodeStoreProcedure?bridgeEndpoint=true")
	    .choice()
	        .when().jsonpath("$.GetAMLPurposeCodesResponse[?(@.record !=null && @.record.size()>0)]")
	            .to("bean:getAMLPurposeCodesService?method=prepareGetAMLPurposeCodesFinalResponse")
	            .setHeader("Content-Type", constant("application/json"))
	        .otherwise()
	            .to("bean:utils?method=prepareFaultNodeStr('GetAMLPurposeCodesResponse','RECORDNOTFOUND','','','','sysOrAppWithoutBkndError',${exchange})");

		
		
	}

}
