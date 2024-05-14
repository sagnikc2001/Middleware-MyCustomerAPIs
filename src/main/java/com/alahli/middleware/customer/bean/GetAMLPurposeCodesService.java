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
				record.setCategoryDescAr(toArabicString(convertHexToString(recordNode.get("CATEGORY_AR").asText())));
				record.setPurposeCode(recordNode.get("PURPOSE_TRAN").asText());
				record.setPurposeCodeDescEn(recordNode.get("PURPOSE_TRAN_EN").asText());
				record.setPurposeCodeDescAr(
						toArabicString(convertHexToString(recordNode.get("PURPOSE_TRAN_AR").asText())));
				record.setSecondLevelPurposeCode(recordNode.get("SEC_PUR_TRAN").asText());
				record.setSecondLevelPurposeCodeDescEn(recordNode.get("SEC_PUR_TRAN_EN").asText());
				record.setSecondLevelPurposeCodeDescAr(
						toArabicString(convertHexToString(recordNode.get("SEC_PUR_TRAN_AR").asText())));
				record.setRelationship(recordNode.get("RELATIONSHIP").asText());
				record.setRelationshipDescEn(recordNode.get("RELATIONSHIP_EN").asText());
				record.setRelationshipDescAr(
						toArabicString(convertHexToString(recordNode.get("RELATIONSHIP_AR").asText())));

				oRecordList.add(record);
			}
		}

		return oGetAMLPurposeCodes;
	}

	/**
	 * Converts a given encoded Arabic string to a readable Arabic
	 * string.
	 * 
	 * @param data The encoded Arabic string in the format "key:value"
	 * @return Readable Arabic string
	 */
	public String toArabicString(String data) {
		if (data == null || "".equals(data))
			return "";
		String[] dataParts = data.split(":");
		if (dataParts.length != 2)
			return "";
		String[] charCodes = dataParts[1].split(",");
		StringBuilder sbhex = new StringBuilder();
		for (int i = 0; i < charCodes.length; i++) {

			if ("".equals(charCodes[i])) {
				charCodes[i] = "0";
			}
			if (Integer.parseInt(charCodes[i].trim()) >= 16)
				sbhex.append(Integer.toHexString(Integer.parseInt(charCodes[i].trim())));
		}

		StringBuilder sb = new StringBuilder();
		String str = sbhex.toString();
		for (int i = 0; i < str.length(); i = i + 2) {
			String tempstr = str.substring(i, i + 2);
			if (tempstr.charAt(0) == 'b')
				tempstr = "066" + tempstr.substring(1, 2);
			else if (tempstr.charAt(0) == 'c')
				tempstr = "062" + tempstr.substring(1, 2);
			else if (tempstr.charAt(0) == 'd') {
				/*
				 * if(tempstr.equals( "DB")) tempstr = "06AC"; else if(tempstr.equals( "DC"))
				 * tempstr = "06AD"; else if(tempstr.equals( "DD")) tempstr = "06AE";
				 * if(tempstr.equals( "DE")) tempstr = "0635"; else if(tempstr.equals( "DF"))
				 * tempstr = "06B0";
				 */
				if (tempstr.equals("db") || tempstr.equals("de") || tempstr.equals("dc") || tempstr.equals("dd")
						|| tempstr.equals("df"))
					tempstr = tempstr;
				else
					tempstr = "063" + tempstr.substring(1, 2);
			} else if (tempstr.charAt(0) == 'e')
				tempstr = "064" + tempstr.substring(1, 2);
			else if (tempstr.charAt(0) == 'f')
				tempstr = "065" + tempstr.substring(1, 2);
			int j;
			j = Integer.parseInt(tempstr, 16);

			char c = (char) j;
			sb.append(c);
			// char a = str.charAt(i);
			// char b = str.charAt(i + 1);
			// sb.append((char) ((hexToInt(a) << 4) | hexToInt(b)));
		}
		return sb.toString();
	}

	/**
	 * Converts a given hexadecimal string to its corresponding string representation.
	 * 
	 * @param hex The input hexadecimal string to be converted
	 * @return The string representation of the hexadecimal input
	 */

	public String convertHexToString(String hex) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		temp.append("Decimal:");
		for (int i = 0; i < hex.length() - 1; i += 2) {
			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);
			temp.append(decimal);
			temp.append(",");
		}
		// System.out.println(sb.toString());
		// return sb.toString();
		return temp.toString();
	}

}
