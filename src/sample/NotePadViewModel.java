package sample;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.lang.System.lineSeparator;

public class NotePadViewModel {
    private Stage stage;
    private Path currentFile;
    private List<String> loggedText;

    public NotePadViewModel(Stage stage) {
        this.stage = stage;
    }

    void save(String savedText) {
        if (currentFile != null) {
            FileManager.save(currentFile, savedText);
        } else {
            saveAs(savedText);
        }
    }

    void saveAs(String savedText) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Txt files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Path openedFile = fileChooser.showSaveDialog(stage).toPath();
        if (openedFile != null) {
            currentFile = openedFile;
            FileManager.save(currentFile, savedText.replaceAll("\n", lineSeparator()));
        }
        updateTitle();
    }

    Optional<List<String>> open() {
        FileChooser fileChooser = new FileChooser();
        Path openedFile = fileChooser.showOpenDialog(stage).toPath();
        if (openedFile != null) {
            currentFile = openedFile;
            try {
                loggedText = FileManager.readPath(currentFile);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            updateTitle();
            return Optional.of(loggedText);
        }
        return Optional.empty();
    }

    void updateTitle() {
        stage.setTitle(fileName() + " — Блокнот");
    }

    private String fileName() {
        if (currentFile == null) {
            return "Безымянный";
        }
        return currentFile.getFileName().toString();
    }
}
