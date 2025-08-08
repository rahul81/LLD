package GenericsAndWildcards.src.Genrics.GenericClass;

public class Main {
    
    public static void main(String[] args) {
        
        Test<Integer> test = new Test<Integer>(15);

        System.out.println(test.getObject());

        Test<String> test2 = new Test<String>("Rahul");

        System.out.println(test2.getObject());


        MyArrayList<String> list1 = new MyArrayList<String>();
        // list1.add(1); won't work because of explicit type declaration
        list1.add("Rahu");
    }
}
