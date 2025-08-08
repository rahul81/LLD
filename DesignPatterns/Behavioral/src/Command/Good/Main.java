package Command.Good;

public class Main {
    public static void main(String[] args) {

        TV tv = new TV();

        // Create commands
        Command switchOn = new SwitchOn(tv);
        Command switchOff = new SwitchOff(tv);
        Command changeChannel = new ChangeChannel(tv, 11);
        Command adjustVolume = new AdjustVolume(tv, 10);

        RemoteControl remote = new RemoteControl();
        // Store commands
        remote.setCommandSwitchOn(switchOn);
        remote.setCommandSwitchOff(switchOff);

        // execute commands
        remote.pressOnButton();
        remote.pressOffButton();

        remote.pressOnButton();

        // execute other commands separately
        changeChannel.execute();
        adjustVolume.execute();

        remote.pressOffButton();
    }
}
