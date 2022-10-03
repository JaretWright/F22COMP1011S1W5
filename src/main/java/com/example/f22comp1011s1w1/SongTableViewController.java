package com.example.f22comp1011s1w1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

public class SongTableViewController {

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
    void createSongScene(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "create-song-view.fxml");
    }

}
