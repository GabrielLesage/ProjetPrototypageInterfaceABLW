package ablw.projetprototypageinterfaceablw;

import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class HelloController implements Initializable {

    //class to contain objet of data for a ligne
    protected static class CustomPanel {
        protected String titre;
        protected int nb_element;

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
        }

        public int getNb_element() {
            return nb_element;
        }

        public void setNb_element(int nb_element) {
            this.nb_element = nb_element;
        }

        public CustomPanel(String titre, int nb_element) {
            super();
            this.titre = titre;
            this.nb_element = nb_element;
        }
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    ListView<CustomPanel> myListView;
    Vector<CustomPanel> customPanel;

    @FXML
    TextField duration;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.customPanel = new Vector<>();

        for (int i = 0; i < 1000; i++) { //2500
            CustomPanel paneSectionVar = new CustomPanel("test", 10);
            customPanel.add(paneSectionVar);
        }

        myListView.getItems().addAll(customPanel);

        myListView.setCellFactory(new Callback<ListView<CustomPanel>, ListCell<CustomPanel>>() {
            @Override
            public ListCell<CustomPanel> call(ListView<CustomPanel> listView) {
                return new PaneSection();
            }
        });

        duration.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    duration.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }
}
