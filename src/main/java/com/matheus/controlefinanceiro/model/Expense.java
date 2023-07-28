package com.matheus.controlefinanceiro.model;


public class Expense {
    private Integer id;
    private String name;
    private String description;
    private float amount;

    private String date;
    public Expense() {
    }

    public Expense(String name, String description, float amount, String date) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Expense(Integer id, String name, String description, float amount, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
