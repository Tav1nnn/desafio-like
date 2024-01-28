package com.otavio.desafiolike.dto;

import com.otavio.desafiolike.entity.BudgetEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class BudgetDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String clientName;
    private Date date;

    public BudgetDto() {
    }

    public BudgetDto(Long id, String clientName, Date date) {
        this.id = id;
        this.clientName = clientName;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
