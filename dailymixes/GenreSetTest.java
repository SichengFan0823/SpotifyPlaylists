package dailymixes;

import student.TestCase;

/**
 * A test class for GenreSet called GenreSetTest.
 * 
 * @author Sicheng Fan
 * @version 03/30/2023
 *
 */
public class GenreSetTest extends TestCase {
    private GenreSet genreSet1;
    private GenreSet genreSet2;
    private GenreSet genreSet3;
    private GenreSet genreSet4;

    /**
     * Set up for testCase.
     */
    public void setUp() {
        genreSet1 = new GenreSet(50, 30, 20);
        genreSet2 = new GenreSet(50, 30, 20);
        genreSet3 = new GenreSet(30, 50, 20);
        genreSet4 = new GenreSet(60, 40, 30);

    }


    /**
     * Test toString method.
     */
    public void testToString() {
        assertEquals("Pop:50 Rock:30 Country:20", genreSet1.toString());
    }


    /**
     * Test equals method.
     */
    public void testEquals() {
        assertTrue(genreSet1.equals(genreSet2));
        assertFalse(genreSet1.equals(genreSet3));
    }


    /**
     * test isWithinRange method.
     */
    public void testIsWithinRange() {
        assertTrue(genreSet1.isWithinRange(genreSet1, genreSet2));
        assertFalse(genreSet1.isWithinRange(genreSet2, genreSet3));
    }


    /**
     * Test compareTo method.
     */
    public void testCompareTo() {

        assertTrue(genreSet1.compareTo(genreSet4) < 0);
        assertTrue(genreSet4.compareTo(genreSet1) > 0);
        assertEquals(0, genreSet1.compareTo(genreSet2));
    }
}
