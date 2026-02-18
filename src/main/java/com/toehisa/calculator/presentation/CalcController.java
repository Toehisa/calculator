package com.toehisa.calculator.presentation;

import com.toehisa.calculator.data.CalcModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class CalcController implements Initializable {
    @FXML
    Label viewLabel;
    @FXML
    GridPane mathContainer;

    private final CalcModel model = new CalcModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rectangle2D vBoxBounds = Screen.getPrimary().getVisualBounds();

        int fontSize = (int)vBoxBounds.getWidth() / 100;

        viewLabel.setFont(new Font("System", fontSize * 2));
        setButtonFontSize(mathContainer, fontSize);

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
        model.clear(viewLabel);
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

    private void setButtonFontSize(GridPane gridPane, double fontSize) {
        for ( var i : gridPane.getChildren()) {
            if ( i.getClass().equals(Button.class) ) {
                ((Button) i).setFont(new Font("System", fontSize));
            }
        }
    }

}
