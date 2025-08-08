package GenericsAndWildcards.src.Genrics.GenericMethod;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    
    public static <T> void addElementsToList(List<T> list, T elem1, T elem2){

        // Adding to list requires known datatype, it won't work with wildcard 
        list.add(elem1);
        list.add(elem2);
    }

    public static <T> T getfirstElemenOfList(List<T> list){
        return list.size() == 0 ? null : list.get(0);
    }

    // alternate way using wildcards

    public static Object getfirstElementOfList2(List<?> list){
        return list.size() == 0 ? null : list.get(0);
    }

    // Wildcards are when reading from a list, does not care about datatype
    public static <T> void printListElements(List<T> list){ 
        System.out.println("Printing all elements in List...");
        for (T item : list){
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        
        List<String> stringList = new ArrayList<String>();
        stringList.add("Rahul");
        ListUtils.addElementsToList(stringList, "John", "Micheal");
        System.out.println(ListUtils.getfirstElementOfList2(stringList));
        ListUtils.printListElements(stringList);

        List<Integer> integerList = new ArrayList<Integer>();
        ListUtils.addElementsToList(integerList, 6, 5);
        System.out.println(ListUtils.getfirstElemenOfList(integerList));
        ListUtils.printListElements(integerList);
        
    }


}
