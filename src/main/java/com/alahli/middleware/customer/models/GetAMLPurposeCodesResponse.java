
package com.alahli.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "success" })
public class GetAMLPurposeCodesResponse {

	@JsonProperty("success")
	private Success success;

	@JsonProperty("success")
	public Success getSuccess() {
		return success;
	}

	@JsonProperty("success")
	public void setSuccess(Success success) {
		this.success = success;
	}

	public GetAMLPurposeCodesResponse() {
		super();
	}

	public GetAMLPurposeCodesResponse(Success success) {
		super();
		this.success = success;
	}

	@Override
	public String toString() {
		return "GetAMLPurposeCodesResponseType [success=" + success + "]";
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
		GetAMLPurposeCodesResponse other = (GetAMLPurposeCodesResponse) obj;
		return Objects.equals(success, other.success);
	}

}
