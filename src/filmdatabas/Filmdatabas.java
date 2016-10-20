package filmdatabas;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Filmdatabas extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("movieDB");
        
        Scene scene = new Scene(root);      
        stage.setScene(scene);
        
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM movies";
            ResultSet data = stmt.executeQuery(sql);
            
            while(data.next()){
                String output = String.format("%s kostar %d kr.", data.getString("namn"), data.getInt("pris"));
                System.out.println(output);
            }
        } catch (SQLException ex) {
            System.out.println("DATABASE ERROR");
        }
    }
    
}
