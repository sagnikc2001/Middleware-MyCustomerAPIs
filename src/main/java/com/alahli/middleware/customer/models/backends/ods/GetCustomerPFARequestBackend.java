
package com.alahli.middleware.customer.models.backends.ods;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GetCustomerPFARequest"
})
public class GetCustomerPFARequestBackend {

    @JsonProperty("GetCustomerPFARequest")
    private GetCustomerPFARequest getCustomerPFARequest;

    @JsonProperty("GetCustomerPFARequest")
    public GetCustomerPFARequest getGetCustomerPFARequest() {
        return getCustomerPFARequest;
    }

    @JsonProperty("GetCustomerPFARequest")
    public void setGetCustomerPFARequest(GetCustomerPFARequest getCustomerPFARequest) {
        this.getCustomerPFARequest = getCustomerPFARequest;
    }

	public GetCustomerPFARequestBackend() {
		super();
	}

	public GetCustomerPFARequestBackend(GetCustomerPFARequest getCustomerPFARequest) {
		super();
		this.getCustomerPFARequest = getCustomerPFARequest;
	}

	@Override
	public String toString() {
		return "GetCustomerPFARequestBackend [getCustomerPFARequest=" + getCustomerPFARequest + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCustomerPFARequest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetCustomerPFARequestBackend other = (GetCustomerPFARequestBackend) obj;
		return Objects.equals(getCustomerPFARequest, other.getCustomerPFARequest);
	}
}
