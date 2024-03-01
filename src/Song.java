import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Song {
    //variables
    private File file;
    private String name;
    private String duration;

    /**
     * constructor
     *
     * @param file the song file
     */
    public Song(File file) {
        this.file = file;
        this.name = file.getName();
        this.duration = calculateDuration();
    }

    /**
     * Special method used to calculate the duration of the song.
     *
     * @return the duration of the song in min
     */
    private String calculateDuration() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            AudioFileFormat format = AudioSystem.getAudioFileFormat(file);
            long durationMicroseconds = (long) (format.getFrameLength() * 1_000_000L / format.getFormat().getFrameRate());
            long seconds = durationMicroseconds / 1_000_000L;
            long minutes = seconds / 60;
            seconds %= 60;
            audioInputStream.close();
            return String.format("%02d:%02d", minutes, seconds);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    //getters, setters..
    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }
}