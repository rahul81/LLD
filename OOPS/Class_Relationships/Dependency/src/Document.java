package Class_Relationships.Dependency.src;

public class Document {
    String content;

    Document(String context) {
        this.content = context;
    }

    void printDocument(Printer printer){
        printer.print(content);
    }
}

