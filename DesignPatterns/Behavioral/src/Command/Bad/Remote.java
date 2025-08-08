package Command.Bad;

public class Remote {
    private TV tv;

    public Remote(TV tv) {
        this.tv = tv;
    }

    public void switchOn() {
        tv.switchOn();
    }

    public void switchOff() {
        tv.switchOff();
    }

    public void changeChannel(int channel) {
        tv.changeChannel(channel);
    }

    public void adjustVolume(int volume) {
        tv.adjustVolume(volume);
    }

    public void switchOnChangeVolumeAndChangeChannel(int volume, int channel) {
        tv.switchOn();
        tv.adjustVolume(volume);
        tv.changeChannel(channel);
    }
}


/*
 * As you can see, we are directly calling the methods on the TV object inside the RemoteControl class. So, 
 * if we wanted to add new functionality or extend the remote with new features, 
 * we’d have to keep modifying the RemoteControl class, leading to code duplication and a lack of flexibility.

The Interviewer’s Questions 🤔

Now imagine an interviewer asking:

• What if we want to add more functionalities to the remote?

• What if we want to store a sequence of operations (like turning the TV on, changing the channel, and adjusting the volume) and execute them later?

• How would you handle a situation where multiple remotes are controlling different devices in the future?


These are great questions that push us to rethink the design. The current approach requires modification each time we add a new feature, and the scalability isn’t great.

The Ugly Code 🤦‍♂️
Let’s take it a step further and see how things get ugly when we add more functionalities. 
If we decide to add more complex operations (like multiple actions in a sequence), 
the code starts to get messy and hard to maintain.
 */