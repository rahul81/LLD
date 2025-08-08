package Observer.Good;

public interface YoutubeChannel {
    void addSubscriber(Subscriber sub);
    void removeSubscriber(Subscriber sub);
    void notifySubscribers();
    void uploadVideo(String video);
}
