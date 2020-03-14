package sample;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

import static java.lang.System.lineSeparator;

public class NotePadView extends VBox {
    private NotePadViewModel viewModel;
    private TextArea textArea;

    public NotePadView(NotePadViewModel viewModel, Stage stage) {
            this.viewModel = viewModel;
        textArea = new TextArea();
        MenuBar menuBar = initMenuBar(stage);
        getChildren().addAll(menuBar, textArea);
        VBox.setVgrow(textArea, Priority.ALWAYS);
    }

    private MenuBar initMenuBar(Stage stage) {
        Menu file = new Menu("File");
        file.getItems().addAll(createOpenItem(stage), createSaveItem(stage), createSaveAsItem(stage));
        return new MenuBar(file);
    }

    private MenuItem createOpenItem(Stage stage) {
        MenuItem open = new MenuItem("Open...");
        open.setOnAction(actionEvent -> {
            Optional<List<String>> optionalStrings = viewModel.open(stage);
            if (optionalStrings.isEmpty())
                return;
            refill(optionalStrings.get());
        });
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY));
        return open;
    }

    private MenuItem createSaveItem(Stage stage) {
        MenuItem save = new MenuItem("Save");
        save.setOnAction(event -> viewModel.save(stage, textArea.getText()));
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY));
        return save;
    }

    private MenuItem createSaveAsItem(Stage stage) {
        MenuItem saveAs = new MenuItem("Save as...");
        saveAs.setOnAction(event -> viewModel.saveAs(stage, textArea.getText()));
        return saveAs;
    }

    private void refill(List<String> contents) {
        textArea.clear();
        for (String line : contents) {
            textArea.appendText(line + lineSeparator());
        }
    }
}
