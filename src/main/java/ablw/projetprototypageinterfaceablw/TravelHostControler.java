package ablw.projetprototypageinterfaceablw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ablw.projetprototypageinterfaceablw.HelloController.*;

public class TravelHostControler {

    public static Sejour actualTravel;
    public static int index;

    @FXML
    TextField desciption;

    @FXML
    TextField tache;
    @FXML
    TextField destination;

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
            System.out.println("index "+index);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse("25-04-2022", formatter);
            this.dateBegin.setValue(localDate);
            localDate = LocalDate.parse("14-06-2022", formatter);
            this.dateEnd.setValue(localDate);
            this.desciption.setText(actualTravel.description);
            this.tache.setText(actualTravel.tache);
            this.destination.setText(actualTravel.titre);

        }
        else
            System.out.println("errrer");


    }

    public void edit(ActionEvent event) throws Exception {
        Sejour sejour = listSej.get(index);
        sejour.setTitre(this.destination.getText());
        sejour.setDescription(this.desciption.getText());
        sejour.setTache(this.tache.getText());

        toMain(event);
    }

}
