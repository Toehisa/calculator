package com.toehisa.calculator.presentation;

import com.toehisa.calculator.data.CalcModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CalcController implements Initializable {
    static double vBoxHeight = 512;
    static double vBoxWidth = 288;

    @FXML
    BorderPane viewContainer;
    @FXML
    GridPane mathContainer;
    @FXML
    Label viewLabel;

    private final CalcModel model = new CalcModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewContainer.prefHeightProperty().set(vBoxHeight * 4 / 10);
        viewContainer.prefWidthProperty().set(vBoxWidth);

        mathContainer.prefHeightProperty().set(vBoxHeight * 6 / 10);
        mathContainer.prefWidthProperty().set(vBoxWidth);
    }

    @FXML
    public void onDigitBtn(ActionEvent event) {
        if (model.keepDigitAfterZero()) {
            model.add(getButtonTextValue(event));
            go();
        }

    }

    @FXML
    public void onOperationBtn(ActionEvent event) {
        if (model.lastIsDigit()) {
            model.add(getButtonTextValue(event));
        } else {
            model.replaceLast(getButtonTextValue(event));
        }
        go();
    }

    @FXML
    public void onDotBtn(ActionEvent event) {
        if (model.dotInSequence()) {
            if (model.lastIsDigit()) {
                model.add(getButtonTextValue(event));
                go();
            } else {
                model.replaceLast(getButtonTextValue(event));
                go();
            }
        }
    }

    @FXML
    public void onAcBtn() {
        clear();
    }

    @FXML
    public void onDelBtn() {
        model.delLast(viewLabel);
    }


    private String getButtonTextValue(ActionEvent event) {
        return ((Button) event.getSource()).textProperty().getValue();
    }

    private void go() {
        model.go(viewLabel);
    }

    private void clear() {
        model.clear();
        viewLabel.setText("");
    }

}
