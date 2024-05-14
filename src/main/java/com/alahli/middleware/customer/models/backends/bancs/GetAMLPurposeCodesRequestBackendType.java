
package com.alahli.middleware.customer.models.backends.bancs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GetAMLPurposeCodesRequest"
})
public class GetAMLPurposeCodesRequestBackendType {

    @JsonProperty("GetAMLPurposeCodesRequest")
    private GetAMLPurposeCodesRequest getAMLPurposeCodesRequest;

    @JsonProperty("GetAMLPurposeCodesRequest")
    public GetAMLPurposeCodesRequest getGetAMLPurposeCodesRequestBackend() {
        return getAMLPurposeCodesRequest;
    }

    @JsonProperty("GetAMLPurposeCodesRequest")
    public void setGetAMLPurposeCodesRequestBackend(GetAMLPurposeCodesRequest getAMLPurposeCodesRequestBackend) {
        this.getAMLPurposeCodesRequest = getAMLPurposeCodesRequestBackend;
    }

	public GetAMLPurposeCodesRequestBackendType() {
		super();
	}

	public GetAMLPurposeCodesRequestBackendType(GetAMLPurposeCodesRequest getAMLPurposeCodesRequestBackend) {
		super();
		this.getAMLPurposeCodesRequest = getAMLPurposeCodesRequestBackend;
	}

	@Override
	public String toString() {
		return "GetAMLPurposeCodesRequestBackend [getAMLPurposeCodesRequestBackend=" + getAMLPurposeCodesRequest
				+ "]";
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
		GetAMLPurposeCodesRequestBackendType other = (GetAMLPurposeCodesRequestBackendType) obj;
		return Objects.equals(getAMLPurposeCodesRequest, other.getAMLPurposeCodesRequest);
	}

}
