package com.dsb.gui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Window;

public class ErrorPopup extends Popup{
    public ErrorPopup(Window window, String txt){
        ObservableList<Node> content = super.getContent();
        Label label = getErrorLabel(txt);
        final double[] windowRect = {window.getX(), window.getY(), window.getWidth(), window.getHeight()};

        content.add(label);
        this.setAutoHide(true);
        this.setX(windowRect[0] + 0.4f * windowRect[2]);
        this.setY(windowRect[1] + 0.85f * windowRect[3]);
        this.setAutoFix(true);
    }

    private Label getErrorLabel(String txt) {
        Label label = new Label(txt);
        String newStyle = "-fx-background-color:white;-fx-font-size:16px;";
        Paint redPaint = Paint.valueOf("Red");
        label.setStyle(newStyle);
        label.setTextFill(redPaint);
        return label;
    }
}
