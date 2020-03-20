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

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.lang.System.lineSeparator;

public class NotePadView extends VBox {
    private NotePadViewModel viewModel;
    private TextArea textArea;
    private Stage stage;

    public NotePadView(NotePadViewModel viewModel, Stage stage) {
        this.stage = stage;
        this.viewModel = viewModel;
        textArea = new TextArea();
        MenuBar menuBar = initMenuBar();
        getChildren().addAll(menuBar, textArea);
        VBox.setVgrow(textArea, Priority.ALWAYS);
    }

    private MenuBar initMenuBar() {
        Menu file = new Menu("File");
        file.getItems().addAll(createOpenItem(), createSaveItem(), createSaveAsItem());
        return new MenuBar(file);
    }

    private MenuItem createOpenItem() {
        MenuItem open = new MenuItem("Open...");
        Path openFilePath = FileManager.choosePath(stage, true);
        open.setOnAction(actionEvent -> {
            Optional<List<String>> optionalStrings = viewModel.open(openFilePath);
            if (optionalStrings.isEmpty())
                return;
            refill(optionalStrings.get());
        });
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY));
        return open;
    }

    private MenuItem createSaveItem() {
        MenuItem save = new MenuItem("Save");
        save.setOnAction(event -> viewModel.save(textArea.getText()));
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY));
        return save;
    }

    private MenuItem createSaveAsItem() {
        MenuItem saveAs = new MenuItem("Save as...");
        Path saveFilePath = FileManager.choosePath(stage, false);
        saveAs.setOnAction(event -> viewModel.saveAs(saveFilePath, textArea.getText()));
        return saveAs;
    }

    private void refill(List<String> contents) {
        textArea.clear();
        for (String line : contents) {
            textArea.appendText(line + lineSeparator());
        }
    }
}
