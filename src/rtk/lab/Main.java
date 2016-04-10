package rtk.lab;

import rtk.lab.predictor.IAutocompleteProvider;
import rtk.lab.predictor.IAutocompleteProviderImpl;
import rtk.lab.predictor.ICandidate;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        IAutocompleteProvider auto = new IAutocompleteProviderImpl();
        String seed = "Dog doggy dogs dog";
        auto.train(seed);
        String term = "dog";
        List<ICandidate> words = auto.getWords(term);
        Collections.sort(words, (ICandidate c1, ICandidate c2) -> Integer.compare(c2.getConfidence(), c1.getConfidence()));
        System.out.print("Input: " + term + " --> ");
        words.forEach(e -> System.out.print(e.toString() + " "));
        System.out.print("\r\n");
    }
}
