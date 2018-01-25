package fxmlexercise.completed;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.cirdles.commons.util.ResourceExtractor;

import java.io.IOException;

public class SelectorController extends VBox {

    //**********************************************//
    //                   CONTROLS                   //
    //**********************************************//

    @FXML private TextField topTextField, bottomTextField;

    @FXML private ColorPicker topColorPicker, bottomColorPicker;

    @FXML private Button printButton;

    //**********************************************//
    //                  PROPERTIES                  //
    //**********************************************//

    private StringProperty topColorName;
    public StringProperty topColorNameProperty() {
        if (topColorName == null) {
            topColorName = new SimpleStringProperty();
            topColorName.bind(topTextField.textProperty());
        }
        return topColorName;
    }
    public String getTopColorName() {
        return topColorNameProperty().get();
    }
    public void setTopColorName(String s) {
        topColorNameProperty().set(s);
    }

    private StringProperty bottomColorName;
    public StringProperty bottomColorNameProperty() {
        if (bottomColorName == null) {
            bottomColorName = new SimpleStringProperty();
            bottomColorName.bind(bottomTextField.textProperty());
        }
        return bottomColorName;
    }
    public String getBottomColorName() {
        return bottomColorNameProperty().get();
    }
    public void setBottomColorName(String s) {
        bottomColorNameProperty().set(s);
    }

    private ObjectProperty<Color> topColor;
    public ObjectProperty<Color> topColorProperty() {
        if (topColor == null) {
            topColor = new SimpleObjectProperty<>();
            topColor.bind(topColorPicker.valueProperty());
        }
        return topColor;
    }
    public Color getTopColor() {
        return topColorProperty().get();
    }
    public void setTopColor(Color c) {
        topColorProperty().set(c);
    }

    private ObjectProperty<Color> bottomColor;
    public ObjectProperty<Color> bottomColorProperty() {
        if (bottomColor == null) {
            bottomColor = new SimpleObjectProperty<>();
            bottomColor.bind(bottomColorPicker.valueProperty());
        }
        return bottomColor;
    }
    public Color getBottomColor() {
        return bottomColorProperty().get();
    }
    public void setBottomColor(Color c) {
        bottomColorProperty().set(c);
    }

    //**********************************************//
    //                 CONSTRUCTORS                 //
    //**********************************************//

    public SelectorController() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    new ResourceExtractor(SelectorController.class).extractResourceAsPath("selector.fxml").toUri().toURL()
            );
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //************************************************//
    //                PRIVATE METHODS                 //
    //************************************************//

    @FXML private void printButtonAction() {
        System.out.println(topTextField.getText() + ": " + topColorPicker.getValue());
        System.out.println(bottomTextField.getText() + ": " + bottomColorPicker.getValue());
    }

    //***********************************************//
    //                PUBLIC METHODS                 //
    //***********************************************//

    @FXML public void initialize() {

        /*
            The following listeners will disable the print button if either TextField is empty.
         */
        topTextField.textProperty().addListener(( (observable, oldValue, newValue) -> {
            if ( newValue.equals("") ) {
                printButton.setDisable(true);
            } else if ( ! bottomTextField.getText().equals("") ) {
                printButton.setDisable(false);
            }
        }));
        bottomTextField.textProperty().addListener(( (observable, oldValue, newValue) -> {
            if ( newValue.equals("") ) {
                printButton.setDisable(true);
            } else if ( ! topTextField.getText().equals("") ) {
                printButton.setDisable(false);
            }
        }));

        /*
            Setting the initial disable state of the print button.
        */
        printButton.setDisable(topTextField.getText().equals("") || bottomTextField.getText().equals(""));
    }
}
