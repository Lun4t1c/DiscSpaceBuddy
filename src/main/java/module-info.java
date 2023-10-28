module com.dsb.discspacebuddy.gui {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.dsb.gui;
    opens com.dsb.gui to javafx.fxml;
}