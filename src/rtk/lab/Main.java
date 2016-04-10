package rtk.lab;

import rtk.lab.predictor.IAutocompleteProvider;
import rtk.lab.predictor.IAutocompleteProviderImpl;
import rtk.lab.predictor.ICandidate;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String seed = "The third thing that I need to tell you is that this thing does not think thoroughly.";
        String partial = "th";

        // Create a new instance of the autocomplete implementation and train it on the seed
        IAutocompleteProvider auto = new IAutocompleteProviderImpl();
        auto.train(seed);

        // Get words from the partial seed, sort them based on confidence, and print
        List<ICandidate> words = auto.getWords(partial);
        Collections.sort(words, (ICandidate c1, ICandidate c2) -> Integer.compare(c2.getConfidence(), c1.getConfidence()));
        System.out.print("Input: " + partial + " --> ");
        words.forEach(e -> System.out.print(e.toString() + " "));
        System.out.print("\r\n");
    }
}
