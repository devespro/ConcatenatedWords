package pro.deves.concatenatedwords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * @author Esien Novruzov
 */
public class SimpleWordReader {

    private static final Logger LOGGER = Logger.getLogger(SimpleWordReader.class.getSimpleName());

    public static Set<String> getWordsSetSortedByLength(String fileName){
        return getWordsSetFromFile(fileName)
                .stream()
                .sorted((Comparator.comparingInt(String::length)))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static Set<String> getWordsSetFromFile(String fileName) {
        return readWordsFromFile(fileName);
    }

    private static Set<String> readWordsFromFile(String fileName) {
        Set<String> words = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, format("Error reading data from + %s", fileName));
        }
        return words;
    }
}
