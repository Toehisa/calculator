package com.toehisa.calculator.presentation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CalcController implements Initializable {
    static double vBoxHeight = 512;
    static double vBoxWidth = 288;

    @FXML
    BorderPane viewContainer;
    @FXML
    GridPane mathContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewContainer.prefHeightProperty().set(vBoxHeight * 4 / 10);
        viewContainer.prefWidthProperty().set(vBoxWidth);

        mathContainer.prefHeightProperty().set(vBoxHeight * 6 / 10);
        mathContainer.prefWidthProperty().set(vBoxWidth);

    }
}
