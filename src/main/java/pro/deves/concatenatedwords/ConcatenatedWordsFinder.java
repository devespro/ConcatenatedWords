package pro.deves.concatenatedwords;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.tuple.MutableTriple;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static pro.deves.concatenatedwords.SimpleWordReader.getWordsSetSortedByLength;

/**
 * @author Esien Novruzov
 */
public class ConcatenatedWordsFinder {

    private static Set<String> sortedByLengthReadWords;
    private static Set<String> concatenatedWords = new HashSet<>();
    private static String possibleConcatenatedWord;

    public static MutableTriple<Integer, String, String> findConcatenatedWordsFromFile(String fileName) {
        Preconditions.checkArgument(isNotEmpty(fileName), "fileName must be not empty");

        sortedByLengthReadWords = getWordsSetSortedByLength(fileName);
        Preconditions.checkState(!sortedByLengthReadWords.isEmpty(), "The words set is empty");

        sortedByLengthReadWords.forEach(word -> {
            possibleConcatenatedWord = word;
            checkWordForConcatenation(word);
        });

        List<String> result = concatenatedWords.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        int totalWordsNumber = result.size();
        String longestConcatenatedWord = result.get(totalWordsNumber - 1);
        String secondLongestConcatenatedWord = result.get(totalWordsNumber - 2);

        MutableTriple<Integer, String, String> resultTriple = new MutableTriple<>();
        resultTriple.setLeft(totalWordsNumber);
        resultTriple.setMiddle(longestConcatenatedWord);
        resultTriple.setRight(secondLongestConcatenatedWord);

        return resultTriple;
    }

    private static void checkWordForConcatenation(String word) {
        for (int i = 0; i < word.length(); i++){
            String leftPart = word.substring(0, i + 1);
            String rightPart = word.substring(i + 1);

            if (sortedByLengthReadWords.contains(leftPart)) {
                if (sortedByLengthReadWords.contains(rightPart)){
                    concatenatedWords.add(possibleConcatenatedWord);
                    break;
                }
                checkWordForConcatenation(rightPart);
            }
        }
    }
}
