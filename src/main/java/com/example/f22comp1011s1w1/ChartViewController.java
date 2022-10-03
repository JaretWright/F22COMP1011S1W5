package com.example.f22comp1011s1w1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChartViewController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    void switchToTableView(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "song-table-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String,Integer> songsByArtist = new XYChart.Series<>();
        songsByArtist.getData().add(new XYChart.Data<>("Bruno Mars",30));
        songsByArtist.getData().add(new XYChart.Data<>("Rhihana",55));
        songsByArtist.getData().add(new XYChart.Data<>("Jaret",65));
        barChart.getData().addAll(songsByArtist);
    }
}
