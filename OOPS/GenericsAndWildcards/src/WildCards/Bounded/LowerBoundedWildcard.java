package GenericsAndWildcards.src.WildCards.Bounded;

import java.util.List;

/*
 * • Restricts the type to Type or its superclasses.
 * • Useful for write operations.
 */

public class LowerBoundedWildcard {
    
    public static void printNumbers(List<? super Integer> list){
        for(Object item : list){
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        List<Number> list1 = List.of(1, 2, 3);
        printNumbers(list1);

        // Won't work because the type of printNumbers lowerbounded by integer, can't use float , double
        // List<Double> list2 = List.of(1.1, 1.2, 1.3);
        // printNumbers(list2);
    }
}
