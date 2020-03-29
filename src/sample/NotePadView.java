package sample;

import javafx.scene.Group;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
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
        Menu help = new Menu("Help");
        help.getItems().add(createHelpItem());
        return new MenuBar(file, help);
    }

    private MenuItem createHelpItem() {
        MenuItem help = new MenuItem("Help");
        help.setOnAction(event -> {
            Stage newStage = new Stage();
            newStage.initOwner(stage);
            newStage.initModality(Modality.WINDOW_MODAL);
            Text text = new Text("Sink or swim. Use your bootstraps.");
            text.setLayoutY(15);
            Group group = new Group(text);
            Scene scene = new Scene(group);
            newStage.setScene(scene);
            newStage.setTitle("Help");
            newStage.setWidth(300);
            newStage.setHeight(300);
            newStage.show();
        });
        return help;
    }

    private MenuItem createOpenItem() {
        MenuItem open = new MenuItem("Open...");
        open.setOnAction(event -> {
            Optional<File> openFile = chooseFile(true);
            if(openFile.isPresent()) {
                Optional<List<String>> optionalStrings = viewModel.open(openFile.get().toPath());
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
        saveAs.setOnAction(event ->
                chooseFile(false).ifPresent(file ->
                        viewModel.saveAs(file.toPath(), textArea.getText())));
        saveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY, KeyCombination.SHIFT_ANY));
        return saveAs;
    }

    private Optional<File> chooseFile(boolean openOrSave) {
        FileChooser fileChooser = new FileChooser();
        if (openOrSave)
            return Optional.ofNullable(fileChooser.showOpenDialog(stage));
        else
            return Optional.ofNullable(fileChooser.showSaveDialog(stage));
    }
}
