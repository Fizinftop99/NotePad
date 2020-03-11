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
        file.getItems().addAll(createNewButton(), createOpenButton(), createSaveButton(), createSaveAsButton());
        return new MenuBar(file);
    }

    private MenuItem createNewButton() {
        MenuItem create = new MenuItem("New");
        create.setOnAction(event -> viewModel.save());
        create.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY));
        return create;
    }

    private MenuItem createOpenButton() {
        MenuItem open = new MenuItem("Open...");
        open.setOnAction(event -> viewModel.open());
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY));
        return open;
    }

    private MenuItem createSaveButton() {
        MenuItem save = new MenuItem("Save");
        save.setOnAction(event -> viewModel.save());
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY));
        return save;
    }

    private MenuItem createSaveAsButton() {
        MenuItem saveAs = new MenuItem("Save as...");
        saveAs.setOnAction(event -> viewModel.saveAs());
        return saveAs;
    }
}
