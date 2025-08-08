package GenericsAndWildcards.src.Genrics.GenericClass;

public class Test<T> {
    
    T obj;

    Test(T obj){
        this.obj = obj;
    }

    public T getObject(){
        return this.obj;
    }

}
