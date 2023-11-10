package com.dsb.gui;

import com.dsb.core.DsbScanner;
import com.dsb.core.models.DirectoryModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Window;

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
                ObservableList<TreeItem<String>> rootItemChildren = rootItem.getChildren();

                rootItemChildren.add(discTreeItem);

                for (DirectoryModel directory : dsbScanner.DirectoriesList) {
                    if (directory.getPath().startsWith(disc.toString())) {
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
            String txt = "Blad mordo\n Something not right with mainController, xDdddd...";
            setUpErrorPopup(txt);
            System.err.println(txt);
            e.printStackTrace();
        }
    }

    private void setUpErrorPopup(String txt) {
        Popup popup = new Popup();
        ObservableList<Node> popupContent = popup.getContent();
        Label label = new Label(txt);
        Scene scene = tree.getScene();
        Window window = scene.getWindow();
        Paint redPaint = Paint.valueOf("Red");

        label.setStyle("-fx-background-color:white;");
        label.setTextFill(redPaint);

        popupContent.add(label);
        popup.setAutoHide(true);

        popup.show(window);
    }
}
