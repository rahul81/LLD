package Command.Good;

public class SwitchOn implements Command {

    private TV tv;

    public SwitchOn(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.switchOn();
    }

}
