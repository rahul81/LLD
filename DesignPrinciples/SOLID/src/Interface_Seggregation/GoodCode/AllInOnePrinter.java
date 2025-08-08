package Interface_Seggregation.GoodCode;

public class AllInOnePrinter implements Printer, Scanner, FaxMachine{
    
    @Override
    public void print(){

    }

    @Override
    public void scan(){

    }

    @Override
    public void fax(){

    }
}


// This Principle is similar to Single responsibilty but for interfaces.

/*
 *
 * This principle is the first principle that applies to Interfaces instead of classes in SOLID and it is similar to the Single Responsibility principle. 
 * It states that do not force any client to implement an interface which is irrelevant to them. Here your main goal is to focus on avoiding fat interface and give preference to many small client-specific interfaces. 
 * You should prefer many client interfaces rather than one general interface and each interface should have a specific responsibility. 
 */