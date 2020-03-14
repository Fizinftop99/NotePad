package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.lang.System.lineSeparator;

public class NotePadViewModel {
    private Stage stage;
    private Path currentPath;

    public NotePadViewModel(Stage stage) {
        this.stage = stage;
    }

    void save(String savedText) {
        if (currentPath != null) {
            FileManager.save(currentPath, savedText);
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
            currentPath = openedFile;
            FileManager.save(currentPath, savedText.replaceAll("\n", lineSeparator()));
        }
        updateTitle();
    }

    Optional<List<String>> open() {
        FileChooser fileChooser = new FileChooser();
            File openedFile = fileChooser.showOpenDialog(stage);
            if (openedFile != null) {
                currentPath = openedFile.toPath();
                List<String> loggedText;
                try {
                    loggedText = FileManager.readPath(currentPath);
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
        if (currentPath == null) {
            return "Безымянный";
        }
        return currentPath.getFileName().toString();
    }
}
