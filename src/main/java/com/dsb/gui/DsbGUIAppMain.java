package com.dsb.gui;

import com.dsb.core.utils.StartingArgsContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class DsbGUIAppMain extends Application {
    public static void mainLoop(StartingArgsContext startingArgs) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DsbGUIAppMain.class.getResource("main-view.fxml"));

        Screen primaryScreen = Screen.getPrimary();
        Rectangle2D visualBounds = primaryScreen.getVisualBounds();

        double screenWidth = visualBounds.getWidth() - visualBounds.getMaxX();
        double screenHeight = visualBounds.getHeight() - visualBounds.getMaxY();

        Scene scene = new Scene(fxmlLoader.load(), screenWidth, screenHeight);
        stage.setTitle("DiscSpaceBuddy");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.show();
    }
}