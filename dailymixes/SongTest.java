package dailymixes;

import student.TestCase;

/**
 * A test class for Song called SongTest.
 * 
 * @author Sicheng Fan
 * @version 03/30/2023
 *
 */
public class SongTest extends TestCase {
    private Song song;
    private Song song1;
    private Song song2;
    private Song songA;
    private Song songB;
    private Song songC;

    /**
     * Set up for testCase.
     */
    public void setUp() {
        song = new Song("Smells Like Teen Spirit", 90, 10, 0, "Daily Mix 2");
        song1 = new Song("Dancing Queen", 10, 90, 0, "Disco Fever");
        song2 = new Song("Dancing Queen", 10, 90, 0, null);
        songA = new Song("Take Me Home Country Roads", 23, 30, 0, "Party");
        songB = new Song("Take Me Home Country Roads", 23, 30, 0, "Party");
        songC = new Song("Can't Stop the Feeling!", 50, 50, 0, "Pop Hits");

    }


    /**
     * test getName method.
     */
    public void testGetName() {
        assertEquals("Smells Like Teen Spirit", song.getName());
    }


    /**
     * Test getGenreSet method.
     */
    public void testGetGenreSet() {
        GenreSet genreSet = new GenreSet(23, 30, 0);
        assertEquals(genreSet, songB.getGenreSet());
    }


    /**
     * Test toString method.
     */
    public void testToString() {
        assertEquals(
            "Dancing Queen Pop:10 Rock:90 Country:0 Suggested:Disco Fever",
            song1.toString());
        assertEquals("Dancing Queen Pop:10 Rock:90 Country:0", song2
            .toString());

    }


    /**
     * Test equals method.
     */
    public void testEquals() {
        assertTrue(songA.equals(songB));
        assertFalse(songA.equals(songC));
    }
}
