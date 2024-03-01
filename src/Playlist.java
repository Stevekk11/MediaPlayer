import java.util.ArrayList;
import java.util.List;

public class Playlist {
    //variables, list
    private List<Song> songs;
    private int currentIndex;

    /**
     * constructor
     */
    public Playlist() {
        this.songs = new ArrayList<>();
        this.currentIndex = 0;
    }

    /**
     * Methods for adding a song, getters, setters..
     *
     * @param song the song
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    public int getSize() {
        return songs.size();
    }

    public Song getCurrentSong() {
        return songs.get(currentIndex);
    }

    public void setCurrentIndex(int index) {
        currentIndex = index;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}