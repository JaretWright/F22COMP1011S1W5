package com.example.f22comp1011s1w1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SongTableViewController implements Initializable {

    @FXML
    private TableColumn<Song, String> artistColumn;

    @FXML
    private TableColumn<Song, String> genreColumn;

    @FXML
    private TableColumn<Song, Integer> lengthColumn;

    @FXML
    private TableColumn<Song, Integer> songIDColumn;

    @FXML
    private TableColumn<Song, String> songNameColumn;

    @FXML
    private TableView<Song> tableview;

    @FXML
    private void createSongScene(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "create-song-view.fxml");
    }

    @FXML
    private void viewChart(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "chart-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //configure the columns to connect with specific methods
        //in the Song class
        //The PropertyValueFactory is calling the get Methods
        //for example songID will call getSongID()
        songIDColumn.setCellValueFactory(new PropertyValueFactory<>("songID"));
        songNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

        ArrayList<Song> songs = DBUtility.getSongsFromDB();
        tableview.getItems().addAll(DBUtility.getSongsFromDB());
    }
}
