package ablw.projetprototypageinterfaceablw;

import javafx.beans.NamedArg;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class PaneSection extends ListCell<HelloController.CustomPanel>  {

    public String titre;
    public int nbEllement;
    public ArrayList<Sejour> resultat;
    protected Vector<Integer> listIndex;
    protected int index;

    public Vector<Integer> getListIndex() {
        return listIndex;
    }

    public void setListIndex(Vector<Integer> listIndex) {
        this.listIndex = listIndex;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNbEllement() {
        return nbEllement;
    }

    public void setNbEllement(int nbEllement) {
        this.nbEllement = nbEllement;
    }

    public ArrayList<Sejour> getResultat() {
        return resultat;
    }

    public void setResultat(ArrayList<Sejour> resultat) {
        this.resultat = resultat;
    }

    public PaneSection(){}

    public PaneSection(String titre, int nbEllement, ArrayList<Sejour> resultat,Vector<Integer> listIndex) {
        this.titre = titre;
        this.nbEllement = nbEllement;
        this.resultat = resultat;
        this.listIndex = listIndex;
    }

    //    public PaneSection(@NamedArg("titre") String titre, @NamedArg("nb_element") int nb_element)
//    {
//        this.titre=titre;
//        this.nb_element=nb_element;
//    }

    @Override
    protected void updateItem(HelloController.CustomPanel item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            //titre.setText(item.getName());
            //price.setText(String.format("%d $", item.getPrice()));
            //setGraphic(content);
            nbEllement++;
            this.titre=item.titre;
            this.nbEllement=item.nb_element;
            this.resultat=item.resultat;
            this.listIndex=item.listIndex;
            System.out.println("nb "+nbEllement);


            setGraphic(generateControl());

        } else {
            this.titre=null;
            this.nbEllement=0;
            this.resultat=null;
            this.listIndex=null;
            setGraphic(null);
        }
    }

    public void onClickButton()
    {
        System.out.println("work");
    }

    private VBox generateControl()
    {
//        List<String,String> sejour = new List<String,String>();
        Image image = new Image(HelloApplication.class.getResourceAsStream("/img/icon.jpg"));
        VBox separateur = new VBox();
        separateur.setAlignment(Pos.TOP_CENTER);
        separateur.setPrefHeight(200);
        separateur.setPrefWidth(100);

        Label titreSection =  new Label();
        titreSection.setAlignment(Pos.TOP_CENTER);
        titreSection.setText("test");
        titreSection.setTextAlignment(TextAlignment.CENTER);
        titreSection.getStyleClass().add("section");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinHeight(Region.USE_PREF_SIZE);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);
        scrollPane.setPrefHeight(image.getHeight()+10);//adape ?
        scrollPane.setPrefWidth(200);
        scrollPane.getStyleClass().add("ligne");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox section = new HBox();
        section.setAlignment(Pos.TOP_CENTER);
        section.setPrefHeight(100);
        section.setPrefWidth(200);
        scrollPane.setContent(section);
        separateur.getChildren().addAll(titreSection,scrollPane);
        separateur.setMargin(scrollPane, new Insets(0,30,0,30));

        for (Sejour sejour: resultat) {

            StackPane stackPane = new StackPane();
            stackPane.setMinWidth(200);
            stackPane.setMinHeight(150);

            Label titre = new Label();
            titre.setText(sejour.titre);
            StackPane.setAlignment(titre, Pos.BOTTOM_CENTER);

            ImageView imageView = new ImageView();
            imageView.setId(titre+" "+String.valueOf(5));
            imageView.setFitHeight(150);
            imageView.setFitWidth(200);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);

            imageView.setImage(image);
            imageView.getStyleClass().add("travel");
            stackPane.getChildren().add(imageView);
            stackPane.getChildren().add(titre);

            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    if(HelloController.connected == "host" && sejour.hote == HelloController.username)
                    {
                        index=resultat.indexOf(sejour);
                        TravelHostControler.actualTravel = sejour;
                        System.out.println(index);
                        System.out.println(sejour.hote);
                        System.out.println(listIndex.get(index));
                        TravelHostControler.index = listIndex.get(index);
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("travelHost.fxml"));
                            HelloController.stage = (Stage)((Node)me.getSource()).getScene().getWindow();
                            HelloController.scene = new Scene(root);
                            HelloController.stage.setScene(HelloController.scene);
                            HelloController.stage.setMaximized(true);
                            HelloController.stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(HelloController.connected == "traveler" || HelloController.connected == "host")
                    {
                        TravelControler.actualTravel = sejour;
                        index=resultat.indexOf(sejour);
                        try {
                            System.out.println(index);
                            System.out.println(sejour.hote);
                            System.out.println(listIndex.get(index));
                            Parent root = FXMLLoader.load(getClass().getResource("travel.fxml"));
                            HelloController.stage = (Stage)((Node)me.getSource()).getScene().getWindow();
                            HelloController.scene = new Scene(root);
                            HelloController.stage.setScene(HelloController.scene);
                            HelloController.stage.setMaximized(true);
                            HelloController.stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });

            section.setMargin(stackPane,new Insets(0,5,0,5));
//            imageView.setImage(); // need list of image

            //button.setGraphic(imageView);
            section.getChildren().add(stackPane);
            index++;
        }

        return separateur;

    }

    public VBox getControl()
    {
        return generateControl();
    }

}