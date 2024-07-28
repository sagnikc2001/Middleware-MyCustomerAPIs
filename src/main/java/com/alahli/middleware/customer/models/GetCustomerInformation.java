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
public class GetCustomerInformation {

	@JsonProperty("CustomerInformationRequest")
	private CustomerInformationRequest getCustomerInformationRequest;
	
	@JsonProperty("CustomerInformationResponse")
	private CustomerInformationResponse getCustomerInformationResponse;

	public CustomerInformationRequest getGetCustomerInformationRequest() {
		return getCustomerInformationRequest;
	}

	public void setGetCustomerInformationRequest(CustomerInformationRequest getCustomerInformationRequest) {
		this.getCustomerInformationRequest = getCustomerInformationRequest;
	}

	public CustomerInformationResponse getGetCustomerInformationResponse() {
		return getCustomerInformationResponse;
	}

	public void setGetCustomerInformationResponse(CustomerInformationResponse getCustomerInformationResponse) {
		this.getCustomerInformationResponse = getCustomerInformationResponse;
	}

	public GetCustomerInformation() {
		super();
	}

	public GetCustomerInformation(CustomerInformationRequest getCustomerInformationRequest,
			CustomerInformationResponse getCustomerInformationResponse) {
		super();
		this.getCustomerInformationRequest = getCustomerInformationRequest;
		this.getCustomerInformationResponse = getCustomerInformationResponse;
	}

	@Override
	public String toString() {
		return "GetCustomerInformation [getCustomerInformationRequest=" + getCustomerInformationRequest
				+ ", getCustomerInformationResponse=" + getCustomerInformationResponse + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCustomerInformationRequest, getCustomerInformationResponse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetCustomerInformation other = (GetCustomerInformation) obj;
		return Objects.equals(getCustomerInformationRequest, other.getCustomerInformationRequest)
				&& Objects.equals(getCustomerInformationResponse, other.getCustomerInformationResponse);
	}

}
