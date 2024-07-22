/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.ed.stream;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author dpetr
 */
public class StreamDemo {

    private static final Lorem generator = LoremIpsum.getInstance();
    private static final String LINE_DELIMITER = "--------------------------------------------------------------------";

    public static void main(String[] args) {
//        streamIntermediateActions();

        System.out.println("STREAM FINAL ACTIONS");
        System.out.println(LINE_DELIMITER);

        String[] names = {"John", "Costas", "Costas", "Costas", "Costas", "Costas", "Nick", "John", "Costas"};

        System.out.println("Foreach Ordered example");
        System.out.println(LINE_DELIMITER);

        Stream<String> forEachOrderedStream = Stream.of(names);
        forEachOrderedStream.distinct().forEachOrdered(System.out::println);

        Stream<String> collectSetStream = Stream.of(names);
        System.out.println("Collect to Set example");
        System.out.println(LINE_DELIMITER);
        collectSetStream.collect(Collectors.toSet()).forEach(System.out::println);

        Stream<String> collectListStream = Stream.of(names);
        System.out.println("Collect to List example");
        System.out.println(LINE_DELIMITER);
        List<String> collectedList = collectListStream.toList();
        OptionalDouble optionalDouble = IntStream.of(1, 2).average();
        System.out.println("Average is: " + optionalDouble.orElseThrow());

        System.out.println("AnyMatch example");
        System.out.println(LINE_DELIMITER);
        boolean anyMatchFound = Stream.of(names).anyMatch(s -> s.startsWith("C"));
        System.out.println(String.format("%s with character C.", anyMatchFound ? "At least one name starts"
                                         : "No names start with character s"));
    }

    private static void streamIntermediateActions() {
        System.out.println("STREAM INTERMEDIATE ACTIONS");
        System.out.println(LINE_DELIMITER);

        Stream<String> filterStream = Stream.of("Monkey", "Lion", "Giraffe", "Lemur").filter(s -> s.startsWith("L"));

        System.out.println("Filter example");
        System.out.println(LINE_DELIMITER);
        filterStream.forEach(System.out::println);

        List<String> generatedNames = createSampleNamesList(10);
        System.out.println("Source list is: ");
        System.out.println(LINE_DELIMITER);
        generatedNames.forEach(System.out::println);

        System.out.println("Limit example");
        System.out.println(LINE_DELIMITER);
        generatedNames.stream().limit(5).forEach(System.out::println);

        System.out.println("Skip example");
        System.out.println(LINE_DELIMITER);
        generatedNames.stream().skip(15).forEach(System.out::println);

        System.out.println("Map example");
        System.out.println(LINE_DELIMITER);
        generatedNames.stream().map(s -> s.toLowerCase()).forEach(System.out::println);

        System.out.println("Flattening Lists example");
        System.out.println(LINE_DELIMITER);

        List<String> list1 = Arrays.asList("One", "Two", "Three");
        List<String> list2 = Arrays.asList("Four", "Five", "Six");
        List<String> list3 = Arrays.asList("Seven", "Eight", "Nine");

        List<List<String>> listOfLists = Arrays.asList(list1, list2, list3);

        listOfLists.stream().flatMap(Collection::stream).forEach(System.out::println);
    }

    private static List<String> createSampleNamesList(int howMany) {
        List<String> names = new ArrayList<>();

        for (int i = 0; i < howMany; i++) {
            names.add(generator.getFirstName());
        }

        return names;
    }
}
