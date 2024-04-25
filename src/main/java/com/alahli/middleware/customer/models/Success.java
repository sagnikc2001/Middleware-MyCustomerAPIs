
package com.alahli.middleware.customer.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "record"
})
public class Success {

    @JsonProperty("record")
    private List<Record> record = new ArrayList<Record>();

    @JsonProperty("record")
    public List<Record> getRecord() {
        return record;
    }

    @JsonProperty("record")
    public void setRecord(List<Record> record) {
        this.record = record;
    }

	public Success() {
		super();
	}

	public Success(List<Record> record) {
		super();
		this.record = record;
	}

	@Override
	public String toString() {
		return "Success [record=" + record + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(record);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Success other = (Success) obj;
		return Objects.equals(record, other.record);
	}

}
