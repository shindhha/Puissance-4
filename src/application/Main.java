package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try { 
            FXMLLoader fxloader = new FXMLLoader(Main.class.getResource("view/Home.fxml"));
            Parent root = fxloader.load();
            Scene scene = new Scene(root);
            Controlleur c = fxloader.getController();
            c.init(primaryStage,-1);
            scene.getStylesheets().setAll(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}