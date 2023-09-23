package dailymixes;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * A projectRunner class(has a main method).
 * 
 * @author Sicheng Fan
 * @version 04/08/2023
 */
public class ProjectRunner {

    public static void main(String[] args) throws FileNotFoundException, ParseException, DailyMixDataException {
        String songsFileName = "input.txt";
        String playlistsFileName = "playlists.txt";

        if (args.length >= 2) {
            songsFileName = args[0];
            playlistsFileName = args[1];
        }
        PlaylistReader pr = new PlaylistReader(songsFileName,
                playlistsFileName);
    }
}
