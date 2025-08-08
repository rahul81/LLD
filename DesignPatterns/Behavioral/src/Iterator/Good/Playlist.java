package Iterator.Good;

import java.util.ArrayList;

public class Playlist {

    private ArrayList<String> songs;

    Playlist() {
        songs = new ArrayList<String>();
    }

    public PlaylistIterator getIterator(String type) {
        switch (type) {
            case "simple":
                return new SimpleIterator(this);
            case "shuffled":
                return new ShuffledIterator(this);

            default:
                throw new IllegalArgumentException("Unkown iterator type : " + type);
        }
    }

    public void addSong(String song) {
        songs.add(song);
    }

    public ArrayList<String> getSongs() {
        return songs;
    }
}
