package Iterator.Good;

public class Main {
    public static void main(String[] args) {

        Playlist myPlaylist = new Playlist();
        myPlaylist.addSong("Song 1");
        myPlaylist.addSong("Song 2");
        myPlaylist.addSong("Song 3");

        PlaylistIterator SimpleIterator = myPlaylist.getIterator("simple");

        System.out.println("Simple PlayList...");
        while (SimpleIterator.hasNext()) {
            System.out.println("Playing song : " + SimpleIterator.next());
        }

        PlaylistIterator ShuffledIterator = myPlaylist.getIterator("shuffled");

        System.out.println("Shuffeld Playlist...");
        while (ShuffledIterator.hasNext()) {
            System.out.println("Playing song : " + ShuffledIterator.next());
        }

    }
}
