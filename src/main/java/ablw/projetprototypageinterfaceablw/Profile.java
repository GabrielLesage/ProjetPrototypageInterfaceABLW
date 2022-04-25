package ablw.projetprototypageinterfaceablw;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Scanner;

public class Profile {
    Stage stage1 = new Stage();
    Stage stage2 = new Stage();
    Label email_profile = new Label();
    Label nom_profile = new Label();
    Label prenom_profile = new Label();
    private static final String STANDARD_BUTTON_STYLE = "-fx-padding: 0.35em 1.2em;-fx-border-color: #FFFFFF;-fx-border-width:0.1em;-fx-border-style:solid;" +
            "-fx-start-margin :0 0.3em;-fx-end-margin: 0.3em 0;-fx-border-radius: 0.12em;" +
            "-fx-font-family: Roboto, sans-serif;-fx-font-weight: 300;-fx-background-color: #FFFFFF;" +
            "-fx-text-alignment: center;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-padding: 0.35em 1.2em;-fx-border-color: #E2E2E2;-fx-border-width:0.1em;-fx-border-style:solid;" +
            "-fx-start-margin :0 0.3em;-fx-end-margin: 0.3em 0;-fx-border-radius: 0.12em;" +
            "-fx-font-family: Roboto, sans-serif;-fx-font-weight: 300;-fx-background-color: #E2E2E2;" +
            "-fx-text-alignment: center;";


    public void changeBackgroundOnHoverUsingEvents(final Node node) {
        node.setStyle(STANDARD_BUTTON_STYLE);
        node.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                node.setStyle(HOVERED_BUTTON_STYLE);
            }
        });
        node.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                node.setStyle(STANDARD_BUTTON_STYLE);
            }
        });
    }
    public Stage creerStageModifier() throws IOException {
        Line line1 = new Line(0,10,10000,500);
        Line line2 = new Line(2000,1000,1100,0);
        line2.setStroke(Color.BLUE);
        line1.setStroke(Color.BLUE);
        Stage stage_modifier = new Stage();
        Pane pane_modifier = new Pane();
        InputStream stream = new FileInputStream("src/main/resources/img/icon.jpg");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Image image_modifier = new Image(stream);
        Circle circle_modifier = new Circle(primaryScreenBounds.getWidth()/4,primaryScreenBounds.getHeight()/4,200);
        circle_modifier.centerXProperty().bind(pane_modifier.widthProperty().divide(3.5));
        circle_modifier.centerYProperty().bind(pane_modifier.heightProperty().divide(2.5));
        circle_modifier.radiusProperty().bind(pane_modifier.widthProperty().divide(7));
        TextField email_modifier = new TextField("email");
        TextField nom_modifier = new TextField("Nom");
        TextField prenom_modifier = new TextField("Prenom");
        TextField imageUrl = new TextField("Link Image");
        Button modifier_modifier = new Button("Modifier");
        changeBackgroundOnHoverUsingEvents(modifier_modifier);
        email_modifier.layoutXProperty().bind(pane_modifier.widthProperty().divide(1.75));
        email_modifier.layoutYProperty().bind(pane_modifier.heightProperty().divide(4.4));
        email_modifier.setFont(new Font("Arial",30));
        nom_modifier.layoutXProperty().bind(pane_modifier.widthProperty().divide(1.75));
        nom_modifier.layoutYProperty().bind(pane_modifier.heightProperty().divide(3.1));
        nom_modifier.setFont(new Font("Arial",30));
        prenom_modifier.layoutXProperty().bind(pane_modifier.widthProperty().divide(1.75));
        prenom_modifier.layoutYProperty().bind(pane_modifier.heightProperty().divide(2.4));
        prenom_modifier.setFont(new Font("Arial",30));
        imageUrl.layoutXProperty().bind(pane_modifier.widthProperty().divide(1.75));
        imageUrl.layoutYProperty().bind(pane_modifier.heightProperty().divide(1.95));
        imageUrl.setFont(new Font("Arial",30));
        modifier_modifier.layoutXProperty().bind(pane_modifier.widthProperty().divide(1.6));
        modifier_modifier.layoutYProperty().bind(pane_modifier.heightProperty().divide(1.5));
        System.out.println(email_modifier.getText());
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/text/donneesUtilisateur.txt"));
        modifier_modifier.setStyle("-fx-padding: 0.35em 1.2em;-fx-border-color: #FFFFFF;-fx-border-width:0.1em;-fx-border-style:solid;" +
                "-fx-start-margin :0 0.3em;-fx-end-margin: 0.3em 0;-fx-border-radius: 0.12em;" +
                "-fx-font-family: Roboto, sans-serif;-fx-font-weight: 300;-fx-background-color: #FFFFFF;" +
                "-fx-text-alignment: center;");
        modifier_modifier.setFont((new Font("Arial",30)));
        circle_modifier.setFill(new ImagePattern(image_modifier));
        pane_modifier.getChildren().addAll(circle_modifier,email_modifier,nom_modifier,prenom_modifier,modifier_modifier,imageUrl,line1,line2);
        Scene scene_modifier = new Scene(pane_modifier,primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        modifier_modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    writer.write(email_modifier.getText()+"\n");
                    writer.write(prenom_modifier.getText()+"\n");
                    writer.write(nom_modifier.getText()+"\n");
                    writer.write(imageUrl.getText());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage_modifier.close();
                try {
                    stage1 = creerStageProfile();
                } catch (FileNotFoundException | MalformedURLException e) {
                    e.printStackTrace();
                }
                stage1.show();
            }
        });
        stage_modifier.setScene(scene_modifier);
        return stage_modifier;
    }

    public Stage creerStageProfile() throws FileNotFoundException, MalformedURLException {
        Line line1 = new Line(0,10,10000,500);
        Line line2 = new Line(2000,1000,1000,0);
        line2.setStroke(Color.BLUE);
        line1.setStroke(Color.BLUE);
        Stage profile_Stage = new Stage();
        Pane pane_profile = new Pane();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        InputStream stream = new FileInputStream("src/main/resources/img/icon.jpg");
        Image image_profile = new Image(stream);
        Circle circle_profile = new Circle(primaryScreenBounds.getWidth()/4,primaryScreenBounds.getHeight()/4,200);
        circle_profile.centerXProperty().bind(pane_profile.widthProperty().divide(3.5));
        circle_profile.centerYProperty().bind(pane_profile.heightProperty().divide(2.5));
        circle_profile.radiusProperty().bind(pane_profile.widthProperty().divide(7));
        File file = new File("src/main/resources/text/donneesUtilisateur.txt");
        Scanner sc = new Scanner(file);
        String emailStrng_profile,prenomStrng_profile,nomStrng_profile,imageUrl_profile;
        try {
            emailStrng_profile = sc.nextLine();
            prenomStrng_profile = sc.nextLine();
            nomStrng_profile = sc.nextLine();
            imageUrl_profile = sc.nextLine();
        }
        catch (Exception e){
            emailStrng_profile = "utilisateur@gmail.com";
            prenomStrng_profile = "prenom";
            nomStrng_profile = "nom";
            imageUrl_profile = "src/main/resources/img/icon.jpg";
        }
        try {
            image_profile = new Image(imageUrl_profile);
        }
        catch (Exception e){
            stream = new FileInputStream("src/main/resources/img/icon.jpg");
            image_profile = new Image(stream);
        }
        email_profile = new Label(emailStrng_profile);
        nom_profile = new Label(nomStrng_profile);
        prenom_profile = new Label(prenomStrng_profile);
        Button modifier_profile = new Button("Modifier");
        changeBackgroundOnHoverUsingEvents(modifier_profile);
        email_profile.layoutXProperty().bind(pane_profile.widthProperty().divide(1.75));
        email_profile.layoutYProperty().bind(pane_profile.heightProperty().divide(4));
        email_profile.setFont(new Font("Arial",30));
        nom_profile.layoutXProperty().bind(pane_profile.widthProperty().divide(1.75));
        nom_profile.layoutYProperty().bind(pane_profile.heightProperty().divide(2.7));
        nom_profile.setFont(new Font("Arial",30));
        prenom_profile.layoutXProperty().bind(pane_profile.widthProperty().divide(1.5));
        prenom_profile.layoutYProperty().bind(pane_profile.heightProperty().divide(2.7));
        prenom_profile.setFont(new Font("Arial",30));
        modifier_profile.layoutXProperty().bind(pane_profile.widthProperty().divide(1.6));
        modifier_profile.layoutYProperty().bind(pane_profile.heightProperty().divide(2));
        modifier_profile.setStyle("-fx-padding: 0.35em 1.2em;-fx-border-color: #FFFFFF;-fx-border-width:0.1em;-fx-border-style:solid;" +
                "-fx-start-margin :0 0.3em;-fx-end-margin: 0.3em 0;-fx-border-radius: 0.12em;" +
                "-fx-font-family: Roboto, sans-serif;-fx-font-weight: 300;-fx-background-color: #FFFFFF;" +
                "-fx-text-alignment: center;");

        circle_profile.setFill(new ImagePattern(image_profile));
        pane_profile.getChildren().addAll(circle_profile,email_profile,nom_profile,prenom_profile,modifier_profile,line1,line2);
        Scene scene_profile = new Scene(pane_profile,primaryScreenBounds.getWidth(),primaryScreenBounds.getHeight());//fxmlLoader.load(), 320, 240);
        profile_Stage.setScene(scene_profile);
        modifier_profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                profile_Stage.close();
                try {
                    stage2 = creerStageModifier();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage2.show();
            }
        });
        return profile_Stage;
    }
}
