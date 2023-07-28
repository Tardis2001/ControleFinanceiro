package com.matheus.controlefinanceiro.dao;

import com.matheus.controlefinanceiro.factory.ConnectionFactory;
import com.matheus.controlefinanceiro.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
public class ExpenseDAOImpl implements ExpenseDAO{
    Connection conn = null;
    @Override
    public void createTableIfNotExists() throws SQLException {
        conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS expenses (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "description TEXT," +
                        "amount REAL,"+
                        "date TEXT);");
        stmt.execute();
    }

    @Override
    public void addExpense(Expense expense) throws SQLException {
        conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO expenses (name, description, amount,date) VALUES (?, ?, ?,?)");
        stmt.setString(1, expense.getName());
        stmt.setString(2, expense.getDescription());
        stmt.setDouble(3, expense.getAmount());
        stmt.setString(4, expense.getDate());
        stmt.executeUpdate();
    }
    @Override
    public void updateExpense(Expense expense) throws SQLException {
        conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE expenses SET name=?, description=?, amount=? WHERE id=?");
            stmt.setString(1, expense.getName());
            stmt.setString(2, expense.getDescription());
            stmt.setDouble(3, expense.getAmount());
            stmt.setInt(4, expense.getId());

            stmt.executeUpdate();
    }
    @Override
    public void deleteExpense(int id) throws SQLException {
        conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM expenses WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
    @Override
    public List<Expense> getAllExpenses() throws SQLException {
        conn = ConnectionFactory.getConnection();
        List<Expense> expensesList = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM expenses");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

                    Expense expense = new Expense(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getFloat("amount"),
                            rs.getString("date"));
                    expensesList.add(expense);
        }
        return expensesList;
    }

    @Override
    public Expense getExpense(int id) throws SQLException {
        conn = ConnectionFactory.getConnection();
        Expense expense = new Expense();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM expenses WHERE id = ?");
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        expense.setId(rs.getInt("id"));
        expense.setName(rs.getString("name"));
        expense.setDescription(rs.getString("description"));
        expense.setAmount(rs.getFloat("amount"));
        expense.setDate(rs.getString("date"));
        return expense;
    }
}