
package com.alahli.middleware.customer.models.backends.ods;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"searchOption",
    "cif",
    "phinenoPhoneNumber",
    "branchNumber",
    "identificationNumber",
    "accountNumber",
    "firstName",
    "lastName",
    "emailAddress"
})
public class CustomerInformationRequest {

    @JsonProperty("searchOption")
    private String searchOption;
    
    @JsonProperty("cif")
    private String cif;
    
    @JsonProperty("phinenoPhoneNumber")
    private String phinenoPhoneNumber;
    
    @JsonProperty("branchNumber")
    private String branchNumber;
    
    @JsonProperty("identificationNumber")
    private Integer identificationNumber;
    
    @JsonProperty("accountNumber")
    private String accountNumber;
    
    @JsonProperty("firstName")
    private String firstName;
    
    @JsonProperty("lastName")
    private String lastName;
    
    @JsonProperty("emailAddress")
    private String emailAddress;

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getPhinenoPhoneNumber() {
		return phinenoPhoneNumber;
	}

	public void setPhinenoPhoneNumber(String phinenoPhoneNumber) {
		this.phinenoPhoneNumber = phinenoPhoneNumber;
	}

	public String getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public Integer getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(Integer identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public CustomerInformationRequest() {
		super();
	}

	public CustomerInformationRequest(String searchOption, String cif, String phinenoPhoneNumber, String branchNumber,
			Integer identificationNumber, String accountNumber, String firstName, String lastName,
			String emailAddress) {
		super();
		this.searchOption = searchOption;
		this.cif = cif;
		this.phinenoPhoneNumber = phinenoPhoneNumber;
		this.branchNumber = branchNumber;
		this.identificationNumber = identificationNumber;
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "CustomerInformationRequest [searchOption=" + searchOption + ", cif=" + cif + ", phinenoPhoneNumber="
				+ phinenoPhoneNumber + ", branchNumber=" + branchNumber + ", identificationNumber="
				+ identificationNumber + ", accountNumber=" + accountNumber + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailAddress=" + emailAddress + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, branchNumber, cif, emailAddress, firstName, identificationNumber, lastName,
				phinenoPhoneNumber, searchOption);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerInformationRequest other = (CustomerInformationRequest) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(branchNumber, other.branchNumber)
				&& Objects.equals(cif, other.cif) && Objects.equals(emailAddress, other.emailAddress)
				&& Objects.equals(firstName, other.firstName)
				&& Objects.equals(identificationNumber, other.identificationNumber)
				&& Objects.equals(lastName, other.lastName)
				&& Objects.equals(phinenoPhoneNumber, other.phinenoPhoneNumber)
				&& Objects.equals(searchOption, other.searchOption);
	}

}
