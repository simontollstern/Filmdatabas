package filmdatabas;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class FXMLDocumentController implements Initializable {

    @FXML
    Pane parentPane;
    
    @FXML
    Button bgBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM movies";
            ResultSet db = stmt.executeQuery(sql);

            while (db.next()) {
                String output = String.format("%s", db.getString("name"));
                System.out.println(output);
            }
        } catch (SQLException ex) {
            System.out.println("DATABASE ERROR");
        }
        
        Form inputForm = new Form();

        bgBtn.setOnAction((event) -> {
            parentPane.getChildren().add(inputForm);
        });
    }
}
