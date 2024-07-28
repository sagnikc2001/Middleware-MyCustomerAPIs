
package com.alahli.middleware.customer.models.backends.ods;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CustomerInformationRequest"
})
public class CustomerInformationRequestBackend {

    @JsonProperty("CustomerInformationRequest")
    private CustomerInformationRequest customerInformationRequest;

    @JsonProperty("CustomerInformationRequest")
    public CustomerInformationRequest getCustomerInformationRequest() {
        return customerInformationRequest;
    }

    @JsonProperty("CustomerInformationRequest")
    public void setCustomerInformationRequest(CustomerInformationRequest customerInformationRequest) {
        this.customerInformationRequest = customerInformationRequest;
    }

	public CustomerInformationRequestBackend() {
		super();
	}

	public CustomerInformationRequestBackend(CustomerInformationRequest customerInformationRequest) {
		super();
		this.customerInformationRequest = customerInformationRequest;
	}

	@Override
	public String toString() {
		return "CustomerInformationRequestBackend [customerInformationRequest=" + customerInformationRequest + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerInformationRequest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerInformationRequestBackend other = (CustomerInformationRequestBackend) obj;
		return Objects.equals(customerInformationRequest, other.customerInformationRequest);
	}

}
