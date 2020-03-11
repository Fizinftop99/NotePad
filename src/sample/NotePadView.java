package sample;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class NotePadView extends VBox {
    private NotePadViewModel viewModel;
    private MenuBar menuBar;
    private TextArea textArea;
    public NotePadView(NotePadViewModel viewModel) {
        this.viewModel = viewModel;
        //menuBar = new MenuBar();
        textArea = new TextArea();
        menuBar = initMenuBar();
       /* textArea.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(viewModel.getStage());
            try {
                List<String> all = FileUtils.readAll(file);
                StringBuilder sb = new StringBuilder();

                all.forEach(sb::append);
                textArea.setText(sb.toString());
            } catch (FileNotFoundException | NullPointerException e) {
                throw new IllegalArgumentException("Wrong path");
            }
        });
        */
        //initMenuBar();
        getChildren().addAll(menuBar, textArea);
        VBox.setVgrow(textArea, Priority.ALWAYS);
        //stage.setScene(new Scene(VBox));
    }

    private MenuItem createOpenButton() {
        MenuItem open = new MenuItem("Открыть..");
        open.setOnAction(event -> viewModel.open());
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY));
        return open;
    }

    private MenuBar initMenuBar() {
        Menu file = new Menu("File");
        file.getItems().addAll(createOpenButton());
        /*MenuItem open = new MenuItem( "Open");
        open.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(viewModel.getStage());
            if(file != null) try {
                List<String> all = FileUtils.readAll(file);
                StringBuilder sb = new StringBuilder();
                all.forEach(sb::append);
                textArea.setText(sb.toString());
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("Wrong path");
            }
        });*/
        //Menu fileMenu = new Menu("File");
        //fileMenu.getItems().add(open);
        //menuBar.getMenus().add(fileMenu);
        return new MenuBar(file);
    }
}
