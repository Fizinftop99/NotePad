package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        NotePadView notePadView = new NotePadView(new NotePadViewModel(primaryStage));
        primaryStage.setScene(new Scene(notePadView, 640, 480));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
