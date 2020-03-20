package sample;

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
            saveAs(null, savedText);
        }
    }

    void saveAs(Path path, String savedText) {
        if (path != null) {
            currentPath = path;
            FileManager.save(currentPath, savedText.replaceAll("\n", lineSeparator()));
        }
    }

    Optional<List<String>> open(Path path) {
        if (path != null) {
            currentPath = path;
            List<String> loggedText;
            try {
                loggedText = FileManager.readPath(currentPath);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            return Optional.of(loggedText);
        }
        return Optional.empty();
    }
}
