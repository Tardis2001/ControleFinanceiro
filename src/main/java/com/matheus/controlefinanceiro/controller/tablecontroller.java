package com.matheus.controlefinanceiro.controller;

import com.matheus.controlefinanceiro.dao.ExpenseDAOImpl;
import com.matheus.controlefinanceiro.model.Expense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class tablecontroller {

    private ExpenseDAOImpl expenseDAO;
    @FXML
    private Button add;
    @FXML
    private TableColumn<Expense, Double> amountColumn;
    @FXML
    private TableColumn<Expense, String> descriptionColumn;
    @FXML
    private Text total;
    @FXML
    private TableColumn<Expense, Integer> idColumn;
    @FXML
    private TableColumn<Expense, String> nameColumn;
    @FXML
    private TableColumn<Expense, String> dateColumn;
    @FXML
    private Button remove;
    @FXML
    private TableView<Expense> table;
    @FXML
    private Button update;
    @FXML
    private TextField idField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField NameField;
    @FXML
    private DatePicker date;
    @FXML
    private Button search;
    private ObservableList<Expense> expensesList = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        expenseDAO = new ExpenseDAOImpl();

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Expense,String>("description"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Expense,Double>("amount"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Expense,String>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Expense,Integer>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Expense,String>("date"));

        try {
            expenseDAO.createTableIfNotExists();
            refreshTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        add.setOnAction(event ->
                addExpense()
        );
        remove.setOnAction(event ->
                removeexpense()
        );
        update.setOnAction(event ->
                updateexpense()
        );
        search.setOnAction(event ->
                searchexpense()
        );
    }



    @FXML
    private void addExpense() {
        try {
            expenseDAO.addExpense(new Expense(NameField.getText(),descriptionField.getText(),Float.parseFloat(amountField.getText()), date.getValue().toString()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (NumberFormatException | NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor preencha os campos corretamente!");
            alert.showAndWait();
        }
        refreshTable();
    }
    @FXML
    void removeexpense() {
        try{
            expenseDAO.deleteExpense(Integer.parseInt(idField.getText()));

        }catch(SQLException e){
            throw new RuntimeException(e);
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor preencha os campos corretamente!");
            alert.showAndWait();
        }
        refreshTable();
    }

    @FXML
    void updateexpense() {
        try{
            expenseDAO.updateExpense(new Expense(Integer.parseInt(idField.getText()),NameField.getText(),descriptionField.getText(),Float.parseFloat(amountField.getText()),date.getValue().toString()));

        }catch(SQLException e){
            throw new RuntimeException(e);
        }catch (NumberFormatException | NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor preencha os campos corretamente!");
            alert.showAndWait();
        }
        refreshTable();
    }
    private void searchexpense() {
        Expense expense;
        if(!idField.getText().equals("")) {
            try {
                expense = expenseDAO.getExpense(Integer.parseInt(idField.getText()));
                expensesList.clear();
                expensesList.add(expense);
                table.setItems(expensesList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Por favor preencha os campos corretamente!");
                alert.showAndWait();
            }
        }
        else{
            refreshTable();
        }
    }
    @FXML
    private void refreshTable() {
        try {
            float amount = 0;
            expensesList.clear();
            expensesList.addAll(expenseDAO.getAllExpenses());
            table.setItems(expensesList);
            for (int i = 0; i <expensesList.size(); i++) {

                amount += expensesList.get(i).getAmount();
            }
            total.setText(String.valueOf(amount));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        NameField.clear();
        descriptionField.clear();
        amountField.clear();
        idField.clear();
        date.setValue(null);
    }

}