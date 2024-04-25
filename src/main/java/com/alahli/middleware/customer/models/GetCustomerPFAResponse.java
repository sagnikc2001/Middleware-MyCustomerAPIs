
package com.alahli.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "success"
})
public class GetCustomerPFAResponse {

    @JsonProperty("success")
    private SuccessPFA success;

    @JsonProperty("success")
    public SuccessPFA getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(SuccessPFA success) {
        this.success = success;
    }

	public GetCustomerPFAResponse() {
		super();
	}

	public GetCustomerPFAResponse(SuccessPFA success) {
		super();
		this.success = success;
	}

	@Override
	public String toString() {
		return "GetCustomerPFAResponse_ [success=" + success + "]";
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
		GetCustomerPFAResponse other = (GetCustomerPFAResponse) obj;
		return Objects.equals(success, other.success);
	}

}
