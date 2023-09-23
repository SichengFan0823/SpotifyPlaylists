package dailymixes;

import java.util.Arrays;

import list.AList;

/**
 * The PlaylistCalculator class is responsible for calculating the appropriate
 * playlist for each incoming song and adding it to that playlist if possible.
 * It also handles rejected songs and provides methods for getting the current
 * song queue, the playlists, and the index of a playlist given its name.
 * 
 * @author Sicheng Fan
 * @version 04/01/2023
 */
public class PlaylistCalculator {

	/**
	 * number of playlists
	 */
	public static final int NUM_PLAYLISTS = 3;
	/**
	 * minimum percentage
	 */
	public static final int MIN_PERCENT = 0;
	/**
	 * maximum percentage
	 */
	public static final int MAX_PERCENT = 100;

	private Playlist[] playlists;
	private AList<Song> rejectedTracks;
	private ArrayQueue<Song> songQueue;

	/**
	 * Constructs a new PlaylistCalculator with the given song queue and list of
	 * playlists.
	 * 
	 * @param songQueue the queue of incoming songs
	 * @param playlists the list of playlists to add songs to
	 * @throws IllegalArgumentException if the songQueue is null
	 */
	public PlaylistCalculator(ArrayQueue<Song> songQueue, Playlist[] playlists) {
		if (songQueue == null) {
			throw new IllegalArgumentException("Input ArrayQueue cannot be null.");
		}
		this.songQueue = songQueue;
		this.playlists = playlists;
		this.rejectedTracks = new AList<>();
	}

	/**
	 * Rejects the next song in the song queue, adding it to the list of rejected
	 * tracks.
	 */
	public void reject() {
		Song rejectedSong = songQueue.dequeue();
		if (rejectedSong != null) {
			rejectedTracks.add(rejectedSong);
		}
	}

	/**
	 * Returns the index of the playlist with the given name.
	 * 
	 * @param playlist the name of the playlist to find
	 * @return the index of the playlist with the given name, or -1 if no such
	 *         playlist exists
	 */
	public int getPlaylistIndex(String playlist) {
		for (int i = 0; i < NUM_PLAYLISTS; i++) {
			if (playlists[i].getName().equals(playlist)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Playlist with most room
	 * 
	 * @param nextSong song for which to find playlist with most room
	 * @return playlist with most room
	 */
	private Playlist getPlaylistWithMostRoom(Song nextSong) {
		Playlist[] sortedPlaylists = playlists.clone();
		Arrays.sort(sortedPlaylists);
		AList<Playlist> acceptedPlaylist = new AList<>();
		for (Playlist playlist : sortedPlaylists) {
			if (canAccept(playlist, nextSong)) {
				acceptedPlaylist.add(playlist);
			}
		}
		if (acceptedPlaylist.isEmpty()) {
			return null;
		}

		return acceptedPlaylist.getEntry(0);
	}

	/**
	 * Get playlist for the given nextSong in parameter
	 * 
	 * @param nextSong song to find playlist for
	 * @return playlist for nextSong
	 */
	public Playlist getPlaylistForSong(Song nextSong) {
		if (nextSong == null) {
			return null;
		}
		// Determine the song’s suggested playlist.
		for (Playlist playlist : playlists) {
			if (playlist.getName().equals(nextSong.getPlaylistName()) && canAccept(playlist, nextSong)) {
				// playlist.addSong(nextSong);
				return playlist;
			}
		}
		return getPlaylistWithMostRoom(nextSong);
	}

	/**
	 * 
	 * Attempts to add the next song in the queue to a qualified playlist. If a
	 * playlist is found, the song is added to that playlist and removed from the
	 * queue. Returns true if the song was added to a playlist, false otherwise.
	 * 
	 * @return boolean indicating if the song at the front of the queue was added to
	 *         a playlist
	 */
	public boolean addSongToPlaylist() {
		if (songQueue.isEmpty()) {
			return false;
		}
		Song song = songQueue.getFront();
		Playlist suggestedPlaylist = getPlaylistForSong(song);
		if (suggestedPlaylist != null && suggestedPlaylist.addSong(song)) {
			songQueue.dequeue();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Returns the song queue.
	 * 
	 * @return the ArrayQueue object representing the song queue
	 */
	public ArrayQueue<Song> getQueue() {
		return songQueue;
	}

	/**
	 * 
	 * Determines if a given song can be added to a given playlist by checking the
	 * following conditions: The playlist has enough capacity The song's genre is
	 * allowed in the playlist The song is not already in the playlist Returns true
	 * if the song can be added to the playlist, false otherwise.
	 * 
	 * @param playlist the playlist to check
	 * @param song     the song to check
	 * @return boolean indicating if the song can be added to the playlist
	 */
	private boolean canAccept(Playlist playlist, Song song) {
		// Check if playlist has enough capacity
		if (playlist.isFull()) {
			return false;
		}
		// Check if song's genre is allowed in the playlist
		if (!playlist.isQualified(song)) {
			return false;
		}
		for (int i = 0; i < playlist.getNumberOfSongs(); i++) {
			Song s = playlist.getSongs()[i];
			if (s.equals(song)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Getter for playlists
	 * 
	 * @return playlists
	 */
	public Playlist[] getPlaylists() {
		return playlists;
	}
}
