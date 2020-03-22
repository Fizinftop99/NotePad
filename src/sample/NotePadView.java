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
import javafx.stage.FileChooser;
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
        MenuBar menuBar = createMenuBar();
        getChildren().addAll(menuBar, textArea);
        VBox.setVgrow(textArea, Priority.ALWAYS);
    }

    private MenuBar createMenuBar() {
        Menu file = new Menu("File");
        file.getItems().addAll(createOpenItem(), createSaveItem(), createSaveAsItem());
        return new MenuBar(file);
    }

    private MenuItem createOpenItem() {
        MenuItem open = new MenuItem("Open...");
        open.setOnAction(event -> {
            Optional<Path> openFilePath = choosePath(true);
            if(openFilePath.isPresent()) {
                Optional<List<String>> optionalStrings = viewModel.open(openFilePath.get());
                if (optionalStrings.isEmpty())
                    return;
                textArea.clear();
                for (String line : optionalStrings.get()) {
                    textArea.appendText(line + lineSeparator());
                }
            }
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
        saveAs.setOnAction(event -> choosePath(false).ifPresent(path -> viewModel.saveAs(path, textArea.getText())));
        saveAs.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_ANY));
        return saveAs;
    }

    private Optional<Path> choosePath(boolean openOrSave) {
        FileChooser fileChooser = new FileChooser();
        //FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Txt files", "*.txt");
        //fileChooser.getExtensionFilters().add(extensionFilter);
        if (openOrSave)
            return Optional.ofNullable(fileChooser.showOpenDialog(stage).toPath());
        else
            return Optional.ofNullable(fileChooser.showSaveDialog(stage).toPath());
    }
}
