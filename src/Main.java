public class Main {
    public static void main(String[] args) {
        /*
        Runs the media player.
         */
        try {
            SongPlayer songPlayer = new SongPlayer();
            songPlayer.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
