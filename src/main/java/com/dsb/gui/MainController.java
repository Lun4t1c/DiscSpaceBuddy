package com.dsb.gui;

import com.dsb.core.DsbScanner;

import java.util.concurrent.CompletableFuture;

import com.dsb.core.models.DirectoryModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.nio.file.Path;

public class MainController {
    @FXML
    public Button updateTreeButton;
    @FXML
    public TreeView<String> tree;

    @FXML
    protected void onUpdateButtonClick() {
        DsbScanner dsbScanner = new DsbScanner();
        try {
            CompletableFuture<Void> scanFuture = dsbScanner.performFullScan();
            scanFuture.get();

            TreeView<String> tree = this.tree;

            TreeItem<String> rootItem = new TreeItem<>("Discs");
            rootItem.setExpanded(true);

            for (Path disc : dsbScanner.DiscsList) {
                String diskLetter = disc.toString().substring(0, 2);
                TreeItem<String> discTreeItem = new TreeItem<>(diskLetter);

                rootItem.getChildren().add(discTreeItem);

                for (DirectoryModel directory : dsbScanner.DirectoriesList) {
                    if (!directory.getPath().startsWith(disc.toString()))
                        continue;

                    String directoryName = directory.getPath().toString().substring(3);
                    TreeItem<String> item = new TreeItem<>(directoryName);

                    if (directory.getPath().startsWith(disc.toString()))
                        discTreeItem.getChildren().add(item);
                }
            }
            tree.setRoot(rootItem);
        } catch (Exception e) {
            // TODO Notify user about error in GUI
            System.err.println("Blad mordo");
            System.err.println("Something not right with mainController, xDdddd...");
            e.printStackTrace();
        }
    }
}
