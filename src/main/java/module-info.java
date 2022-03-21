module ablw.projetprototypageinterfaceablw {
    requires javafx.controls;
    requires javafx.fxml;


    opens ablw.projetprototypageinterfaceablw to javafx.fxml;
    exports ablw.projetprototypageinterfaceablw;
}