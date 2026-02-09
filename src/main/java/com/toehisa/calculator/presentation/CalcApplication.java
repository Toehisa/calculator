package com.toehisa.calculator.presentation;


import com.toehisa.calculator.data.SysInfoProvider.ScreenResolution;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class CalcApplication extends Application {
    public static  double SCREEN_WIDTH = ScreenResolution.getScreenWidth(0);
    public static  double SCREEN_HEIGHT = ScreenResolution.getScreenHeight(0);
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("calculator-view.fxml"));
        Scene scene = adaptiveScene(fxmlLoader, SCREEN_WIDTH, SCREEN_HEIGHT, 5);

        CalcController.vBoxHeight = scene.getHeight();
        CalcController.vBoxWidth = scene.getWidth();

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
