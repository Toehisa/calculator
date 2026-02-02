package com.toehisa.calculator.presentation;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalcApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalcApplication.class.getResource("calculator-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),200, 200);

        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
