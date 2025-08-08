package Command.Good;

public class ChangeChannel implements Command {

    private TV tv;
    private int channel;

    public ChangeChannel(TV tv, int channel) {
        this.channel = channel;
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.changeChannel(channel);
    }
}
