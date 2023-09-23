package dailymixes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * PlaylistReader class
 * 
 * @author Sicheng Fan
 * @version 04/08/2023
 *
 */
public class PlaylistReader {

	private ArrayQueue<Song> queue;
	private Playlist[] playlists;

	/**
	 * 
	 * @param songsFileName     the name of the input file containing the song data
	 * @param playlistsFileName the name of the input file containing the playlist
	 *                          data
	 * @throws ParseException        if there is an error in the input data
	 * @throws DailyMixDataException if there is an error in the input data
	 * @throws FileNotFoundException if the input files are not found
	 */
	public PlaylistReader(String songsFileName, String playlistsFileName)
			throws ParseException, DailyMixDataException, FileNotFoundException {
		this.queue = readQueueFile(songsFileName);
		this.playlists = readPlaylistFile(playlistsFileName);
		PlaylistCalculator calculator = new PlaylistCalculator(queue, playlists);
		new PlaylistWindow(calculator);
	}

	/**
	 * This function returns whether or not all of the integers it is passed (num1,
	 * num2, and num3) are between the minimum(0) and maximum(100) possible values
	 * for a genre's percent composition
	 * 
	 * @param num1 first number
	 * @param num2 second number
	 * @param num3 third number
	 * @return true if (num1, num2, and num3) are between the minimum(0) and
	 *         maximum(100) false otherwise
	 */
	private boolean isInValidPercentRange(int num1, int num2, int num3) {
		return num1 >= PlaylistCalculator.MIN_PERCENT && num1 <= PlaylistCalculator.MAX_PERCENT
				&& num2 >= PlaylistCalculator.MIN_PERCENT && num2 <= PlaylistCalculator.MAX_PERCENT
				&& num3 >= PlaylistCalculator.MIN_PERCENT && num3 <= PlaylistCalculator.MAX_PERCENT;
	}

	/**
	 * Returns Playlist array form playlist input file
	 * 
	 * @param playlistsFileName the name of the input file containing the playlist
	 *                          data
	 * @return
	 * @throws ParseException        if there is an error in the input data
	 * @throws DailyMixDataException if there is an error in the input data
	 * @throws FileNotFoundException if the input files are not found
	 */
	private Playlist[] readPlaylistFile(String playlistsFileName)
			throws ParseException, DailyMixDataException, FileNotFoundException {
		Playlist[] _playlists = new Playlist[3];
		Scanner scanner = new Scanner(new File(playlistsFileName));
		int playlistIndex = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(",");
			if (tokens.length != 8) {
				throw new ParseException("Invalid number of parameter", tokens.length);

			}
			String name = tokens[0].trim();
			try {
				int minPop = Integer.parseInt(tokens[1].trim());
				int minRock = Integer.parseInt(tokens[2].trim());
				int minCountry = Integer.parseInt(tokens[3].trim());
				int maxPop = Integer.parseInt(tokens[4].trim());
				int maxRock = Integer.parseInt(tokens[5].trim());
				int maxCountry = Integer.parseInt(tokens[6].trim());
				int capacity = Integer.parseInt(tokens[7].trim());

				if (!isInValidPercentRange(minPop, minRock, minCountry)) {
					throw new DailyMixDataException("Invalid percentage composition: " + line);
				}
				if (!isInValidPercentRange(maxPop, maxRock, maxCountry)) {
					throw new DailyMixDataException("Invalid percentage composition: " + line);
				}
				_playlists[playlistIndex] = new Playlist(name, minPop, minRock, minCountry, maxPop, maxRock, maxCountry,
						capacity);
				playlistIndex++;
				if (playlistIndex == 3) {
					break;
				}
			} catch (Exception e) {
				throw new ParseException(e.getMessage(), 0);
			}

		}
		scanner.close();
		if (playlistIndex < 3) {
			throw new DailyMixDataException("Invalid number of playlists: " + playlistIndex);
		}
		return _playlists;
	}

	/**
	 * Populates the song queue from the songs input file.
	 * 
	 * @param songsFileName the name of the input file containing the song data
	 * @return ArrayQueue of songs
	 * @throws ParseException        if there is an error in the input data
	 * @throws FileNotFoundException if the input file is not found
	 */
	private ArrayQueue<Song> readQueueFile(String songsFileName) throws ParseException, FileNotFoundException {
		ArrayQueue<Song> queue = new ArrayQueue<>();
		Scanner scanner = new Scanner(new File(songsFileName));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(",");
			if (tokens.length <= 3 || tokens.length > 5) {
				throw new ParseException("Invalid input format: " + line, 0);
			}
			String name = tokens[0].trim();
			try {
				int pop = Integer.parseInt(tokens[1].trim());
				int rock = Integer.parseInt(tokens[2].trim());
				int country = Integer.parseInt(tokens[3].trim());
				if (!isInValidPercentRange(pop, rock, country)) {
					throw new DailyMixDataException("Invalid percentage composition: " + line);
				}
				String suggestedPlaylist = "";
				if (tokens.length > 4) {
					suggestedPlaylist = tokens[4].trim();
				}
				Song song = new Song(name, pop, rock, country, suggestedPlaylist);
				queue.enqueue(song);
			} catch (Exception e) {
				throw new ParseException(e.getMessage(), 0);
			}

		}
		scanner.close();
		return queue;
	}

}
