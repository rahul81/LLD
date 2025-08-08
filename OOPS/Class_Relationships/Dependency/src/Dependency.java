package Class_Relationships.Dependency.src;

/*
Dependency represents a temporary relationship where one class uses another class, typically via method parameters or local variables. 
It’s like borrowing a tool for a short while 
*/

public class Dependency {
    public static void main(String[] args) {
        Document doc = new Document("Hello World");
        Printer printer = new Printer();
        doc.printDocument(printer);
    }
}
