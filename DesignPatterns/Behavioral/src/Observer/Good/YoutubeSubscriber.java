package Observer.Good;

public class YoutubeSubscriber implements Subscriber {

    private String name;

    YoutubeSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String video) {
        System.out.println("Notfying subscriber : "+ name + " New video uplaod : " + video);
    }

    public String getName() {
        return this.name;
    }
}
