package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;



class MainTest {

    @Test
    void testCollectValidInput() {
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> expected = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        assertEquals(expected, summarizer.collect(input));
    }

    @Test
    void testCollectEmptyInput() {
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.collect(""));
        assertEquals("Input cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCollectNullInput() {
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.collect(null));
        assertEquals("Input cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCollectInvalidNumberFormat() {
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.collect("1,2,a,4"));
        assertTrue(exception.getMessage().contains("Invalid number format"));
    }

    @Test
    void testSummarizeCollectionValidInput() {
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Collection<Integer> input = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        assertEquals(expected, summarizer.summarizeCollection(input));
    }

    @Test
    void testSummarizeCollectionEmptyInput() {
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.summarizeCollection(Arrays.asList()));
        assertEquals("Input collection cannot be null or empty", exception.getMessage());
    }

    @Test
    void testSummarizeCollectionNullInput() {
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.summarizeCollection(null));
        assertEquals("Input collection cannot be null or empty", exception.getMessage());
    }
}