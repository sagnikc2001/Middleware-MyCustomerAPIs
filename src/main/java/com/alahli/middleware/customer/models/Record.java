
package com.alahli.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "channelId",
    "transactionType",
    "customerType",
    "category",
    "categoryDescEn",
    "categoryDescAr",
    "purposeCode",
    "purposeCodeDescEn",
    "purposeCodeDescAr",
    "secondLevelPurposeCode",
    "secondLevelPurposeCodeDescEn",
    "secondLevelPurposeCodeDescAr",
    "relationship",
    "relationshipDescEn",
    "relationshipDescAr"
})
public class Record {

    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("transactionType")
    private String transactionType;
    @JsonProperty("customerType")
    private String customerType;
    @JsonProperty("category")
    private String category;
    @JsonProperty("categoryDescEn")
    private String categoryDescEn;
    @JsonProperty("categoryDescAr")
    private String categoryDescAr;
    @JsonProperty("purposeCode")
    private String purposeCode;
    @JsonProperty("purposeCodeDescEn")
    private String purposeCodeDescEn;
    @JsonProperty("purposeCodeDescAr")
    private String purposeCodeDescAr;
    @JsonProperty("secondLevelPurposeCode")
    private String secondLevelPurposeCode;
    @JsonProperty("secondLevelPurposeCodeDescEn")
    private String secondLevelPurposeCodeDescEn;
    @JsonProperty("secondLevelPurposeCodeDescAr")
    private String secondLevelPurposeCodeDescAr;
    @JsonProperty("relationship")
    private String relationship;
    @JsonProperty("relationshipDescEn")
    private String relationshipDescEn;
    @JsonProperty("relationshipDescAr")
    private String relationshipDescAr;

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

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("categoryDescEn")
    public String getCategoryDescEn() {
        return categoryDescEn;
    }

    @JsonProperty("categoryDescEn")
    public void setCategoryDescEn(String categoryDescEn) {
        this.categoryDescEn = categoryDescEn;
    }

    @JsonProperty("categoryDescAr")
    public String getCategoryDescAr() {
        return categoryDescAr;
    }

    @JsonProperty("categoryDescAr")
    public void setCategoryDescAr(String categoryDescAr) {
        this.categoryDescAr = categoryDescAr;
    }

    @JsonProperty("purposeCode")
    public String getPurposeCode() {
        return purposeCode;
    }

    @JsonProperty("purposeCode")
    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    @JsonProperty("purposeCodeDescEn")
    public String getPurposeCodeDescEn() {
        return purposeCodeDescEn;
    }

    @JsonProperty("purposeCodeDescEn")
    public void setPurposeCodeDescEn(String purposeCodeDescEn) {
        this.purposeCodeDescEn = purposeCodeDescEn;
    }

    @JsonProperty("purposeCodeDescAr")
    public String getPurposeCodeDescAr() {
        return purposeCodeDescAr;
    }

    @JsonProperty("purposeCodeDescAr")
    public void setPurposeCodeDescAr(String purposeCodeDescAr) {
        this.purposeCodeDescAr = purposeCodeDescAr;
    }

    @JsonProperty("secondLevelPurposeCode")
    public String getSecondLevelPurposeCode() {
        return secondLevelPurposeCode;
    }

    @JsonProperty("secondLevelPurposeCode")
    public void setSecondLevelPurposeCode(String secondLevelPurposeCode) {
        this.secondLevelPurposeCode = secondLevelPurposeCode;
    }

    @JsonProperty("secondLevelPurposeCodeDescEn")
    public String getSecondLevelPurposeCodeDescEn() {
        return secondLevelPurposeCodeDescEn;
    }

    @JsonProperty("secondLevelPurposeCodeDescEn")
    public void setSecondLevelPurposeCodeDescEn(String secondLevelPurposeCodeDescEn) {
        this.secondLevelPurposeCodeDescEn = secondLevelPurposeCodeDescEn;
    }

    @JsonProperty("secondLevelPurposeCodeDescAr")
    public String getSecondLevelPurposeCodeDescAr() {
        return secondLevelPurposeCodeDescAr;
    }

    @JsonProperty("secondLevelPurposeCodeDescAr")
    public void setSecondLevelPurposeCodeDescAr(String secondLevelPurposeCodeDescAr) {
        this.secondLevelPurposeCodeDescAr = secondLevelPurposeCodeDescAr;
    }

    @JsonProperty("relationship")
    public String getRelationship() {
        return relationship;
    }

    @JsonProperty("relationship")
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @JsonProperty("relationshipDescEn")
    public String getRelationshipDescEn() {
        return relationshipDescEn;
    }

    @JsonProperty("relationshipDescEn")
    public void setRelationshipDescEn(String relationshipDescEn) {
        this.relationshipDescEn = relationshipDescEn;
    }

    @JsonProperty("relationshipDescAr")
    public String getRelationshipDescAr() {
        return relationshipDescAr;
    }

    @JsonProperty("relationshipDescAr")
    public void setRelationshipDescAr(String relationshipDescAr) {
        this.relationshipDescAr = relationshipDescAr;
    }

	public Record() {
		super();
	}

	public Record(String channelId, String transactionType, String customerType, String category, String categoryDescEn,
			String categoryDescAr, String purposeCode, String purposeCodeDescEn, String purposeCodeDescAr,
			String secondLevelPurposeCode, String secondLevelPurposeCodeDescEn, String secondLevelPurposeCodeDescAr,
			String relationship, String relationshipDescEn, String relationshipDescAr) {
		super();
		this.channelId = channelId;
		this.transactionType = transactionType;
		this.customerType = customerType;
		this.category = category;
		this.categoryDescEn = categoryDescEn;
		this.categoryDescAr = categoryDescAr;
		this.purposeCode = purposeCode;
		this.purposeCodeDescEn = purposeCodeDescEn;
		this.purposeCodeDescAr = purposeCodeDescAr;
		this.secondLevelPurposeCode = secondLevelPurposeCode;
		this.secondLevelPurposeCodeDescEn = secondLevelPurposeCodeDescEn;
		this.secondLevelPurposeCodeDescAr = secondLevelPurposeCodeDescAr;
		this.relationship = relationship;
		this.relationshipDescEn = relationshipDescEn;
		this.relationshipDescAr = relationshipDescAr;
	}

	@Override
	public String toString() {
		return "Record [channelId=" + channelId + ", transactionType=" + transactionType + ", customerType="
				+ customerType + ", category=" + category + ", categoryDescEn=" + categoryDescEn + ", categoryDescAr="
				+ categoryDescAr + ", purposeCode=" + purposeCode + ", purposeCodeDescEn=" + purposeCodeDescEn
				+ ", purposeCodeDescAr=" + purposeCodeDescAr + ", secondLevelPurposeCode=" + secondLevelPurposeCode
				+ ", secondLevelPurposeCodeDescEn=" + secondLevelPurposeCodeDescEn + ", secondLevelPurposeCodeDescAr="
				+ secondLevelPurposeCodeDescAr + ", relationship=" + relationship + ", relationshipDescEn="
				+ relationshipDescEn + ", relationshipDescAr=" + relationshipDescAr + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, categoryDescAr, categoryDescEn, channelId, customerType, purposeCode,
				purposeCodeDescAr, purposeCodeDescEn, relationship, relationshipDescAr, relationshipDescEn,
				secondLevelPurposeCode, secondLevelPurposeCodeDescAr, secondLevelPurposeCodeDescEn, transactionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		return Objects.equals(category, other.category) && Objects.equals(categoryDescAr, other.categoryDescAr)
				&& Objects.equals(categoryDescEn, other.categoryDescEn) && Objects.equals(channelId, other.channelId)
				&& Objects.equals(customerType, other.customerType) && Objects.equals(purposeCode, other.purposeCode)
				&& Objects.equals(purposeCodeDescAr, other.purposeCodeDescAr)
				&& Objects.equals(purposeCodeDescEn, other.purposeCodeDescEn)
				&& Objects.equals(relationship, other.relationship)
				&& Objects.equals(relationshipDescAr, other.relationshipDescAr)
				&& Objects.equals(relationshipDescEn, other.relationshipDescEn)
				&& Objects.equals(secondLevelPurposeCode, other.secondLevelPurposeCode)
				&& Objects.equals(secondLevelPurposeCodeDescAr, other.secondLevelPurposeCodeDescAr)
				&& Objects.equals(secondLevelPurposeCodeDescEn, other.secondLevelPurposeCodeDescEn)
				&& Objects.equals(transactionType, other.transactionType);
	}
}
