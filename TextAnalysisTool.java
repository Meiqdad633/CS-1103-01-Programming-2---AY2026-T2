import java.util.*;

public class TextAnalysisTool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Input
        System.out.println("Enter a paragraph of text:");
        String text = scanner.nextLine();

        // Character Count
        int charCount = text.length();
        System.out.println("Total characters: " + charCount);

        // Word Count
        String[] words = text.trim().split("\\s+");
        int wordCount = words.length;
        System.out.println("Total words: " + wordCount);

        // Most Common Character
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
            }
        }
        char mostCommon = ' ';
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : charFreq.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommon = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        System.out.println("Most common character: " + mostCommon);

        // Character Frequency
        System.out.println("Enter a character to check frequency:");
        char userChar = scanner.next().toLowerCase().charAt(0);
        int userCharCount = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            if (c == userChar) userCharCount++;
        }
        System.out.println("Frequency of '" + userChar + "': " + userCharCount);

        // Word Frequency
        System.out.println("Enter a word to check frequency:");
        String userWord = scanner.next().toLowerCase();
        int userWordCount = 0;
        for (String w : words) {
            if (w.toLowerCase().equals(userWord)) userWordCount++;
        }
        System.out.println("Frequency of \"" + userWord + "\": " + userWordCount);

        // Unique Words
        Set<String> uniqueWords = new HashSet<>();
        for (String w : words) {
            uniqueWords.add(w.toLowerCase());
        }
        System.out.println("Number of unique words: " + uniqueWords.size());

        scanner.close();
    }
}
