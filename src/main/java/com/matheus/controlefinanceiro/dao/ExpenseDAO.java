package com.matheus.controlefinanceiro.dao;

import com.matheus.controlefinanceiro.model.Expense;

import java.sql.SQLException;
import java.util.List;

public interface ExpenseDAO {
    void createTableIfNotExists() throws SQLException;
    void addExpense(Expense expense) throws SQLException;
    void updateExpense(Expense expense) throws SQLException;
    void deleteExpense(int id) throws SQLException;
    List<Expense> getAllExpenses() throws SQLException;
    Expense getExpense(int id) throws SQLException;
}
