package rtk.lab;

import rtk.lab.predictor.IAutocompleteProvider;
import rtk.lab.predictor.IAutocompleteProviderImpl;
import rtk.lab.predictor.ICandidate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        IAutocompleteProvider auto = new IAutocompleteProviderImpl();
        auto.train("The third thing that I need to tell you is that this thing does not think thoroughly.");
        List<ICandidate> words = auto.getWords("th");
        Collections.sort(words, (ICandidate c1, ICandidate c2) -> Integer.compare(c2.getConfidence(), c1.getConfidence()));
        words.forEach(e -> System.out.println(e.toString()));
    }
}
