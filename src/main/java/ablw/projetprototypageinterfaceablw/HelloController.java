package ablw.projetprototypageinterfaceablw;

import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
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

    @FXML
    Button connection;

    static protected Stage stage;
    static protected Scene scene;
    static protected Parent root;

    static protected String connected=null;

    public void connection(ActionEvent event) throws IOException {
        if(this.connected == null)
        {
            Stage modal = new Stage();
            modal.setTitle("type de compte");
            modal.setWidth(400);
            modal.setWidth(300);
            modal.setX(800);
            modal.setY(400);
            modal.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("chooseAccount.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            modal.setScene(scene);
            //modal.initOwner(stage);
            connection.setText("Se déconnecter");
            modal.show();
        }
        else {
            this.connected=null;
            connection.setText("Se connecter");
            toMain(event);
        }

    }
    @FXML
    Button closeButton;

    public void connectionTraveler(){
        this.connected="traveler";

        close();
    }

    public void connectionHost(){
        this.connected="host";
        close();
    }

    private void close()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void toMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        if(connected != null && this.connection != null)
        {
            connection.setText("Se déconnecter");
        }
//        connection.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//                connectionTraveler();
//            }
//        });



        if(this.myListView !=null)
        {
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
}
