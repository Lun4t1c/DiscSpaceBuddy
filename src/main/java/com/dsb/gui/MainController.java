package com.dsb.gui;

import com.dsb.core.DsbScanner;
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
    protected void onUpdateButtonClick(){
        DsbScanner dsbScanner = new DsbScanner();
        TreeView<String> tree = this.tree;

        TreeItem<String> rootItem = new TreeItem<>("Discs");
        rootItem.setExpanded(true);

        for(Path disc : dsbScanner.Discs) {
            String diskLetter = disc.toString().substring(0,2);
            TreeItem<String> discTreeItem = new TreeItem<>(diskLetter);

            rootItem.getChildren().add(discTreeItem);

            for(Path path : dsbScanner.Directories) {
                if(!path.startsWith(disc.toString()))
                    continue;

                String directoryName = path.toString().substring(3);
                TreeItem<String> item = new TreeItem<>(directoryName);

                if (path.startsWith(disc.toString()))
                    discTreeItem.getChildren().add(item);
            }
        }
        tree.setRoot(rootItem);
    }
}
