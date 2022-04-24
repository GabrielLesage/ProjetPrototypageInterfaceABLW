package ablw.projetprototypageinterfaceablw;

import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

public class HelloController implements Initializable {

    public static ArrayList<Sejour> listSej = new ArrayList<>();
    public static ArrayList<Sejour> resultat;

    public static Sejour actualTravel;

    static ObservableList<CustomPanel> observableList = FXCollections.observableArrayList();


    @FXML
    private TextField mc;
    @FXML
    public void search(){
        //appel de la fonction de recherche dans un autre thread s'il y a plus de 2 lettres dans la recherche.
        if(mc.getText().length()<2) {
            resultat = new ArrayList<>(listSej);//Renvoie l'ensemble des séjours en cas de recherche vide.
            return;
        }
        RechercheVoyage search = new RechercheVoyage(listSej, mc.getText());
        Task<ArrayList<Sejour>> task = search.createTask();
        new Thread(task).run();
        resultat = task.getValue();//Renvoie la liste des séjours.
        observableList.clear();
        observableList.addAll(setupRes(resultat));
        myListView.setItems(observableList);
    }
    //class to contain objet of data for a ligne
    protected static class CustomPanel {
        protected String titre;
        protected int nb_element;
        protected ArrayList<Sejour> resultat;

        public ArrayList<Sejour> getResultat() {
            return resultat;
        }

        public void setResultat(ArrayList<Sejour> resultat) {
            this.resultat = resultat;
        }

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

        public CustomPanel(String titre, int nb_element, ArrayList<Sejour> resultat) {
            super();
            this.titre = titre;
            this.nb_element = nb_element;
            this.resultat = resultat;
        }
    }

    @FXML
    private Label welcomeText;

    @FXML
    private Button search;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    ListView<CustomPanel> myListView;
    Vector<Sejour> customPanel;

    @FXML
    TextField duration;

    @FXML
    Button connection;

    static protected Stage stage;
    static protected Scene scene;
    static protected Parent root;

    static protected String connected=null;

    public void connection(ActionEvent event) throws IOException, Exception {
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

    public void toMain(ActionEvent event) throws Exception {
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





        //Création de la liste de 10 000 voyages.
        for(int i = 0; i < 500; i++){
            listSej.add(new Sejour("Cavaillon", "Gabriel", "Une jolie maison en périphérie", "Ménage, travaux intérieurs"));
            listSej.add(new Sejour("Orange", "Denis", "Maison avec piscine et jardin", "Entretien jardin, travaux intérieurs"));
            listSej.add(new Sejour("Mondragon", "Jacob", "Maison au bord d'un étang", "Entretien jardin, travaux extérieurs"));
            listSej.add(new Sejour("Cheval Blanc", "Alexis", "Une jolie maison", "Potager, récolte de fruits"));
            listSej.add(new Sejour("Montfavet", "Eddy", "Appartement dans le centre", "Couper du bois"));
            listSej.add(new Sejour("Molégès", "Sylvain", "Une jolie maison en périphérie", "Jardinage"));
            listSej.add(new Sejour("Apt", "Jérémie", "Apartement avec jacuzzi", "Conception 3D"));
            listSej.add(new Sejour("Les taillades", "Damien", "Appartement", "Travaux intérieurs"));
            listSej.add(new Sejour("Avignon", "Nathanaël", "Maison", "Travaux extérieurs"));
            listSej.add(new Sejour("Lyon", "Jean", "Maison", "Soudures"));
            listSej.add(new Sejour("Paris", "Marc", "Appartement", "Electronique"));
            listSej.add(new Sejour("Strasbourg", "Jean-Marc", "Maison", "Assistance informatique"));
            listSej.add(new Sejour("Saint Etienne", "Arthur", "Maison", "Jardinage"));
            listSej.add(new Sejour("Brest", "Alexandre", "Appartement", "Garde d'enfant"));
            listSej.add(new Sejour("Rouen", "Jacque", "Appartement", "Traveaux extérieurs"));
            listSej.add(new Sejour("Montpellier", "Charle", "Villa au bord de la mer", "Entretien jardin et piscine"));
            listSej.add(new Sejour("Saint Saturnin", "Gabriel", "Maison", "Jardinage"));
            listSej.add(new Sejour("Chateauneuf", "Justin", "Appartement", "Traveaux intérieurs, Ménage"));
            listSej.add(new Sejour("Jagonzac", "Lucile", "Maison dans un petit hameau", "Aide personne agée"));
            listSej.add(new Sejour("Le puy en Velay", "Grégory", "Maison", "Entretien jardin"));
        }

        resultat = new ArrayList<>(listSej);
        if(this.myListView !=null)
        {
            //this.myListView = new ListView<Sejour>();
            this.customPanel = new Vector<>();
            if(myListView != null)
                myListView.getItems().addAll(setupRes(resultat));

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
        //Créer un nombre de panneau en fonction du nombre de séjour dans le résultat de recherche.
//        for (int i = 0; i < resultat.size()/10; i++) { //2500
//            CustomPanel paneSectionVar = new CustomPanel("test "+String.valueOf(i), 10);
//
//            customPanel.add(paneSectionVar);
//        }



    }

    public Vector<CustomPanel> setupRes(ArrayList<Sejour> res)
    {
        Vector<CustomPanel> tmp =new Vector<CustomPanel>();

        for (int i = 0; i < resultat.size()/10; i++) {
            tmp.add(new CustomPanel("test "+String.valueOf(i), 10,new ArrayList<Sejour>(res.subList(i*10, (i+1)*10))));
        }
        return tmp;
    }
}