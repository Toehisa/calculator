package com.toehisa.calculator.presentation;


import com.toehisa.calculator.data.SysInfoProvider.ScreenResolution;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalcApplication extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(CalcApplication.class.getResource("calculator-view.fxml"));
        ScreenResolution resInfo = new ScreenResolution();

        Scene scene = adaptiveScene(
                fxmlLoader,
                resInfo.getScreenWidth(0),
                resInfo.getScreenHeight(0)
        );

        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private <T> Scene adaptiveScene(FXMLLoader loader, double width, double height) {
        try {
            if (width < height) {
                return new Scene(loader.load(), width / 9, height / 6);
            } else {
                return new Scene(loader.load(), height / 6, width / 9);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }
}
