package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    static void writeAll(List<String> lines, String name) {

    }

    static List<String> readAll(File file) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(file)) {
            List<String> list = new ArrayList<>();
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
            return list;
        } catch(NullPointerException e) {
            throw new IllegalArgumentException("file not selected");
        }
    }
}
