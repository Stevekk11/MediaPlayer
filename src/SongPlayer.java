import javax.swing.*;
import java.io.File;

public class SongPlayer {
    //variables
    private Playlist playlist;
    private MediaPlayer mediaPlayer;
    private GUI gui;

    /**
     * This method starts the player
     * It creates an array for the song files and initialises the playlist.
     * Also creates the media player object
     */

    public void start() {
        // Get the folder path from the user
        String folderPath = JOptionPane.showInputDialog("Enter the folder path: ");

        // Get a list of files in the folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) {
            throw new RuntimeException("Invalid folder path or empty folder!");
        }

        playlist = new Playlist();

        /*
         Iterate through each file in the folder
         */
        for (File file : files) {
            if (!file.isDirectory()) {
                //Check if it's a playable audio file
                if (isPlayableAudioFile(file)) {
                    Song song = new Song(file);
                    playlist.addSong(song);
                }
            }
        }

        if (playlist.getSize() == 0) {
            throw new RuntimeException("No playable audio files found in the folder!");
        }

        mediaPlayer = new MediaPlayer(playlist);

        /*
        Create and show the GUI and listeners
         */
        SwingUtilities.invokeLater(() -> {
            gui = new GUI(playlist); // Pass the playlist to the GUI constructor to initialize the song list
            gui.setSongInfo(playlist.getCurrentSong().getName(), playlist.getCurrentSong().getDuration());
            gui.addPlayListener(e -> mediaPlayer.play());
            gui.addPauseListener(e -> mediaPlayer.pause());
            gui.addNextListener(e -> {
                mediaPlayer.next();
                gui.setSongInfo(playlist.getCurrentSong().getName(), playlist.getCurrentSong().getDuration());
            });
            gui.addPreviousListener(e -> {
                mediaPlayer.previous();
                gui.setSongInfo(playlist.getCurrentSong().getName(), playlist.getCurrentSong().getDuration());
            });
            gui.addSongListSelectionListener(playlist, mediaPlayer); // Add the song list selection listener
            gui.addCloseListener(e -> System.exit(0));
            mediaPlayer.play();
            gui.setVisible(true);
        });
    }

    /*
    Check if the file is a playable audio file
     */
    private static boolean isPlayableAudioFile(File file) {
        String fileName = file.getName();
        return fileName.endsWith(".wav") || fileName.endsWith(".au") || fileName.endsWith(".aiff");
    }
}