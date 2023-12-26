package com.bomfim.interview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HashSearch {

    public static void main(String[] args) {
        List<String> comments = List.of(
                "Great post! Really informative.",
                "I totally agree with your points.",
                "This is awesome!",
                "Nice work. Keep it up!",
                "Great insights. Thanks for sharing.",
                "I love it, it is awesome.",
                "Great "
        );

        Map<String, Long> wordFrequencies = analyzeWordFrequencies(comments);

        displayTopWords(wordFrequencies, 3);
    }

    private static Map<String, Long> analyzeWordFrequencies(List<String> comments) {
        return comments
                .stream()
                .flatMap(c -> Arrays.stream(c.split("[\\s.,;!?]+")))
                .filter(w -> w.length() > 2)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static void displayTopWords(Map<String, Long> wordFrequencies, int topN) {
        wordFrequencies.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(topN)
                .forEach(e ->   System.out.println(e.getKey() + ": " + e.getValue() + " occurrences"));
    }
}
