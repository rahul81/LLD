package Interface_Seggregation.BadCode;

public class BasicPrinter implements Machine {
    

    @Override
    public void print(){
        // print logic
    };

    // Below methods are irrelevant for a Basic printer 
    // interface for each functionality should be separate
    // all combined interfaces can be used in All in one printer

    @Override 
    public void scan(){
        // scan logic

        throw new UnsupportedOperationException("Scan is invalid operation for basic printer");
    }

    @Override 
    public void fax(){
        throw new UnsupportedOperationException("Fax is invalid operation for basic printer.");
    }
}
