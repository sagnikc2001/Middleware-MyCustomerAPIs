package com.alahli.middleware.customer.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alahli.middleware.customer.models.GetAMLPurposeCodes;
import com.alahli.middleware.customer.models.GetAMLPurposeCodesRequest;
import com.alahli.middleware.customer.models.GetAMLPurposeCodesResponse;
import com.alahli.middleware.customer.models.ServiceHeader;
import com.alahli.middleware.customer.models.Success;
import com.alahli.middleware.customer.models.backends.bancs.GetAMLPurposeCodesRequestBackendType;
import com.alahli.middleware.customer.utils.CustomerUtils;
import com.alahli.middleware.utility.Utils.StringUtil;
import com.alahli.middleware.customer.models.Record;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GetAMLPurposeCodesService {

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	StringUtil oStringUtil;
	
	@Autowired
	CustomerUtils oCustomerUtils;

	// storing incoming GetAMLPurposeCodesRequest data
	private GetAMLPurposeCodesRequest oGetAMLPurposeCodesRequest;
	
	// storing the incoming service header
	private ServiceHeader oServiceHeader;

	/**
	 * 
	 * @param getAMLPurposeCodesRequestType
	 * @param serviceHeader
	 * @throws Exception
	 */
	public void setGetAMLPurposeCodesRequest(GetAMLPurposeCodes getAMLPurposeCodes,
			@Header("ServiceHeader") String serviceHeader) throws Exception {

		this.oServiceHeader = objectMapper.readValue(serviceHeader, ServiceHeader.class);
		this.oGetAMLPurposeCodesRequest = getAMLPurposeCodes.getGetAMLPurposeCodesRequest();
	}

	/**
	 * Prepare request for the external api
	 * 
	 * @return GetAMLPurposeCodesRequestBackendType class
	 * @throws Exception
	 */
	public GetAMLPurposeCodesRequestBackendType prepareGetAMLPurposeCodesRequestBackend() throws Exception {

		GetAMLPurposeCodesRequestBackendType oGetAMLPurposeCodesRequestBackendType = new GetAMLPurposeCodesRequestBackendType();

		com.alahli.middleware.customer.models.backends.bancs.GetAMLPurposeCodesRequest oGetAMLPurposeCodesRequestBackend = new com.alahli.middleware.customer.models.backends.bancs.GetAMLPurposeCodesRequest();

		oGetAMLPurposeCodesRequestBackendType.setGetAMLPurposeCodesRequestBackend(oGetAMLPurposeCodesRequestBackend);

		oGetAMLPurposeCodesRequestBackend.setChannelId(oStringUtil.setDefaultValue(oGetAMLPurposeCodesRequest.getChannelId(),""));
		oGetAMLPurposeCodesRequestBackend.setCustomerType(oStringUtil.setDefaultValue(oGetAMLPurposeCodesRequest.getCustomerType(),""));
		oGetAMLPurposeCodesRequestBackend.setTransactionType(oStringUtil.setDefaultValue(oGetAMLPurposeCodesRequest.getTransactionType(),""));

		return oGetAMLPurposeCodesRequestBackendType;
	}

	/**
	 * Prepare final response by mapping the retrieved response from the external api
	 * 
	 * @param ex Exchange body
	 * @return JsonNode for the AMLPurposeCodes Response
	 * @throws Exception
	 */
	public GetAMLPurposeCodes prepareGetAMLPurposeCodesFinalResponse(Exchange ex) throws Exception {

		JsonNode oGetAMLPurposeCodesResponseNode = objectMapper.readTree(ex.getIn().getBody(String.class));

		JsonNode oGetAMLPurposeCodesResponse = oGetAMLPurposeCodesResponseNode.get("GetAMLPurposeCodesResponse");

		JsonNode orecordArrayNode = oGetAMLPurposeCodesResponse.get("record");

		GetAMLPurposeCodes oGetAMLPurposeCodes = new GetAMLPurposeCodes();
		GetAMLPurposeCodesResponse getAMLPurposeCodesResponse = new GetAMLPurposeCodesResponse();
		Success oSuccess = new Success();
		List<Record> oRecordList = new ArrayList<Record>();

		oGetAMLPurposeCodes.setGetAMLPurposeCodesResponse(getAMLPurposeCodesResponse);
		getAMLPurposeCodesResponse.setSuccess(oSuccess);
		oSuccess.setRecord(oRecordList);

		if (orecordArrayNode.isArray()) {

			for (JsonNode recordNode : orecordArrayNode) {

				Record record = new Record();

				record.setChannelId(recordNode.get("CHANNEL_ID").asText());
				record.setTransactionType(recordNode.get("TRANS_TYPE").asText());
				record.setCustomerType(recordNode.get("CUSTOMER_TYPE").asText());
				record.setCategory(recordNode.get("CATEGORY").asText());
				record.setCategoryDescEn(recordNode.get("CATEGORY_EN").asText());
				record.setCategoryDescAr(oCustomerUtils.toArabicString(oCustomerUtils.convertHexToString(recordNode.get("CATEGORY_AR").asText())));
				record.setPurposeCode(recordNode.get("PURPOSE_TRAN").asText());
				record.setPurposeCodeDescEn(recordNode.get("PURPOSE_TRAN_EN").asText());
				record.setPurposeCodeDescAr(
						oCustomerUtils.toArabicString(oCustomerUtils.convertHexToString(recordNode.get("PURPOSE_TRAN_AR").asText())));
				record.setSecondLevelPurposeCode(recordNode.get("SEC_PUR_TRAN").asText());
				record.setSecondLevelPurposeCodeDescEn(recordNode.get("SEC_PUR_TRAN_EN").asText());
				record.setSecondLevelPurposeCodeDescAr(
						oCustomerUtils.toArabicString(oCustomerUtils.convertHexToString(recordNode.get("SEC_PUR_TRAN_AR").asText())));
				record.setRelationship(recordNode.get("RELATIONSHIP").asText());
				record.setRelationshipDescEn(recordNode.get("RELATIONSHIP_EN").asText());
				record.setRelationshipDescAr(
						oCustomerUtils.toArabicString(oCustomerUtils.convertHexToString(recordNode.get("RELATIONSHIP_AR").asText())));

				oRecordList.add(record);
			}
		}

		return oGetAMLPurposeCodes;
	}

}
