package fxmlexercise.completed;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cirdles.commons.util.ResourceExtractor;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("FXMLExample");

        Parent root = FXMLLoader.load(
                new ResourceExtractor(Main.class).extractResourceAsPath("frame.fxml").toUri().toURL()
        );

        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
