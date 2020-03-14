package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
}