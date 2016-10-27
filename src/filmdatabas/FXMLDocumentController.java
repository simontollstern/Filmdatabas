package filmdatabas;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Movie> tableID;

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

    ObservableList<Movie> data;
    
    public String selectedMovieTitle;
    public String selectedMovieDescription;

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

            data = FXCollections.observableArrayList();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                data.add(new Movie(
                        rs.getString(1),
                        rs.getString(4),
                        rs.getString(3),
                        rs.getInt(5),
                        rs.getInt(6))
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
        tableID.setItems(null);

        tableID.setItems(data);
        
        DetailBox display = new DetailBox();

        tableID.setOnMousePressed((event) -> {
            parentPane.getChildren().remove(display);
            
            Movie selectedMovie = tableID.getSelectionModel().getSelectedItem();
            selectedMovieTitle = selectedMovie.getTitle();
            
            parentPane.getChildren().add(display);
        });

        Form inputForm = new Form();

        bgBtn.setOnAction((event) -> {
            parentPane.getChildren().add(inputForm);
        });
    }
}
