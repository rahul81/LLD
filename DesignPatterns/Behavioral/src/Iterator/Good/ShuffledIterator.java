package Iterator.Good;

import java.util.ArrayList;
import java.util.Collections;

public class ShuffledIterator implements PlaylistIterator {

    Playlist playlist;
    private int index = 0;
    private ArrayList<String> shuffledSongs;

    ShuffledIterator(Playlist playlist){
        this.playlist = playlist;
        this.shuffledSongs = this.playlist.getSongs();
        Collections.shuffle(this.shuffledSongs);
    }

    @Override
    public boolean hasNext() {

        return index < shuffledSongs.size();
    }

    @Override
    public String next() {
        return shuffledSongs.get(index++);
    }

}
