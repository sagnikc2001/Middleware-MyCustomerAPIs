
package com.alahli.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "success"
})
public class CustomerInformationResponse {

    @JsonProperty("success")
    private CustomerInfoType success;

    @JsonProperty("success")
    public CustomerInfoType getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(CustomerInfoType success) {
        this.success = success;
    }

	public CustomerInformationResponse() {
		super();
	}

	public CustomerInformationResponse(CustomerInfoType success) {
		super();
		this.success = success;
	}

	@Override
	public String toString() {
		return "CustomerInformationResponse [success=" + success + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(success);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerInformationResponse other = (CustomerInformationResponse) obj;
		return Objects.equals(success, other.success);
	}
    
}
