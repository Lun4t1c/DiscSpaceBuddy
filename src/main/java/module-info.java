module com.dsb.discspacebuddy.gui {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.dsb.discspacebuddy.gui;
    opens com.dsb.discspacebuddy.gui to javafx.fxml;
}