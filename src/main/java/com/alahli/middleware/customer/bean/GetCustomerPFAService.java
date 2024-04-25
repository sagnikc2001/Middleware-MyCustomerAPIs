package com.alahli.middleware.customer.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alahli.middleware.customer.models.GetCustomerPFA;
import com.alahli.middleware.customer.models.GetCustomerPFARequest;
import com.alahli.middleware.customer.models.GetCustomerPFAResponse;
import com.alahli.middleware.customer.models.ServiceHeader;
import com.alahli.middleware.customer.models.SuccessPFA;
import com.alahli.middleware.customer.models.backends.GetCustomerPFARequestBackend;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GetCustomerPFAService {

	@Autowired
	ObjectMapper objectMapper;

	// storing incoming GetCustomerPFARequest data
	private GetCustomerPFARequest getCustomerPFARequest;

	// storing the incoming service header
	private ServiceHeader oServiceHeader;
	
	/**
	 * 
	 * @param getCustomerPFA
	 * @param serviceHeader
	 * @throws Exception
	 */
	public void setGetCustomerPFARequestIn(GetCustomerPFA getCustomerPFA, @Header("ServiceHeader") String serviceHeader)
			throws Exception {

		this.oServiceHeader = objectMapper.readValue(serviceHeader, ServiceHeader.class);
		this.getCustomerPFARequest = getCustomerPFA.getGetCustomerPFARequest();
	}
	
	/**
	 * Prepare request for the external api
	 * 
	 * @return GetCustomerPFARequestBackend class
	 * @throws Exception
	 */
	public GetCustomerPFARequestBackend prepareGetCustomerPFARequestBackend() throws Exception{
		
		com.alahli.middleware.customer.models.backends.GetCustomerPFARequest oGetCustomerPFARequest = new com.alahli.middleware.customer.models.backends.GetCustomerPFARequest();
		GetCustomerPFARequestBackend oGetCustomerPFARequestBackend = new GetCustomerPFARequestBackend();
		
		oGetCustomerPFARequestBackend.setGetCustomerPFARequest(oGetCustomerPFARequest);
		oGetCustomerPFARequest.setShortCIF(getCustomerPFARequest.getShortCIF());
		return oGetCustomerPFARequestBackend;
	}
	
	/**
	 * Prepare final response by mapping the retrieved response from the external api
	 * 
	 * @param ex Exchange body
	 * @return GetCustomerPFA class Response
	 * @throws Exception
	 */
	public GetCustomerPFA prepareGetCustomerPFAFinalResponse(Exchange ex) throws Exception{
		
		JsonNode oGetCustomerPFAResponseNode = objectMapper.readTree(ex.getIn().getBody(String.class));
		
		JsonNode oGetCustomerPFAResponseJson = oGetCustomerPFAResponseNode.get("GetCustomerPFAResponse");
		
		JsonNode oSuccessNode = oGetCustomerPFAResponseJson.get("success");
		
		GetCustomerPFA oGetCustomerPFA = new GetCustomerPFA();
		GetCustomerPFAResponse oGetCustomerPFAResponse = new GetCustomerPFAResponse();
		SuccessPFA oSuccess = new SuccessPFA();
		
		oGetCustomerPFA.setGetCustomerPFAResponse(oGetCustomerPFAResponse);
		oGetCustomerPFAResponse.setSuccess(oSuccess);
		
		oSuccess.setCurrentBalance(oSuccessNode.get("CURRENTBALANCE").asInt());
		oSuccess.setBalanceAverageMonthly(oSuccessNode.get("BALANCEAVERAGEMONTHLY").asInt());
		oSuccess.setBalanceAverageSixMonth(oSuccessNode.get("BALANCEAVERAGESIXMONTH").asInt());
		oSuccess.setInvestmentBalance(oSuccessNode.get("INVESTMENTBALANCE").asInt());
		oSuccess.setKhayratBalance(oSuccessNode.get("KHAYRATBALANCE").asInt());
		oSuccess.setPFA(oSuccessNode.get("PFA").asInt());
		
		return oGetCustomerPFA;
	}
}
