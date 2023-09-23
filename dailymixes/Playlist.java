package dailymixes;

/**
 * The Playlist class represents a playlist that contains a list of songs. The
 * class implements the Comparable interface, allowing for comparison of
 * playlists based on capacity, spaces left, minGenreSet, maxGenreSet and name.
 * 
 * It provides methods for retrieving and comparing these genres.
 * 
 * @author Sicheng Fan
 * @version 04/01/2023
 */
public class Playlist implements Comparable<Playlist> {

	private GenreSet minGenreSet;
	private GenreSet maxGenreSet;
	private Song[] songs;
	private int capacity;
	private int numberOfSong;
	private String name;

	/**
	 * 
	 * @param playlistName playlist name
	 * @param minPop       minimum pop
	 * @param minRock      minimum rock
	 * @param minCountry   minimum country
	 * @param maxPop       maximum pop
	 * @param maxRock      maximum rock
	 * @param maxCountry   max country
	 * @param playlistCap  playlist capacity
	 */
	public Playlist(String playlistName, int minPop, int minRock, int minCountry, int maxPop, int maxRock,
			int maxCountry, int playlistCap) {
		this.name = playlistName;
		this.minGenreSet = new GenreSet(minPop, minRock, minCountry);
		this.maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
		this.capacity = playlistCap;
		this.songs = new Song[playlistCap];
	}

	/**
	 * Getter for minGenreSet
	 * 
	 * @return minGenreSet
	 */
	public GenreSet getMinGenreSet() {
		return minGenreSet;
	}

	/**
	 * Calculates the space left in the playlist
	 * 
	 * @return capacity - numberOfSong
	 */
	public int getSpacesLeft() {
		return capacity - numberOfSong;
	}

	/**
	 * Getter for maxGenreSet
	 * 
	 * @return maxGenreSet
	 */
	public GenreSet getMaxGenreSet() {
		return maxGenreSet;
	}

	/**
	 * Compares this playlist to another playlist based on capacity, spaces left,
	 * minGenreSet, maxGenreSet and name.
	 * 
	 * @param other the other playlist to compare to
	 * @return a negative integer, zero, or a positive integer as this playlist is
	 *         less than, equal to, or greater than the specified playlist
	 */
	@Override
	public int compareTo(Playlist other) {
		int value = Integer.compare(this.capacity, other.capacity);
		if (value != 0) {
			return value;
		}

		// playlists have equal capacities, order based on spaces left
		int thisSpacesLeft = this.getSpacesLeft();
		int otherSpacesLeft = other.getSpacesLeft();
		value = Integer.compare(otherSpacesLeft, thisSpacesLeft);
		if (value != 0) {
			return value;
		}

		// playlists have equal capacity and spaces left, order based on
		// MinGenreSet
		value = this.minGenreSet.compareTo(other.minGenreSet);
		if (value != 0) {
			return value;
		}

		// playlists have equal capacity, spaces left, and MinGenreSet, order
		// based on MaxGenreSet
		value = this.maxGenreSet.compareTo(other.maxGenreSet);
		if (value != 0) {
			return value;
		}

		// playlists have all the same attributes, order based on name
		return this.name.compareTo(other.name);
	}

	/**
	 * Getter for numberOfSong
	 * 
	 * @return numberOfSong
	 */
	public int getNumberOfSongs() {
		return numberOfSong;
	}

	/**
	 * Adds song to playlist
	 * 
	 * @param newSong song to be added
	 * @return true of newSong is added, false otherwise
	 */
	public boolean addSong(Song newSong) {
		// Check if there is room available for this song
		if (isFull()) {
			return false; // Playlist is full
		}

		// Check if the song is qualified to be added to the playlist
		if (!isQualified(newSong)) {
			return false; // Song does not meet genre requirements
		}

		// Add the song to the playlist
		songs[numberOfSong] = newSong;
		numberOfSong++;

		return true;
	}

	/**
	 * String representation of Playlist
	 * 
	 * @return String representation of Playlist
	 */
	public String tostring() {
		return "Playlist: " + name + ", # of songs: " + numberOfSong + ", (cap: " + capacity + "), Requires: " + "Pop:"
				+ minGenreSet.getPop() + "%-" + maxGenreSet.getPop() + "%," + "Rock:" + minGenreSet.getRock() + "%-"
				+ maxGenreSet.getRock() + "%," + "Country:" + minGenreSet.getCountry() + "%-" + maxGenreSet.getCountry()
				+ "%";
	}

	/**
	 * Check if playlist is full
	 * 
	 * @return true if the capacity of the playlist is reached, false otherwise
	 */
	public boolean isFull() {
		return capacity == numberOfSong;
	}

	/**
	 * Check of current object is equals to the given object in parameter
	 * 
	 * @param obj object to be compared to
	 * @return true of obj is equal to the current object, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Playlist other = (Playlist) obj;
		if (this.capacity != other.capacity) {
			return false;
		}
		if (this.numberOfSong != other.numberOfSong) {
			return false;
		}
		if (!this.name.equals(other.name)) {
			return false;
		}
		if (!this.minGenreSet.equals(other.getMinGenreSet())) {
			return false;
		}
		if (!this.maxGenreSet.equals(other.getMaxGenreSet())) {
			return false;
		}
		if (this.getNumberOfSongs() != other.getNumberOfSongs()) {
			return false;
		}
		if (this.songs.length > 0) {
			for (int i = 0; i < this.getNumberOfSongs(); i++) {
				if (this.songs[i] == null) {
					return false;
				}
				if (!this.songs[i].equals(other.getSongs()[i])) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Getter for songs
	 * 
	 * @return songs
	 */
	public Song[] getSongs() {
		return songs;
	}

	/**
	 * Getter for capacity
	 * 
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Getter for name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * 
	 * @param newName new name for the playlist
	 * 
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Returns true if the given song is qualified to be added to the playlist,
	 * false otherwise.
	 *
	 * @param possibleSong the song to check
	 * @return true if the given song is qualified to be added to the playlist,
	 *         false otherwise
	 */
	public boolean isQualified(Song possibleSong) {
		GenreSet songGenres = possibleSong.getGenreSet();

		// Check if the song's genres are within the allowed range for this
		// playlist
		return songGenres.isWithinRange(minGenreSet, maxGenreSet);

	}

}
