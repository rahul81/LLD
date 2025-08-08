package Iterator.Bad;

import java.util.ArrayList;
import java.util.Collections;

public class Paylist {

    private ArrayList<String> songs = new ArrayList<>();

    public void addSongs(String song) {
        songs.add(song);
    }

    public void playPlaylist(String mode) {

        // now if more methods were added for playing it would get harder to maintain
        // also there's no control over playing next or go back.

        if (mode == "simple") {
            for (String song : songs) {
                System.out.println("Playing songs : " + song);
            }
        } else if (mode == "shuffle") {
            System.out.println("Shuffling Songs...");
            ArrayList<String> shuffledSongs = songs;
            Collections.shuffle(shuffledSongs);

            System.out.println("Playing shuffled songs...");
            for (String song : songs) {
                System.out.println("Playing song : " + song);
            }

        }
    }
}
