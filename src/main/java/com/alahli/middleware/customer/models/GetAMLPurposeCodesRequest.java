
package com.alahli.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "channelId",
    "transactionType",
    "customerType"
})
public class GetAMLPurposeCodesRequest {

    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("transactionType")
    private String transactionType;
    @JsonProperty("customerType")
    private String customerType;

    @JsonProperty("channelId")
    public String getChannelId() {
        return channelId;
    }

    @JsonProperty("channelId")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @JsonProperty("transactionType")
    public String getTransactionType() {
        return transactionType;
    }

    @JsonProperty("transactionType")
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @JsonProperty("customerType")
    public String getCustomerType() {
        return customerType;
    }

    @JsonProperty("customerType")
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

	public GetAMLPurposeCodesRequest() {
		super();
	}

	public GetAMLPurposeCodesRequest(String channelId, String transactionType, String customerType) {
		super();
		this.channelId = channelId;
		this.transactionType = transactionType;
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "GetAMLPurposeCodesRequestType [channelId=" + channelId + ", transactionType=" + transactionType
				+ ", customerType=" + customerType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(channelId, customerType, transactionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetAMLPurposeCodesRequest other = (GetAMLPurposeCodesRequest) obj;
		return Objects.equals(channelId, other.channelId) && Objects.equals(customerType, other.customerType)
				&& Objects.equals(transactionType, other.transactionType);
	}

    }
