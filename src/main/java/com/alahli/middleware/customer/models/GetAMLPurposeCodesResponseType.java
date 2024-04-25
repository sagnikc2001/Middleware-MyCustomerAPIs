
package com.alahli.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GetAMLPurposeCodesResponse"
})
public class GetAMLPurposeCodesResponseType {

    @JsonProperty("GetAMLPurposeCodesResponse")
    private GetAMLPurposeCodesResponse getAMLPurposeCodesResponse;

    @JsonProperty("GetAMLPurposeCodesResponse")
    public GetAMLPurposeCodesResponse getGetAMLPurposeCodesResponse() {
        return getAMLPurposeCodesResponse;
    }

    @JsonProperty("GetAMLPurposeCodesResponse")
    public void setGetAMLPurposeCodesResponse(GetAMLPurposeCodesResponse getAMLPurposeCodesResponse) {
        this.getAMLPurposeCodesResponse = getAMLPurposeCodesResponse;
    }

	public GetAMLPurposeCodesResponseType() {
		super();
	}

	public GetAMLPurposeCodesResponseType(GetAMLPurposeCodesResponse getAMLPurposeCodesResponse) {
		super();
		this.getAMLPurposeCodesResponse = getAMLPurposeCodesResponse;
	}

	@Override
	public String toString() {
		return "GetAMLPurposeCodesResponse [getAMLPurposeCodesResponse=" + getAMLPurposeCodesResponse + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAMLPurposeCodesResponse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetAMLPurposeCodesResponseType other = (GetAMLPurposeCodesResponseType) obj;
		return Objects.equals(getAMLPurposeCodesResponse, other.getAMLPurposeCodesResponse);
	}

}
