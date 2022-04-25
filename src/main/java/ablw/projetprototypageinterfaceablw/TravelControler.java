package ablw.projetprototypageinterfaceablw;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

import static ablw.projetprototypageinterfaceablw.HelloController.*;

public class TravelControler {

    public static Sejour actualTravel;

    @FXML
    Label desciption;

    @FXML
    Label destination;

    @FXML
    DatePicker dateBegin;
    @FXML
    DatePicker dateEnd;

    public void toMain(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void initialize(){
        if(actualTravel != null)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse("25-04-2022", formatter);
            this.dateBegin.setValue(localDate);
            localDate = LocalDate.parse("14-06-2022", formatter);
            this.dateEnd.setValue(localDate);
            this.desciption.setText("Hôte: "+actualTravel.hote+"\n"+"description: "+actualTravel.description+"\n"+"Tache: "+actualTravel.tache);
            this.destination.setText(actualTravel.titre);

        }


    }

}
