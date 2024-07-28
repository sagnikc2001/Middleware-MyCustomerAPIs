package com.alahli.middleware.customer.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alahli.middleware.customer.models.GetCustomerInformation;
import com.alahli.middleware.customer.models.CustomerInfoType;
import com.alahli.middleware.customer.models.CustomerInformationRequest;
import com.alahli.middleware.customer.models.CustomerInformationResponse;
import com.alahli.middleware.customer.models.ServiceHeader;
import com.alahli.middleware.customer.models.backends.ods.CustomerInformationRequestBackend;
import com.alahli.middleware.utility.Utils.NumberUtil;
import com.alahli.middleware.utility.Utils.StringUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GetCustomerInformationService {

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	StringUtil oStringUtil;
	
	@Autowired
	NumberUtil oNumberUtil;

	// storing incoming GetCustomerInformationRequest data
	private CustomerInformationRequest getCustomerInformationRequest;

	// storing the incoming service header
	private ServiceHeader oServiceHeader;

	/**
	 * 
	 * @param getCustomerInformation
	 * @param serviceHeader
	 * @throws Exception
	 */
	public void setGetCustomerInformationRequestIn(GetCustomerInformation getCustomerInformation,
			@Header("ServiceHeader") String serviceHeader) throws Exception {

		this.oServiceHeader = objectMapper.readValue(serviceHeader, ServiceHeader.class);
		this.getCustomerInformationRequest = getCustomerInformation.getGetCustomerInformationRequest();
	}
	
	/**
	 * Prepare request for the external api
	 * 
	 * @return CustomerInformationRequestBackend class
	 * @throws Exception
	 */
	public CustomerInformationRequestBackend prepareCustomerInformationRequestBackend() throws Exception{
		
		CustomerInformationRequestBackend oCustomerInformationRequestBackend = new CustomerInformationRequestBackend();
		com.alahli.middleware.customer.models.backends.ods.CustomerInformationRequest oCustomerInformationRequest = new com.alahli.middleware.customer.models.backends.ods.CustomerInformationRequest();
		
		oCustomerInformationRequestBackend.setCustomerInformationRequest(oCustomerInformationRequest);
		
		oCustomerInformationRequest.setSearchOption(oStringUtil.setDefaultValue(getCustomerInformationRequest.getSearchOption(),""));
		oCustomerInformationRequest.setCif(oStringUtil.setDefaultValue(getCustomerInformationRequest.getCif(),""));
		oCustomerInformationRequest.setPhinenoPhoneNumber(oStringUtil.setDefaultValue(getCustomerInformationRequest.getPhinenoPhoneNumber(),""));
		oCustomerInformationRequest.setBranchNumber(oStringUtil.setDefaultValue(getCustomerInformationRequest.getBranchNumber(),""));
		oCustomerInformationRequest.setIdentificationNumber(getCustomerInformationRequest.getIdentificationNumber());
		oCustomerInformationRequest.setAccountNumber(oStringUtil.setDefaultValue(getCustomerInformationRequest.getAccountNumber(),""));
		oCustomerInformationRequest.setFirstName(oStringUtil.setDefaultValue(getCustomerInformationRequest.getFirstName(),""));
		oCustomerInformationRequest.setLastName(oStringUtil.setDefaultValue(getCustomerInformationRequest.getLastName(),""));
		oCustomerInformationRequest.setEmailAddress(oStringUtil.setDefaultValue(getCustomerInformationRequest.getEmailAddress(),""));
		
		return oCustomerInformationRequestBackend;
	}
	
	/**
	 * Prepare final response by mapping the retrieved response from the external api
	 * 
	 * @param ex Exchange body
	 * @return GetCustomerInformation class
	 * @throws Exception
	 */
	public GetCustomerInformation prepareGetCustomerInformationFinalResponse(Exchange ex) throws Exception{
		
		JsonNode oCustomerInformationResponseNode = objectMapper.readTree(ex.getIn().getBody(String.class));
		
		JsonNode oCustomerInformationResponseJson = oCustomerInformationResponseNode.get("GetCustomerInformationResponse");
		
		JsonNode oSuccessNode = oCustomerInformationResponseJson.get("success");
		
		GetCustomerInformation oGetCustomerInformation = new GetCustomerInformation();
		CustomerInformationResponse oCustomerInformationResponse = new CustomerInformationResponse();
		CustomerInfoType oSuccess = new CustomerInfoType();
		
		oGetCustomerInformation.setGetCustomerInformationResponse(oCustomerInformationResponse);
		oCustomerInformationResponse.setSuccess(oSuccess);
		
		oSuccess.setPartyId(oSuccessNode.get("PARTYID").asText());
		oSuccess.setPartyNumber(oSuccessNode.get("PARTYNUMBER").asInt());
		oSuccess.setPartyName(oSuccessNode.get("PARTYNAME").asText());
		
		return oGetCustomerInformation;
	}
}
