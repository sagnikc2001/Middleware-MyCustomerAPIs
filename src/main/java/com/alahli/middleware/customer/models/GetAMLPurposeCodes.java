package com.alahli.middleware.customer.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GetAMLPurposeCodesRequest",
    "GetAMLPurposeCodesResponse"
})
public class GetAMLPurposeCodes {
	
	@JsonProperty("GetAMLPurposeCodesRequest")
	private GetAMLPurposeCodesRequest getAMLPurposeCodesRequest;
	
	@JsonProperty("GetAMLPurposeCodesResponse")
	private GetAMLPurposeCodesResponse getAMLPurposeCodesResponse;

	public GetAMLPurposeCodesRequest getGetAMLPurposeCodesRequest() {
		return getAMLPurposeCodesRequest;
	}

	public void setGetAMLPurposeCodesRequest(GetAMLPurposeCodesRequest getAMLPurposeCodesRequest) {
		this.getAMLPurposeCodesRequest = getAMLPurposeCodesRequest;
	}

	public GetAMLPurposeCodesResponse getGetAMLPurposeCodesResponse() {
		return getAMLPurposeCodesResponse;
	}

	public void setGetAMLPurposeCodesResponse(GetAMLPurposeCodesResponse getAMLPurposeCodesResponse) {
		this.getAMLPurposeCodesResponse = getAMLPurposeCodesResponse;
	}

	public GetAMLPurposeCodes() {
		super();
	}

	public GetAMLPurposeCodes(GetAMLPurposeCodesRequest getAMLPurposeCodesRequest,
			GetAMLPurposeCodesResponse getAMLPurposeCodesResponse) {
		super();
		this.getAMLPurposeCodesRequest = getAMLPurposeCodesRequest;
		this.getAMLPurposeCodesResponse = getAMLPurposeCodesResponse;
	}

	@Override
	public String toString() {
		return "GetAMLPurposeCodes [getAMLPurposeCodesRequest=" + getAMLPurposeCodesRequest
				+ ", getAMLPurposeCodesResponse=" + getAMLPurposeCodesResponse + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAMLPurposeCodesRequest, getAMLPurposeCodesResponse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetAMLPurposeCodes other = (GetAMLPurposeCodes) obj;
		return Objects.equals(getAMLPurposeCodesRequest, other.getAMLPurposeCodesRequest)
				&& Objects.equals(getAMLPurposeCodesResponse, other.getAMLPurposeCodesResponse);
	}

}
