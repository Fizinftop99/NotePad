package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotePad extends Application {
    @Override
    public void start(Stage primaryStage) {
        //NotePadViewModel viewModel = new NotePadViewModel();
        //NotePadView notePadView = new NotePadView(viewModel);
        primaryStage.setScene(new Scene(new NotePadView(new NotePadViewModel()), 640, 480));
        primaryStage.show();
    }
}
