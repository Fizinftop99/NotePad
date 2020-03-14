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
    private Path currentPath;

    void save(String savedText) {
        if (currentPath != null) {
            FileManager.save(currentPath, savedText);
        } else {
            saveAs(currentPath, savedText);
        }
    }

    void saveAs(Path path, String savedText) {
        if (path != null) {
            currentPath = path;
            FileManager.save(currentPath, savedText.replaceAll("\n", lineSeparator()));
        }
    }

    Optional<List<String>> open(Stage stage) {
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
            stage.setTitle(fileName());
            return Optional.of(loggedText);
        }
        return Optional.empty();
    }

    private String fileName() {
        if (currentPath == null) {
            return "NoName";
        }
        return currentPath.getFileName().toString();
    }
}
