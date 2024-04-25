
package com.alahli.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GetAMLPurposeCodesRequest"
})
public class GetAMLPurposeCodesRequestType {

    @JsonProperty("GetAMLPurposeCodesRequest")
    private GetAMLPurposeCodesRequest getAMLPurposeCodesRequest;

    @JsonProperty("GetAMLPurposeCodesRequest")
    public GetAMLPurposeCodesRequest getGetAMLPurposeCodesRequest() {
        return getAMLPurposeCodesRequest;
    }

    @JsonProperty("GetAMLPurposeCodesRequest")
    public void setGetAMLPurposeCodesRequest(GetAMLPurposeCodesRequest getAMLPurposeCodesRequest) {
        this.getAMLPurposeCodesRequest = getAMLPurposeCodesRequest;
    }

	public GetAMLPurposeCodesRequestType() {
		super();
	}

	public GetAMLPurposeCodesRequestType(GetAMLPurposeCodesRequest getAMLPurposeCodesRequest) {
		super();
		this.getAMLPurposeCodesRequest = getAMLPurposeCodesRequest;
	}

	@Override
	public String toString() {
		return "GetAMLPurposeCodesRequest [getAMLPurposeCodesRequest=" + getAMLPurposeCodesRequest + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAMLPurposeCodesRequest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetAMLPurposeCodesRequestType other = (GetAMLPurposeCodesRequestType) obj;
		return Objects.equals(getAMLPurposeCodesRequest, other.getAMLPurposeCodesRequest);
	}
}
