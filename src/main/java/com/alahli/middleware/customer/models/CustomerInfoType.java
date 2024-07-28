
package com.alahli.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "partyId",
    "partyNumber",
    "partyName"
})
public class CustomerInfoType {

    @JsonProperty("partyId")
    private String partyId;
    
    @JsonProperty("partyNumber")
    private Integer partyNumber;
    
    @JsonProperty("partyName")
    private String partyName;

    @JsonProperty("partyId")
    public String getPartyId() {
        return partyId;
    }

    @JsonProperty("partyId")
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    @JsonProperty("partyNumber")
    public Integer getPartyNumber() {
        return partyNumber;
    }

    @JsonProperty("partyNumber")
    public void setPartyNumber(Integer partyNumber) {
        this.partyNumber = partyNumber;
    }

    @JsonProperty("partyName")
    public String getPartyName() {
        return partyName;
    }

    @JsonProperty("partyName")
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

	public CustomerInfoType() {
		super();
	}

	public CustomerInfoType(String partyId, Integer partyNumber, String partyName) {
		super();
		this.partyId = partyId;
		this.partyNumber = partyNumber;
		this.partyName = partyName;
	}

	@Override
	public String toString() {
		return "CustomerInformationSuccess [partyId=" + partyId + ", partyNumber=" + partyNumber + ", partyName="
				+ partyName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(partyId, partyName, partyNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerInfoType other = (CustomerInfoType) obj;
		return Objects.equals(partyId, other.partyId) && Objects.equals(partyName, other.partyName)
				&& Objects.equals(partyNumber, other.partyNumber);
	}

}
