package filmdatabas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Filmdatabas extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainFrame.fxml"));

        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("movieDB");

        Scene scene = new Scene(root);
        stage.setScene(scene);

        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
