package GenericsAndWildcards.src.WildCards.example;

import java.util.List;

public class Example {
    
    public static void PrintList(List<?> list){
        for(Object item : list){
            System.out.println(item);
        }
    }

    public static void main(String[] args){
        List<String> list = List.of("Apple","Mango","Orange");
        PrintList(list);

        List<Integer> list2 = List.of(1,2,3,4);
        PrintList(list2);
    }

}
