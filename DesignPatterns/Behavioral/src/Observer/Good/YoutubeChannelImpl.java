package Observer.Good;

import java.util.ArrayList;

public class YoutubeChannelImpl implements YoutubeChannel {

    private ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
    private String video;
    @SuppressWarnings("unused")
    private String channelName;

    YoutubeChannelImpl(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber sub : subscribers) {
            sub.update(video); // Call update for all subscriber type (Youtube, Email, Push, etc)
        }

    }

    @Override
    public void uploadVideo(String video) {
        this.video = video;
        System.out.println("Uploaded video : " + video);
        notifySubscribers();

    }
}
