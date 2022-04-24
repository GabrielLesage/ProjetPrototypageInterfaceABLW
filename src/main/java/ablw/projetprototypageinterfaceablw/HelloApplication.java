package ablw.projetprototypageinterfaceablw;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        //test();
    }

    /*public void test() throws Exception {
        ArrayList<Sejour> listSej = new ArrayList<Sejour>();
        listSej.add(new Sejour("Bootcamp Ori", "Dedew","C'est génial chez moi", "Speedrun Ori"));
        RechercheVoyage search = new RechercheVoyage(listSej, "Dedew");
        Task<ArrayList<Sejour>> task = search.createTask();
        new Thread(task).run();
        System.out.println(task.getValue().get(0).hote);
    }*/

    public static void main(String[] args) {
        launch();
    }
}