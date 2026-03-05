package presentation;

import data.CalcModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.BiFunction;

public class CalcController implements Initializable {
    @FXML
    Label viewLabel;
    @FXML
    GridPane mathContainer;

    private final CalcModel model = new CalcModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rectangle2D vBoxBounds = Screen.getPrimary().getVisualBounds();

        int fontSize = (int) vBoxBounds.getWidth() / 100;

        viewLabel.setFont(new Font("System", fontSize * 1.8));

        setButtonFontSize(mathContainer, fontSize);
        setButtonPrefSize(mathContainer, fontSize * 2.5, fontSize * 2.5);

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

    @FXML
    public void onEqBtn() {
        model.calculate(viewLabel);
    }


    private String getButtonTextValue(ActionEvent event) {
        return ((Button) event.getSource()).textProperty().getValue();
    }

    private void go() {
        model.go(viewLabel);
    }

    private void setButtonFontSize(GridPane gridPane, double fontSize) {
        for (var i : gridPane.getChildren()) {
            if (i.getClass().equals(Button.class)) {
                ((Button) i).setFont(new Font("System", fontSize));
                ((Button) i).setFont(new Font("System", fontSize));
            }
        }
    }

    private void setButtonPrefSize(GridPane gridPane, double height, double width) {
        BiFunction<Node, String, Boolean> textEquals =  (node, str) -> (((Button)node).textProperty().getValue().equals(str));

        for (Node i : gridPane.getChildren()) {
            if (i.getClass().equals(Button.class)) {
                if (textEquals.apply(i,"=")) {
                    ((Button) i).setPrefHeight(height * 2);
                    ((Button) i).setPrefWidth(width * 1.2);
                } else if (textEquals.apply(i,"AC") || textEquals.apply(i,"del")) {
                    ((Button) i).setPrefHeight(height * 1.2);
                    ((Button) i).setPrefWidth(width * 1.2);
                } else {
                    ((Button) i).setPrefHeight(height);
                    ((Button) i).setPrefWidth(width);
                }
            }

        }
    }
}




