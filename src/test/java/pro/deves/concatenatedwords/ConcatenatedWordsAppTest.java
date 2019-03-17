package pro.deves.concatenatedwords;


import org.apache.commons.lang3.tuple.MutableTriple;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConcatenatedWordsAppTest {
    private static final String FILE_NAME = "words.txt";


    @Test
    public void shouldFindLongestConcatenatedWords() {
        MutableTriple<Integer, String, String> resultTriple = ConcatenatedWordsFinder.findConcatenatedWordsFromFile(FILE_NAME);

        assertThat(resultTriple).isNotNull();
        Integer numberOfConcatenatedWords = resultTriple.getLeft();
        String longestWord = resultTriple.getMiddle();
        String secondLongestWord = resultTriple.getRight();

        assertThat(numberOfConcatenatedWords).isEqualTo(97107);
        assertThat(longestWord).isEqualTo("ethylenediaminetetraacetates");
        assertThat(secondLongestWord).isEqualTo("electroencephalographically");

    }
}