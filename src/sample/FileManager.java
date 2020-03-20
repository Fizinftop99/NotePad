package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static void save(Path path, String text) {
        try (FileWriter ostream = new FileWriter(path.toFile())){
            ostream.write(text);
            ostream.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static List<String> readPath(Path currentPath) throws IOException {
        List<String> readLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(currentPath.toFile()));
        while (reader.ready()) {
            readLines.add(reader.readLine());
        }
        return readLines;
    }

    public static Path choosePath(Stage stage, boolean openOrSave) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Txt files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        if (openOrSave)
            return fileChooser.showOpenDialog(stage).toPath();
        else
            return fileChooser.showSaveDialog(stage).toPath();
    }
}