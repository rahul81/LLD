package Command.Good;

public class SwitchOff implements Command {

    private TV tv;

    public SwitchOff(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.switchOff();
    }

}
