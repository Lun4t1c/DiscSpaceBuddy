package com.dsb.gui;

import com.dsb.core.utils.StartingArgsContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DsbGUIAppMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DsbGUIAppMain.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("DiscSpaceBuddy");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(StartingArgsContext startingArgs) {
        launch();
    }
}