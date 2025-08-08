package Observer.Good;

public class EmailSubscriber implements Subscriber {

    private String email;

    EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String video) {
        System.out.println("Sending email to : " + email + " for new video uploaded : " + video);
    }
}
