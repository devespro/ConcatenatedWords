package pro.deves.concatenatedwords;

import org.apache.commons.lang3.tuple.MutableTriple;

import static java.lang.String.format;

/**
 * @author Esien Novruzov
 */
public class ConcatenatedWordsApp {

    private static final String FILE_NAME = "words.txt";

    public static void main(String[] args) {

        MutableTriple<Integer, String, String> resultTriple = ConcatenatedWordsFinder.findConcatenatedWordsFromFile(FILE_NAME);

        System.out.println(format(
                "The number of concatenated words: %d \n" +
                "The longest word is %s \n" +
                "The second longest word is %s",
                resultTriple.getLeft(),
                resultTriple.getMiddle(),
                resultTriple.getRight()));
    }
}
