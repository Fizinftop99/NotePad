package sample;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static void save(Path path, String text) {
        try (FileWriter outStream = new FileWriter(path.toFile())){
            outStream.write(text);
            outStream.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static List<String> readPath(Path currentPath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(currentPath.toFile()))) {
            List<String> readLines = new ArrayList<>();
            while (reader.ready()) {
                readLines.add(reader.readLine());
            }
            return readLines;
        } catch (IOException e) {
        throw new UncheckedIOException(e);
        }
    }
}