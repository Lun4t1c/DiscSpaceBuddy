package com.dsb.gui;

import com.dsb.core.DsbScanner;
import com.dsb.core.models.DirectoryModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class MainController {
    @FXML
    public Button updateTreeButton;
    @FXML
    public TreeView<String> tree;

    @FXML
    protected void onUpdateButtonClick() {
        try {
            DsbScanner dsbScanner = new DsbScanner();
            CompletableFuture<Void> scanFuture = dsbScanner.performFullScan();
            TreeItem<String> rootItem = new TreeItem<>("Discs");

            rootItem.setExpanded(true);
            scanFuture.get();

            for (Path disc : dsbScanner.DiscsList) {
                String diskLetter = disc.toString().substring(0, 2);
                TreeItem<String> discTreeItem = new TreeItem<>(diskLetter);

                rootItem.getChildren().add(discTreeItem);

                for (DirectoryModel directory : dsbScanner.DirectoriesList) {
                    boolean s = directory.getPath().startsWith(disc.toString());

                    if (s) {

                        Path path = directory.getPath();
                        String directoryName = path.toString().substring(3);
                        TreeItem<String> item = new TreeItem<>(directoryName);

                        if (!directoryName.isEmpty())
                            discTreeItem.getChildren().add(item);
                    }
                }
            }
            tree.setRoot(rootItem);
        } catch (Exception e) {
            // TODO Notify user about error in GUI
            System.err.println("Blad mordo");
            System.err.println("Something not right with mainController, xDdddd...");
            setupErrorPopup("Blad mordo");
            setupErrorPopup("Something not right with mainController, xDdddd...");
            e.printStackTrace();
        }
    }

    private void setupErrorPopup(String txt) {
        Popup popup = new Popup();
        Label label = new Label("Something not right with mainController, xDdddd...");
        label.setStyle("-fx-background-color:white;");
        label.setTextFill(Paint.valueOf("Red"));
        popup.getContent().add(label);
        popup.show(tree.getScene().getWindow());
        popup.setAutoHide(true);
    }
}
