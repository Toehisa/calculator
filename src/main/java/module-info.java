module com.toehisa.calculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires javafx.graphics;

    opens com.toehisa.calculator to javafx.fxml;
    exports com.toehisa.calculator;
    exports com.toehisa.calculator.presentation;
    opens com.toehisa.calculator.presentation to javafx.fxml;
}