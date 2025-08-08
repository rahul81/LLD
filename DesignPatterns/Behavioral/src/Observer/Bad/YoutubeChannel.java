package Observer.Bad;

import java.util.ArrayList;

public class YoutubeChannel {

    private ArrayList<Subscriber> subscribers = new ArrayList<>();
    @SuppressWarnings("unused")
    private String channelName;
    private String video;

    YoutubeChannel(String channelName) {
        this.channelName = channelName;
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println("Subscriber added : " + subscriber.getName());
    }

    public void uploadVideo(String video) {
        this.video = video;
        System.out.println("Uploaded video : " + video);
    }

    public void notifySubscribers() {
        for (Subscriber sub : subscribers) {
            System.out.println("Notifying subscriber " + sub.getName() + "about new video" + video);

            sendEmail(sub);

            // now if we wanted to add more ways to notify the code will become harder to maintain and scale.
        }

    }

    public void sendEmail(Subscriber sub) {
        System.out.println("Sending email to : " + sub.getName());
    }
}
