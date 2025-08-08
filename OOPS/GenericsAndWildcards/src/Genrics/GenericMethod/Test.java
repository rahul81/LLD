package GenericsAndWildcards.src.Genrics.GenericMethod;

//Generic Method

public class Test {
    

    <T> void genericDisplay(T element){
        System.out.println(element.getClass().getName() + " = "+ element);
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.genericDisplay(11);
        test.genericDisplay("John");

        /*
         * java.lang.Integer = 11
         * java.lang.String = John
         */
    }
}
