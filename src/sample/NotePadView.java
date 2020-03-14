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

import java.util.List;
import java.util.Optional;

import static java.lang.System.lineSeparator;

public class NotePadView extends VBox {
    private NotePadViewModel viewModel;
    private MenuBar menuBar;
    private TextArea textArea;

    public NotePadView(NotePadViewModel viewModel) {
        this.viewModel = viewModel;
        textArea = new TextArea();
        menuBar = initMenuBar();
        getChildren().addAll(menuBar, textArea);
        VBox.setVgrow(textArea, Priority.ALWAYS);
    }

    private MenuBar initMenuBar() {
        Menu file = new Menu("File");
        file.getItems().addAll(createNewItem(), createOpenItem(), createSaveItem(), createSaveAsItem());
        return new MenuBar(file);
    }

    private MenuItem createNewItem() {
        MenuItem create = new MenuItem("New");
        create.setOnAction(event -> viewModel.save(textArea.getText()));
        create.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY));
        return create;
    }

    private MenuItem createOpenItem() {
        MenuItem open = new MenuItem("Open...");
        open.setOnAction(actionEvent -> {
            Optional<List<String>> optionalStrings = viewModel.open();
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
        saveAs.setOnAction(event -> viewModel.saveAs(textArea.getText()));
        return saveAs;
    }

    private void refill(List<String> contents) {
        textArea.clear();
        for (String line : contents) {
            textArea.appendText(line + lineSeparator());
        }
    }
}
