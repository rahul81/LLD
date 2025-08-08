package GenericsAndWildcards.src.WildCards.Bounded;

import java.util.List;
/*
 *  Restricts the type to Type or its subclasses.
 *  Useful for read-only operations where the specific type is not required.
 */

public class UpperBoundedWildcard {

    public static void printNumbers(List<? extends Number> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4);
        printNumbers(list);

        List<Double> list2 = List.of(1.1, 1.2, 1.3);
        printNumbers(list2);

        // List<String> list3 = List.of("a", "b", "c");
    }
}
