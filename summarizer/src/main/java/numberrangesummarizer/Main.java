package numberrangesummarizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {

        // safety check for null or empty input
        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        ArrayList<Integer> numbers = new ArrayList<>();
        String[] split = input.split(",");
        
        for (String s : split) {
            try {
                numbers.add(Integer.parseInt(s.trim()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format: " + s, e);
            }
        }

        return numbers;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        // safety check for null or empty input
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input collection cannot be null or empty");
        }

        StringBuilder result = new StringBuilder();

        int prev = Integer.MAX_VALUE; //Infinity to indicate error TODO: FIX THIS TO SOMETHING BETTER
        boolean isFirst = true; //used to check if the number is the first in the range for comma placing
        boolean isRange = false;

        Iterator<Integer> it = input.iterator(); 
        while (it.hasNext()) {    
            int curr = it.next();
            
            if (isRange) {
                if (!(curr == (prev + 1))) {
                    // we have reached the end of a range
                    result.append("-" + Integer.toString(prev));
                    result.append(", " + Integer.toString(curr));
                    isRange = false;
                }
            } else { // !isRange 
                if (curr == (prev + 1)) {
                    isRange = true; 
                    //indicate that we 
                } else {
                    // this is just another number in the sequence
                    if (!isFirst) result.append(" ,"); isFirst = false;
                    result.append(Integer.toString(curr));
                }
            }

            prev = curr;
        }
        return result.toString();
    }

}

public class Main {
    public static void main(String[] args) {
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        String exString = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> input = summarizer.collect(exString);
        String output = summarizer.summarizeCollection(input);
        System.out.println(output);

    }
}
