package com.dsb.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class MainController {
    @FXML
    public Button updateTreeButton;
    @FXML
    public TreeView<String> tree;

    @FXML
    protected void onUpdateButtonClick(){
        TreeView<String> tree = this.tree;

        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.setExpanded(true);

        TreeItem<String> item = new TreeItem<>("ExampleItem");

        rootItem.getChildren().add(item);
        tree.setRoot(rootItem);
    }
}
