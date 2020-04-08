package com.foody.swiggy.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "budget")
@Access(value = AccessType.PROPERTY)
public class Budget {

    private long budgetId;

    private String name;

    private BigDecimal goalAmount;

    private String period;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE , generator = "table_key_generator")
    @TableGenerator(name = "table_key_generator",table = "ifinances_keys",pkColumnName = "PK_NAME",valueColumnName = "PK_VALUE")
    @Column(name="BUDGET_ID")
    public long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(long budgetId) {
        this.budgetId = budgetId;
    }

    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name="GOAL_AMOUNT")
    public BigDecimal getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(BigDecimal goalAmount) {
        this.goalAmount = goalAmount;
    }
    @Column(name="PERIOD")
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
