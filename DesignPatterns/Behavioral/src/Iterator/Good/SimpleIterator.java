package Iterator.Good;

public class SimpleIterator implements PlaylistIterator {

    Playlist playlist;
    private int index = 0;

    public SimpleIterator(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public boolean hasNext() {

        return index < this.playlist.getSongs().size();
    }

    @Override
    public String next() {
        return playlist.getSongs().get(index++);
    }
}
