package dailymixes;

import student.TestCase;

/**
 * A test class for PlaylistT called PlaylistTest.
 * 
 * @author Sicheng Fan
 * @version 04/08/2023
 *
 */
public class PlaylistTest extends TestCase {

	private Playlist playlist1;
	private Playlist playlist2;
	private Playlist playlist3;

	/**
	 * Set up for testCase.
	 * 
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		playlist1 = new Playlist("Daily Mix 1", 50, 20, 0, 99, 49, 10, 3);
		playlist2 = new Playlist("Daily Mix 2", 22, 58, 2, 43, 95, 6, 3);
		playlist3 = new Playlist("Daily Mix 3", 20, 2, 50, 30, 14, 100, 3);
	}

	/**
	 * Test for Playlist
	 */
	public void testPlaylist() {
		assertEquals(playlist1.getName(), "Daily Mix 1");
		assertEquals(playlist2.getName(), "Daily Mix 2");
		assertEquals(playlist3.getName(), "Daily Mix 3");
	}

	/**
	 * Test for getMinGenreSet
	 */
	public void testGetMinGenreSet() {
		assertEquals(playlist1.getMinGenreSet(), new GenreSet(50, 20, 0));
		assertEquals(playlist2.getMinGenreSet(), new GenreSet(22, 58, 2));
		assertEquals(playlist3.getMinGenreSet(), new GenreSet(20, 2, 50));
	}

	/**
	 * Test for getMaxGenreSet
	 */
	public void testGetMaxGenreSet() {
		assertEquals(playlist1.getMaxGenreSet(), new GenreSet(99, 49, 10));
		assertEquals(playlist2.getMaxGenreSet(), new GenreSet(43, 95, 6));
		assertEquals(playlist3.getMaxGenreSet(), new GenreSet(30, 14, 100));
	}

	/**
	 * Test for getCapacity
	 */
	public void testGetCapacity() {
		assertEquals(playlist1.getCapacity(), 3);
		assertEquals(playlist2.getCapacity(), 3);
		assertEquals(playlist3.getCapacity(), 3);
	}

	/**
	 * Test for equals
	 */
	public void testEquals() {
		assertTrue(playlist1.equals(playlist1));
		assertTrue(playlist1.equals(new Playlist("Daily Mix 1", 50, 20, 0, 99, 49, 10, 3)));
		assertTrue(playlist2.equals(new Playlist("Daily Mix 2", 22, 58, 2, 43, 95, 6, 3)));
		assertFalse(playlist1.equals(playlist2));
		assertFalse(playlist2.equals(playlist3));
		assertFalse(playlist1.equals(playlist3));
	}

	/**
	 * Test for getNumerOfSongs
	 */
	public void testGetNumberOfSongs() {
		// 50, 20, 0, 99, 49, 10
		Song song1 = new Song("Song1", 51, 25, 5, "Playlist");
		Song song2 = new Song("Song2", 52, 30, 6, "Playlist");
		assertTrue(playlist1.addSong(song1));
		assertEquals(1, playlist1.getNumberOfSongs());
		assertTrue(playlist1.addSong(song2));
		assertEquals(2, playlist1.getNumberOfSongs());

	}

	/**
	 * Test for addSong
	 */
	public void testAddSong() {
		Song song1 = new Song("Song1", 51, 25, 5, "Playlist");
		Song song2 = new Song("Song2", 52, 30, 6, "Playlist");

		assertTrue(playlist1.addSong(song1));
		assertEquals(1, playlist1.getNumberOfSongs());
		assertTrue(playlist1.addSong(song2));
	}

	/**
	 * Test for toString
	 */
	public void testToString() {
		assertEquals(playlist1.tostring(), "Playlist: Daily Mix 1, # of songs: 0, (cap: 3), "
				+ "Requires: Pop:50%-99%,Rock:20%-49%,Country:0%-10%");
		assertEquals(playlist2.tostring(), "Playlist: Daily Mix 2, # of songs: 0, (cap: 3), "
				+ "Requires: Pop:22%-43%,Rock:58%-95%,Country:2%-6%");
		assertEquals(playlist3.tostring(), "Playlist: Daily Mix 3, # of songs: 0, (cap: 3),"
				+ " Requires: Pop:20%-30%,Rock:2%-14%,Country:50%-100%");
	}

	/**
	 * Test for isFull
	 */
	public void testIsFull() {
		// 50, 20, 0, 99, 49, 10
		Song song1 = new Song("Song1", 51, 25, 5, "Playlist");
		Song song2 = new Song("Song2", 52, 30, 6, "Playlist");
		Song song3 = new Song("Song3", 52, 30, 7, "Playlist");

		assertTrue(playlist1.addSong(song1));
		assertFalse(playlist1.isFull());
		assertTrue(playlist1.addSong(song2));
		assertFalse(playlist1.isFull());
		assertTrue(playlist1.addSong(song3));
		assertTrue(playlist1.isFull());
	}

	/**
	 * Test for getSongs
	 */
	public void testGetSongs() {
		assertEquals(3, playlist1.getSongs().length);
		Song song1 = new Song("Song1", 51, 25, 5, "Playlist");
		Song song2 = new Song("Song2", 52, 30, 6, "Playlist");
		Song song3 = new Song("Song3", 52, 30, 7, "Playlist");
		playlist1.addSong(song1);
		assertEquals(playlist1.getSongs()[0], song1);
		playlist1.addSong(song2);
		assertEquals(playlist1.getSongs()[1], song2);
		playlist1.addSong(song3);
		assertEquals(playlist1.getSongs()[2], song3);
	}

	/**
	 * Test for isQualified
	 */
	public void testIsQualified() {
		// 50, 20, 0,
		// 99, 49, 10
		Song qualifiedSong1 = new Song("Song1", 51, 25, 5, "Playlist");
		Song qualifiedSong2 = new Song("Song2", 70, 25, 9, "Playlist");

		Song unQualifiedSong1 = new Song("Song3", 52, 30, 20, "Playlist");

		assertTrue(playlist1.isQualified(qualifiedSong1));
		assertTrue(playlist1.isQualified(qualifiedSong2));
		assertFalse(playlist1.isQualified(unQualifiedSong1));
	}

	/**
	 * Test for getSpacesLeft
	 */
	public void testGetSpacesLeft() {
		Song song = new Song("Song1", 51, 25, 5, "Playlist");

		assertEquals(3, playlist1.getSpacesLeft());
		playlist1.addSong(song);
		assertEquals(2, playlist1.getSpacesLeft());
	}

	/**
	 * Test for compareTo
	 */
	public void testCompareTo() {
		assertTrue(playlist1.compareTo(playlist2) < 0);
		assertFalse(playlist1.compareTo(playlist3) > 0);
		assertTrue(playlist3.compareTo(playlist2) < 0);
		assertEquals(0, playlist1.compareTo(playlist1));
	}
}
