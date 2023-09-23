package dailymixes;

import student.TestCase;

/**
 * A test class for PlaylistCalculator called PlaylistCalculatorTest.
 * 
 * @author Sicheng Fan
 * @version 04/08/2023
 *
 */
public class PlaylistCalculatorTest extends TestCase {

    private ArrayQueue<Song> songQueue;
    private Playlist[] playlists;
    private PlaylistCalculator calculator;
    private Song song1 = new Song("Song1", 51, 25, 5, "Playlist 1");
    private Song song2 = new Song("Song2", 40, 60, 30, "Playlist 2");
    private Song song3 = new Song("Song3", 25, 10, 70, "Playlist 3");
    private Song unSopportedPlaylistSong = new Song("Song 4", 0, 0, 0,
        "Unsupported Playlist");

    /**
     * Set up for testCase.
     * 
     * @throws Exception
     */
    public void setUp() {
        // Initialize the songQueue, playlists and PlaylistCalculator
        songQueue = new ArrayQueue<>();
        playlists = new Playlist[PlaylistCalculator.NUM_PLAYLISTS];
        // Initialize playlists with dummy data
        playlists[0] = new Playlist("Daily Mix 1", 50, 20, 0, 99, 49, 10, 3);
        playlists[1] = new Playlist("Daily Mix 2", 22, 58, 2, 43, 95, 60, 3);
        playlists[2] = new Playlist("Daily Mix 3", 20, 2, 50, 30, 14, 100, 3);
        calculator = new PlaylistCalculator(songQueue, playlists);
    }


    /**
     * Test for reject
     */
    public void testReject() {
        // Add a song to the queue

        songQueue.enqueue(song1);

        // Call the reject() method
        calculator.reject();

        // Check if the song was removed from the queue and added to the
        // rejectedTracks
        assertTrue(songQueue.isEmpty());
        // You may need to create a method to get rejectedTracks or use
        // reflection to
        // access the private field
    }


    /**
     * test for getPlaylistIndex
     */
    public void testGetPlaylistIndex() {
        assertEquals(0, calculator.getPlaylistIndex("Daily Mix 1"));
        assertEquals(1, calculator.getPlaylistIndex("Daily Mix 2"));
        assertEquals(2, calculator.getPlaylistIndex("Daily Mix 3"));

        // Check the index of a non-existing playlist
        assertEquals(-1, calculator.getPlaylistIndex("Non-Existing Playlist"));
    }


    /**
     * Test for getPlaylistForSong
     */
    public void testGetPlaylistForSong() {

        // Test when input song is null
        assertNull(calculator.getPlaylistForSong(null));

        // Test when a playlist is available for the song
        Playlist expectedPlaylist = playlists[0]; // Assuming the playlist 0 is
                                                  // qualified and not full
        Playlist actualPlaylist = calculator.getPlaylistForSong(song1);
        assertEquals(expectedPlaylist, actualPlaylist);
        assertNull(calculator.getPlaylistForSong(unSopportedPlaylistSong));
    }


    /**
     * Test for addSongToPlaylist
     * 
     */
    public void testAddSongToPlaylist() {
        calculator.getQueue().enqueue(song1);
        calculator.getQueue().enqueue(song2);
        calculator.getQueue().enqueue(song3);

        // Test adding a song to a playlist
        assertTrue(calculator.addSongToPlaylist());
        assertEquals(song1, playlists[0].getSongs()[0]);

        // Test adding another song to a different playlist
        assertTrue(calculator.addSongToPlaylist());
        assertTrue(song2.equals(playlists[1].getSongs()[0]));

        assertTrue(calculator.addSongToPlaylist());
        assertEquals(playlists[2].getNumberOfSongs(), 1);
        assertTrue(song3.equals(playlists[2].getSongs()[0]));
        calculator.getQueue().enqueue(unSopportedPlaylistSong);
        assertFalse(calculator.addSongToPlaylist());
        // Check if the song is still in the queue
        assertEquals(unSopportedPlaylistSong, calculator.getQueue().getFront());
    }


    /**
     * Test for getQueue
     */
    public void testGetQueue() {
        ArrayQueue<Song> queue = calculator.getQueue();
        assertTrue(queue.isEmpty());
        queue.enqueue(song1);
        queue.enqueue(song2);
        queue.enqueue(song3);
        assertEquals(song1, queue.dequeue());
        assertEquals(song2, queue.dequeue());
        assertEquals(song3, queue.dequeue());
    }


    /**
     * Test getPlaylists
     */
    public void testGetPlaylists() {
        // Test the getPlaylists method
        Playlist[] actualPlaylists = calculator.getPlaylists();

        // Test the integrity of the returned playlists
        for (int i = 0; i < actualPlaylists.length; i++) {
            assertEquals(playlists[i], actualPlaylists[i]);
            assertEquals(playlists[i].getName(), actualPlaylists[i].getName());
            assertEquals(playlists[i].getSongs(), actualPlaylists[i]
                .getSongs());
        }
    }

}
