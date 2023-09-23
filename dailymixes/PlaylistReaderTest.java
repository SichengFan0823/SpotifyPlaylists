package dailymixes;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * A test class for PlaylistReader called PlaylistReaderTest.
 * 
 * @author Sicheng Fan
 * @version 04/08/2023
 *
 */
public class PlaylistReaderTest extends student.TestCase {

	/***
	 * test fpr constructor
	 */
	public void testPlaylistReader() {
		PlaylistReader playlistReader;

		Exception thrown = null;
		try {
			playlistReader = new PlaylistReader("", "");
		} catch (FileNotFoundException | ParseException | DailyMixDataException e) {
			thrown = e;
		}
		assertEquals(FileNotFoundException.class.toString(), thrown.getClass().toString());
	}

}
