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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONException;

public class MainFrameController implements Initializable {

    //<editor-fold desc="CREATE OBSERVABLELISTS">
    ObservableList<Movie> data = FXCollections.observableArrayList();
    ObservableList<String> ratings1 = FXCollections.observableArrayList("0,", "1,", "2,", "3,", "4,", "5,", "6,", "7,", "8,", "9,", "10,");
    ObservableList<String> ratings2 = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    //</editor-fold>
    
    //<editor-fold desc="CREATE FXML OBJECTS">
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

    @FXML
    Button addBtn;

    @FXML
    private TextField titleField;
    @FXML
    private TextField genreField;
    @FXML
    private TextField directorField;
    @FXML
    private ChoiceBox ratingBox1;
    @FXML
    private ChoiceBox ratingBox2;
    @FXML
    private TextField imdbTitleField;
    @FXML
    private ChoiceBox imdbRatingBox1;
    @FXML
    private ChoiceBox imdbRatingBox2;

    @FXML
    Button deleteBtn;
    //</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //<editor-fold desc="SET VALUES AND ITEMS OF RATINGBOXES">
        ratingBox1.setValue("0,");
        ratingBox1.setItems(ratings1);
        ratingBox2.setValue("0");
        ratingBox2.setItems(ratings2);
        imdbRatingBox1.setValue("0,");
        imdbRatingBox1.setItems(ratings1);
        imdbRatingBox2.setValue("0");
        imdbRatingBox2.setItems(ratings2);
        //</editor-fold>

        //<editor-fold desc="GET MOVIES FROM DB AND ADD TO LIST">
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
        //</editor-fold>

        //<editor-fold desc="SOMETHING REQUIRED FOR TABLE TO WORK">
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        director.setCellValueFactory(new PropertyValueFactory<>("director"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        imdb_rating.setCellValueFactory(new PropertyValueFactory<>("imdb_rating"));
        //</editor-fold>

        //EVENT FOR "Lägg till film" BUTTON
        addBtn.setOnAction((event) -> {
            try {
                //ADDS MOVIE MANUALLY IF IMDB FIELD IS EMPTY
                if (imdbTitleField.getLength() == 0) {
                    
                    //<editor-fold desc="CREATE STRINGS FROM TEXT FIELDS AND GET VALUES FROM CHOICE BOXES">
                    String title = titleField.getText();
                    String genre = genreField.getText();
                    String director = directorField.getText();
                    String rating1 = ratingBox1.getValue().toString();
                    String rating2 = ratingBox2.getValue().toString();
                    String rating = rating1 + rating2;
                    //</editor-fold>

                    //<editor-fold desc="ADD MANUAL MOVIE TO LIST">
                    data.add(new Movie(
                            title,
                            genre,
                            director,
                            rating,
                            "-")
                    );
                    //</editor-fold>

                    try {
                        
                        //<editor-fold desc="INSERT ADDED MOVIE INTO DB">
                        Connection conn = ConnectionFactory.getConnection();
                        Statement stmt = conn.createStatement();
                        String sql = "INSERT INTO movies (name, director, genre, rating_imdb, rating_personal) "
                                + "VALUES ('" + title + "','" + director + "','" + genre + "','-','" + rating + "')";

                        stmt.executeUpdate(sql);
                        //</editor-fold>

                    } catch (SQLException ex) {
                        System.out.println("DATABASE ERROR");
                    }

                    //<editor-fold desc="CLEAR TEXT FIELDS">
                    titleField.clear();
                    genreField.clear();
                    directorField.clear();
                    ratingBox1.setValue("0,");
                    ratingBox2.setValue("0");
                    //</editor-fold>
                  
                //ADDS MOVIE FROM IMDB (IGNORES OTHER FIELDS)    
                } else {
                    try {
                        
                        //<editor-fold desc="CREATE STRINGS WITH DATA FROM OMDB API AND GET VALUE FROM CHOICE BOXES">
                        String title = GetInfo(imdbTitleField.getText(), "Title");
                        String genre = GetInfo(imdbTitleField.getText(), "Genre");
                        String director = GetInfo(imdbTitleField.getText(), "Director");
                        String rating1 = imdbRatingBox1.getValue().toString();
                        String rating2 = imdbRatingBox2.getValue().toString();
                        String rating = rating1 + rating2;
                        String imdbRating = GetInfo(imdbTitleField.getText(), "imdbRating");
                        //</editor-fold>

                        //<editor-fold desc="ADD IMDB MOVIE TO LIST">
                        data.add(new Movie(
                                title,
                                genre,
                                director,
                                rating,
                                imdbRating)
                        );
                        //</editor-fold>

                        try {
                            
                            //<editor-fold desc="INSERT ADDED MOVIE INTO DB">
                            Connection conn = ConnectionFactory.getConnection();
                            Statement stmt = conn.createStatement();
                            String sql = "INSERT INTO movies (name, director, genre, rating_imdb, rating_personal) "
                                    + "VALUES ('" + title + "','" + director + "','" + genre + "','" + imdbRating + "','" + rating + "')";

                            stmt.executeUpdate(sql);
                            //</editor-fold>

                        } catch (SQLException ex) {
                            System.out.println("DATABASE ERROR");
                        }

                        //<editor-fold desc="CLEAR TEXT FIELDS">
                        titleField.clear();
                        genreField.clear();
                        directorField.clear();
                        ratingBox1.setValue("0,");
                        ratingBox2.setValue("0");
                        imdbTitleField.clear();
                        imdbRatingBox1.setValue("0,");
                        imdbRatingBox2.setValue("0");
                        //</editor-fold>
                        
                    } catch (Exception e) {
                        System.out.println("error");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //EVENT FOR "Ta bort markerad film" BUTTON
        deleteBtn.setOnAction((event) -> {
            
            //<editor-fold desc="CREATE MOVIE ITEM FROM SELECTED TABLE ITEM AND REMOVE">
            Movie movie = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(movie);
            //</editor-fold>

            try {
                
                //<editor-fold desc="DELETE SELECTED MOVIE FROM DB">
                Connection conn = ConnectionFactory.getConnection();
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM movies WHERE name='" + movie.getTitle() + "'";

                stmt.executeUpdate(sql);
                //</editor-fold>

            } catch (SQLException ex) {
                System.out.println("DATABASE ERROR");
            }
        });

        //<editor-fold desc="CLEAR TABLE AND ADD ITEMS FROM LIST">
        table.setItems(null);
        table.setItems(data);
        //</editor-fold>
    }

    private String GetInfo(String input, String jsonData) {
        String api = "http://www.omdbapi.com/?t=";
        input = input.replaceAll(" ", "%20");
        api += input;
        String url = null;
        try {
            url = JsonReader.readJsonFromUrl(api).getString(jsonData);
        } catch (IOException | JSONException ex) {
            System.out.println(ex.getMessage());
        }
        return url;
    }
}
