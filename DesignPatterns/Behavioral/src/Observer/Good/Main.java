package Observer.Good;

public class Main {

    public static void main(String[] args) {
        
        YoutubeSubscriber ytSub1 = new YoutubeSubscriber("Rahul");
        YoutubeSubscriber ytSub2 = new YoutubeSubscriber("Rishi");

        EmailSubscriber emailSub1 = new EmailSubscriber("Frank");
        EmailSubscriber emailSub2 = new EmailSubscriber("Denice");


        YoutubeChannelImpl ytChannel = new YoutubeChannelImpl("JavaMagic");
        // Added yt subs
        ytChannel.addSubscriber(ytSub1);
        ytChannel.addSubscriber(ytSub2);

        // Added email subs
        ytChannel.addSubscriber(emailSub2);
        ytChannel.addSubscriber(emailSub1);

        ytChannel.uploadVideo("Java Observer design pattern tutorial");

        ytChannel.removeSubscriber(emailSub2);

        ytChannel.uploadVideo("Obeserver pattern use cases");
    }
    
}
