package sample;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static void save(Path path, String text) {
        try (BufferedWriter ostream = new BufferedWriter(new FileWriter(path.toFile()))){
            ostream.write(text);
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }
    public static List<String> readPath(Path currentPath) {
        List<String> readLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(currentPath.toFile()))){
            while (reader.ready()) {
                readLines.add(reader.readLine());
            }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
        return readLines;
    }
}