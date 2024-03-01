import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MediaPlayer {
    private Playlist playlist;
    private Clip clip;

    /**
     * constructor
     *
     * @param playlist the list of songs
     */
    public MediaPlayer(Playlist playlist) {
        this.playlist = playlist;
    }

    /**
     * This method is used to play the audio clip using AudioInputStream
     * exception handling.
     */
    public void play() {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }

            File file = playlist.getCurrentSong().getFile();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to pause/stop the music
     */
    public void pause() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Methods to start the next song / previous song
     */
    public void next() {
        int currentIndex = playlist.getCurrentIndex();
        currentIndex++;
        if (currentIndex >= playlist.getSize()) {
            currentIndex = 0;
        }
        playlist.setCurrentIndex(currentIndex);
        play();
    }

    public void previous() {
        int currentIndex = playlist.getCurrentIndex();
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = playlist.getSize() - 1;
        }
        playlist.setCurrentIndex(currentIndex);
        play();
    }
}