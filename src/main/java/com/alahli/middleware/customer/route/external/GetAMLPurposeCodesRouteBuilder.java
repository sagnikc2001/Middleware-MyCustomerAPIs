package com.alahli.middleware.customer.route.external;

import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.alahli.middleware.customer.models.GetAMLPurposeCodes;
import com.alahli.middleware.customer.models.backends.bancs.GetAMLPurposeCodesRequestBackendType;

@Component
public class GetAMLPurposeCodesRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
		.bindingMode(RestBindingMode.json);
		
		rest("/api/customer")
		.post("v1/GetAMLPurposeCodes")
		.type(GetAMLPurposeCodes.class)
		.consumes("application/json")
		.produces("application/json")
		.to("direct:invokeGetAMLPurposeCodes")
		.outType(GetAMLPurposeCodes.class);
		
		onException(Exception.class)
		.to("bean:oUtils?method=onException(${exchange},\"GetAMLPurposeCodesResponse\",${header.system})")
		.handled(true);
		
		
		from("direct:invokeGetAMLPurposeCodes").routeId("GetAMLPurposeCodes")
		.setHeader("system",constant("MW"))
	    .to("bean:getAMLPurposeCodesService?method=setGetAMLPurposeCodesRequest")
	    .to("bean:getAMLPurposeCodesService?method=prepareGetAMLPurposeCodesRequestBackend")
	    .marshal(new JacksonDataFormat(GetAMLPurposeCodesRequestBackendType.class))
	    .setHeader("system",constant("BANCS"))
	    .to("{{BANCSDBConnector.host}}{{BANCSDBConnector.contextPath}}"+"/v1/BancsAMLPurposeCodeStoreProcedure?bridgeEndpoint=true")
	    .choice()
	        .when().jsonpath("$.GetAMLPurposeCodesResponse[?(@.record !=null && @.record.size()>0)]")
	            .to("bean:getAMLPurposeCodesService?method=prepareGetAMLPurposeCodesFinalResponse")
	            .setHeader("Content-Type", constant("application/json"))
	        .otherwise()
	            .to("bean:oUtils?method=prepareFaultNodeStr('GetAMLPurposeCodesResponse','RECORDNOTFOUND','','','','sysOrAppWithoutBkndError',${exchange})");

		
		
	}

}
