package com.toehisa.calculator.presentation;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;

public class CalcApplication extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("calculator-view.fxml"));
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = adaptiveScene(fxmlLoader, visualBounds.getWidth(), visualBounds.getHeight(), 4);

        stage.setResizable(false);
        stage.setTitle("calculator");
        stage.setScene(scene);

        stage.show();

    }

    private Scene adaptiveScene(FXMLLoader loader, double width, double height, int multiplier) {
        try {
            if (width < height) {
                return new Scene(loader.load(), width / multiplier, height / multiplier);
            } else {
                return new Scene(loader.load(), height / multiplier, width / multiplier);
            }
        } catch (IOException e) {
            System.err.println("adaptiveSceneErr:" + e);
        }
        return null;
    }



}
