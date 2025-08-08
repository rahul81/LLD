package Observer.Bad;


public class Subscriber{

    private String name;

    Subscriber(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}