package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotePad extends Application {
    @Override
    public void start(Stage primaryStage) {
        NotePadViewModel viewModel = new NotePadViewModel();
        NotePadView view = new NotePadView(viewModel, primaryStage);
        primaryStage.setScene(new Scene(view, 640, 480));
        primaryStage.show();
    }
}
