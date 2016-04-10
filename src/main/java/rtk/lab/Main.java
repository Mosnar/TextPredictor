package rtk.lab;

import org.apache.commons.cli.*;
import rtk.lab.predictor.IAutocompleteProvider;
import rtk.lab.predictor.IAutocompleteProviderImpl;
import rtk.lab.predictor.ICandidate;

import java.util.Collections;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption(OptionBuilder.withLongOpt("training-string")
                .withDescription("Training string")
                .isRequired(true)
                .hasArg()
                .withArgName("T")
                .create());

        options.addOption(OptionBuilder.withLongOpt("fragment")
                .withDescription("Search query")
                .isRequired(true)
                .hasArg()
                .withArgName("F")
                .create());

        try {
            CommandLine line = parser.parse(options, args);

            String seed = line.getOptionValue("training-string");
            String partial = line.getOptionValue("fragment");

            // Create a new instance of the autocomplete implementation and train it on the seed
            IAutocompleteProvider auto = new IAutocompleteProviderImpl();
            auto.train(seed);
            System.out.println("Train: " + seed);

            // Get words from the partial seed, sort them based on confidence, and print
            List<ICandidate> words = auto.getWords(partial);
            Collections.sort(words, (ICandidate c1, ICandidate c2) -> Integer.compare(c2.getConfidence(), c1.getConfidence()));
            System.out.print("Input: " + partial + " --> ");
            words.forEach(e -> System.out.print(e.toString() + " "));
            System.out.print("\r\n");
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }

    }
}
