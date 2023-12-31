package com.dsb.gui;

import com.dsb.core.utils.StartingArgsContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DsbGUIAppMain extends Application {
    public static void mainLoop(StartingArgsContext startingArgs) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DsbGUIAppMain.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("DiscSpaceBuddy");

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}