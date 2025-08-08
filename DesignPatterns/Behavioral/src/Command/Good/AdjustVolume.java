package Command.Good;

public class AdjustVolume implements Command {
    private TV tv;
    private int volume;

    public AdjustVolume(TV tv, int volume) {
        this.tv = tv;
        this.volume = volume;
    }

    @Override
    public void execute() {
        tv.adjustVolume(volume);
    }
}
