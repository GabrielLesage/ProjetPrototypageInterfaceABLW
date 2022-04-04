package ablw.projetprototypageinterfaceablw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");

        Map<String, Object> namespace = fxmlLoader.getNamespace();
        System.out.println(namespace.get("list_section"));

        ImageView img = (ImageView) namespace.get("fond");
        img.setFitWidth(1905);
        img.setFitHeight(600);

        for (int i = 0; i < 2500; i++) {
            PaneSection paneSection = new PaneSection("test", 5);
            VBox tmp = (VBox) namespace.get("list_section");
            tmp.getChildren().add(paneSection.getControl());
        }


        // System.out.println("er");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}