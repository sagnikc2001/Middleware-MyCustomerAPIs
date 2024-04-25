
package com.alahli.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "currentBalance",
    "balanceAverageMonthly",
    "balanceAverageSixMonth",
    "investmentBalance",
    "khayratBalance",
    "PFA"
})
public class SuccessPFA {

    @JsonProperty("currentBalance")
    private Integer currentBalance;
    @JsonProperty("balanceAverageMonthly")
    private Integer balanceAverageMonthly;
    @JsonProperty("balanceAverageSixMonth")
    private Integer balanceAverageSixMonth;
    @JsonProperty("investmentBalance")
    private Integer investmentBalance;
    @JsonProperty("khayratBalance")
    private Integer khayratBalance;
    @JsonProperty("PFA")
    private Integer pFA;

    @JsonProperty("currentBalance")
    public Integer getCurrentBalance() {
        return currentBalance;
    }

    @JsonProperty("currentBalance")
    public void setCurrentBalance(Integer currentBalance) {
        this.currentBalance = currentBalance;
    }

    @JsonProperty("balanceAverageMonthly")
    public Integer getBalanceAverageMonthly() {
        return balanceAverageMonthly;
    }

    @JsonProperty("balanceAverageMonthly")
    public void setBalanceAverageMonthly(Integer balanceAverageMonthly) {
        this.balanceAverageMonthly = balanceAverageMonthly;
    }

    @JsonProperty("balanceAverageSixMonth")
    public Integer getBalanceAverageSixMonth() {
        return balanceAverageSixMonth;
    }

    @JsonProperty("balanceAverageSixMonth")
    public void setBalanceAverageSixMonth(Integer balanceAverageSixMonth) {
        this.balanceAverageSixMonth = balanceAverageSixMonth;
    }

    @JsonProperty("investmentBalance")
    public Integer getInvestmentBalance() {
        return investmentBalance;
    }

    @JsonProperty("investmentBalance")
    public void setInvestmentBalance(Integer investmentBalance) {
        this.investmentBalance = investmentBalance;
    }

    @JsonProperty("khayratBalance")
    public Integer getKhayratBalance() {
        return khayratBalance;
    }

    @JsonProperty("khayratBalance")
    public void setKhayratBalance(Integer khayratBalance) {
        this.khayratBalance = khayratBalance;
    }

    @JsonProperty("PFA")
    public Integer getPFA() {
        return pFA;
    }

    @JsonProperty("PFA")
    public void setPFA(Integer pFA) {
        this.pFA = pFA;
    }

	public SuccessPFA() {
		super();
	}

	public SuccessPFA(Integer currentBalance, Integer balanceAverageMonthly, Integer balanceAverageSixMonth,
			Integer investmentBalance, Integer khayratBalance, Integer pFA) {
		super();
		this.currentBalance = currentBalance;
		this.balanceAverageMonthly = balanceAverageMonthly;
		this.balanceAverageSixMonth = balanceAverageSixMonth;
		this.investmentBalance = investmentBalance;
		this.khayratBalance = khayratBalance;
		this.pFA = pFA;
	}

	@Override
	public String toString() {
		return "Success [currentBalance=" + currentBalance + ", balanceAverageMonthly=" + balanceAverageMonthly
				+ ", balanceAverageSixMonth=" + balanceAverageSixMonth + ", investmentBalance=" + investmentBalance
				+ ", khayratBalance=" + khayratBalance + ", pFA=" + pFA + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(balanceAverageMonthly, balanceAverageSixMonth, currentBalance, investmentBalance,
				khayratBalance, pFA);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuccessPFA other = (SuccessPFA) obj;
		return Objects.equals(balanceAverageMonthly, other.balanceAverageMonthly)
				&& Objects.equals(balanceAverageSixMonth, other.balanceAverageSixMonth)
				&& Objects.equals(currentBalance, other.currentBalance)
				&& Objects.equals(investmentBalance, other.investmentBalance)
				&& Objects.equals(khayratBalance, other.khayratBalance) && Objects.equals(pFA, other.pFA);
	}

}
