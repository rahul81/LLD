package GenericsAndWildcards.src.Genrics.GenericClass;

public class MyArrayList<T> {
    
    private Object[] elements; // elements is array of object type
    private int size = 0;

    public MyArrayList(){
        elements = new Object[10]; // initial size
    }

    public void add(T element){
        if (size == elements.length){
            resize();
        }

        elements[size++] = element;
    }

    private void resize(){
        Object[] newElements = new Object[2 * elements.length];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

}
