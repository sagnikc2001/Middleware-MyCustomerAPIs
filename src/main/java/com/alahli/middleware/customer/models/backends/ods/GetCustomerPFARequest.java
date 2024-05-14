
package com.alahli.middleware.customer.models.backends.ods;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "shortCIF"
})
public class GetCustomerPFARequest {

    @JsonProperty("shortCIF")
    private String shortCIF;

    @JsonProperty("shortCIF")
    public String getShortCIF() {
        return shortCIF;
    }

    @JsonProperty("shortCIF")
    public void setShortCIF(String shortCIF) {
        this.shortCIF = shortCIF;
    }

	public GetCustomerPFARequest() {
		super();
	}

	public GetCustomerPFARequest(String shortCIF) {
		super();
		this.shortCIF = shortCIF;
	}

	@Override
	public String toString() {
		return "GetCustomerPFARequest [shortCIF=" + shortCIF + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(shortCIF);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetCustomerPFARequest other = (GetCustomerPFARequest) obj;
		return Objects.equals(shortCIF, other.shortCIF);
	}
}
