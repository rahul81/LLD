package Command.Good;

public class RemoteControl {

    private Command switchOn;
    private Command switchOff;

    public void setCommandSwitchOn(Command switchOn) {
        this.switchOn = switchOn;
    }

    public void setCommandSwitchOff(Command switchOff) {
        this.switchOff = switchOff;
    }

    public void pressOnButton() {
        switchOn.execute();
    }

    public void pressOffButton() {
        switchOff.execute();
    }
}
