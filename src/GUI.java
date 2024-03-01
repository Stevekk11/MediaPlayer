import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    //variables
    private JLabel songInfoLabel;
    private JProgressBar progressBar;
    private JButton playButton;
    private JButton pauseButton;
    private JButton nextButton;
    private JButton prevButton;
    private JList<String> songList;
    private DefaultListModel<String> listModel;
    private JButton closeButton;

    /**
     * Constructor for the GUI of the player.
     *
     * @param playlist the list of songs
     */
    public GUI(Playlist playlist) {
        //title, size etc...
        setTitle("Java Media Player");
        setSize(800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //the buttons
        songInfoLabel = new JLabel();
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        closeButton = new JButton("Exit");

        listModel = new DefaultListModel<>();
        progressBar = new JProgressBar();
        for (Song song : playlist.getSongs()) {
            listModel.addElement(song.getName());
        }
        songList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(songList);
        //create a new JPanel object with the buttons and more.
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(closeButton);

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(songInfoLabel, BorderLayout.NORTH);
        controlPanel.add(buttonPanel, BorderLayout.CENTER);
        controlPanel.add(progressBar, BorderLayout.SOUTH);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, controlPanel);
        splitPane.setResizeWeight(0.5);
        add(splitPane);
    }

    /**
     * Used to set the name and duration of the song.
     *
     * @param name     name
     * @param duration duration in min
     */
    public void setSongInfo(String name, String duration) {
        songInfoLabel.setText("     Now Playing: "+ name +" "+duration);
    }

    /**
     * Adds the buttons
     *
     * @param listener listener
     */
    public void addPlayListener(ActionListener listener) {
        playButton.addActionListener(listener);
    }

    public void addPauseListener(ActionListener listener) {
        pauseButton.addActionListener(listener);
    }

    public void addNextListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    public void addPreviousListener(ActionListener listener) {
        prevButton.addActionListener(listener);
    }

    public void addCloseListener(ActionListener listener) {
        closeButton.addActionListener(listener);
    }

    /**
     * This method adds the song selection scroll pane.
     *
     * @param playlist    the playlist
     * @param mediaPlayer the object
     */
    public void addSongListSelectionListener(Playlist playlist, MediaPlayer mediaPlayer) {
        songList.addListSelectionListener(e -> {
            int selectedIndex = songList.getSelectedIndex();
            if (selectedIndex != -1) {
                playlist.setCurrentIndex(selectedIndex);
                mediaPlayer.pause();
                mediaPlayer.play();
                setSongInfo(playlist.getCurrentSong().getName(), playlist.getCurrentSong().getDuration());
            }
        });
    }
}