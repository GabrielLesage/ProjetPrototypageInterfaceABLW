package ablw.projetprototypageinterfaceablw;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.util.List;
import java.util.Vector;

public class PaneSection {

    private String titre;
    private int nb_element;

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

    public PaneSection(){}

    public PaneSection(@NamedArg("titre") String titre, @NamedArg("nb_element") int nb_element)
    {
        this.titre=titre;
        this.nb_element=nb_element;
    }

    private VBox generateControl()
    {
//        List<String,String> sejour = new List<String,String>();
        VBox separateur = new VBox();
        separateur.setAlignment(Pos.TOP_CENTER);
        separateur.setPrefHeight(200);
        separateur.setPrefWidth(100);

        Label titreSection =  new Label();
        titreSection.setAlignment(Pos.TOP_CENTER);
        titreSection.setText(titre);
        titreSection.setTextAlignment(TextAlignment.CENTER);

        HBox section = new HBox();
        section.setAlignment(Pos.TOP_CENTER);
        section.setPrefHeight(100);
        section.setPrefWidth(200);
        separateur.getChildren().addAll(titreSection,section);

        for (int i = 0; i < nb_element; i++) {
            Button button = new Button();
            section.setMargin(button,new Insets(0,5,0,0));
            button.setMnemonicParsing(false);

            ImageView imageView = new ImageView();
            imageView.setId(titre+" "+String.valueOf(i));
            imageView.setFitHeight(150);
            imageView.setFitWidth(200);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);
//            imageView.setImage(); // need list of image

            button.setGraphic(imageView);
            section.getChildren().add(button);

        }

        return separateur;

    }

    public VBox getControl()
    {
        return generateControl();
    }

}
