package com.alahli.middleware.customer.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GetCustomerPFARequest",
    "GetCustomerPFAResponse"
})
public class GetCustomerPFA {
	
	@JsonProperty("GetCustomerPFARequest")
	private GetCustomerPFARequest getCustomerPFARequest;
	
	@JsonProperty("GetCustomerPFAResponse")
	private GetCustomerPFAResponse getCustomerPFAResponse;

	public GetCustomerPFARequest getGetCustomerPFARequest() {
		return getCustomerPFARequest;
	}

	public void setGetCustomerPFARequest(GetCustomerPFARequest getCustomerPFARequest) {
		this.getCustomerPFARequest = getCustomerPFARequest;
	}

	public GetCustomerPFAResponse getGetCustomerPFAResponse() {
		return getCustomerPFAResponse;
	}

	public void setGetCustomerPFAResponse(GetCustomerPFAResponse getCustomerPFAResponse) {
		this.getCustomerPFAResponse = getCustomerPFAResponse;
	}

	public GetCustomerPFA() {
		super();
	}

	public GetCustomerPFA(GetCustomerPFARequest getCustomerPFARequest, GetCustomerPFAResponse getCustomerPFAResponse) {
		super();
		this.getCustomerPFARequest = getCustomerPFARequest;
		this.getCustomerPFAResponse = getCustomerPFAResponse;
	}

	@Override
	public String toString() {
		return "GetCustomerPFA [getCustomerPFARequest=" + getCustomerPFARequest + ", getCustomerPFAResponse="
				+ getCustomerPFAResponse + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCustomerPFARequest, getCustomerPFAResponse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetCustomerPFA other = (GetCustomerPFA) obj;
		return Objects.equals(getCustomerPFARequest, other.getCustomerPFARequest)
				&& Objects.equals(getCustomerPFAResponse, other.getCustomerPFAResponse);
	}

}
