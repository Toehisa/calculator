package presentation;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class CalcApplication extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/calculator-view.fxml"));
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = adaptiveScene(fxmlLoader, visualBounds.getWidth(), visualBounds.getHeight());
        stage.getIcons().add(new Image(Objects.requireNonNull(
                CalcApplication.class.getResourceAsStream("/wallpapers/icon.png")
        )));
        stage.setResizable(false);
        stage.setTitle("");
        stage.setScene(scene);

        stage.show();
    }

    private Scene adaptiveScene(FXMLLoader loader, double width, double height) {
        try {
            if (width < height) {
                return portraitOrientaionScene(loader, width, height);
            } else {
                return albumOrientaionScene(loader, width, height);
            }
        } catch (IOException e) {
            System.err.println("FXMLLoader issue: " + e);
        }
        return null;
    }

    private Scene portraitOrientaionScene(FXMLLoader loader, double width, double height) throws IOException {
        double screenRatio = height / width;

        if (screenRatio > 2.6) {                                            //32:9
            return new Scene(loader.load(), width / 2.4, height / 5);
        } else if (screenRatio > 2.1 && screenRatio < 2.6) {                //21:9
            return new Scene(loader.load(), width / 3, height / 4);
        } else if (screenRatio > 1.76 && screenRatio < 2) {                 //16:9
            return new Scene(loader.load(), width / 4, height / 4);
        } else if (screenRatio > 1.59 && screenRatio < 1.75) {              //16:10
            return new Scene(loader.load(), width / 4, height / 3.6);
        } else if (screenRatio < 1.59) {                                    //4:3
            return new Scene(loader.load(), width / 4, height / 3);
        } else {
            return null;
        }
    }

    private Scene albumOrientaionScene(FXMLLoader loader, double width, double height) throws IOException {
        double screenRatio = width / height;

        if (screenRatio > 2.6) {                                            //32:9
            return new Scene(loader.load(), height / 2.4, width / 5);
        } else if (screenRatio > 2.1 && screenRatio < 2.6) {                //21:9
            return new Scene(loader.load(), height / 3, width / 4);
        } else if (screenRatio > 1.76 && screenRatio < 2) {                 //16:9
            return new Scene(loader.load(), height / 4, width / 4);
        } else if (screenRatio > 1.59 && screenRatio < 1.75) {              //16:10
            return new Scene(loader.load(), height / 4, width / 3.6);
        } else if (screenRatio < 1.59) {                                    //4:3
            return new Scene(loader.load(), height / 4, width / 3);
        } else {
            return null;
        }
    }

}
