module com.dsb.discspacebuddy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dsb.discspacebuddy to javafx.fxml;
    exports com.dsb.discspacebuddy;
}