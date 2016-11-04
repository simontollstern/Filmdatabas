package filmdatabas;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainFrameController implements Initializable {

    @FXML
    private TableView<Movie> table;

    @FXML
    private TableColumn<Movie, String> title;
    @FXML
    private TableColumn<Movie, String> genre;
    @FXML
    private TableColumn<Movie, String> director;
    @FXML
    private TableColumn<Movie, Integer> rating;
    @FXML
    private TableColumn<Movie, Integer> imdb_rating;

    ObservableList<Movie> data = FXCollections.observableArrayList();

    @FXML
    Button addBtn;

    @FXML
    Button deleteBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM movies";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                data.add(new Movie(
                        rs.getString(1),
                        rs.getString(3),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getString(4))
                );
            }
        } catch (SQLException ex) {
            System.out.println("DATABASE ERROR");
        }

        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        director.setCellValueFactory(new PropertyValueFactory<>("director"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        imdb_rating.setCellValueFactory(new PropertyValueFactory<>("imdb_rating"));

        addBtn.setOnAction((event) -> {
            try {
                System.out.println("FUCKKK");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        deleteBtn.setOnAction((event) -> {
            Movie movie = table.getSelectionModel().getSelectedItem();
            System.out.println(movie.getTitle());
            table.getItems().remove(movie);

            try {
                Connection conn = ConnectionFactory.getConnection();
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM movies WHERE name='" + movie.getTitle() + "'";

                stmt.executeUpdate(sql);

            } catch (SQLException ex) {
                System.out.println("DATABASE ERROR");
            }
        });

        table.setItems(null);
        table.setItems(data);
    }
}
