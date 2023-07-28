module com.matheus.controlefinanceiro {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.matheus.controlefinanceiro to javafx.fxml;
    exports com.matheus.controlefinanceiro;
    opens com.matheus.controlefinanceiro.model to javafx.base;
    exports com.matheus.controlefinanceiro.controller;
    opens com.matheus.controlefinanceiro.controller to javafx.fxml;
}