package dailymixes;

/**
 * A class called GenreSet that implements Comparable<GenreSet>.
 * 
 * GenreSet represents a set of music genres, including rock, pop, and country.
 * 
 * It provides methods for retrieving and comparing these genres.
 * 
 * @author Sicheng Fan
 * @version 03/30/2023
 */
public class GenreSet implements Comparable<GenreSet> {

    private int pop;
    private int rock;
    private int country;

    /**
     * the constructor for GenreSet.
     * 
     * @param pop
     *            pop style of music
     * @param rock
     *            rock style of music
     * @param country
     *            country style of music
     */
    public GenreSet(int pop, int rock, int country) {
        this.pop = pop;
        this.rock = rock;
        this.country = country;
    }


    /**
     * Getter for rock
     * 
     * @return rock
     */
    public int getRock() {
        return rock;
    }


    /**
     * Getter for pop
     * 
     * @return pop
     */
    public int getPop() {
        return pop;
    }


    /**
     * String representation of GenreSet
     */
    @Override
    public String toString() {
        return "Pop:" + pop + " Rock:" + rock + " Country:" + country;
    }


    /**
     * 
     * Compares the current GenreSet object to another GenreSet object for
     * equality.
     * 
     * @param obj
     *            the object to compare to
     * @return true if the objects are equal, false otherwise
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
        final GenreSet other = (GenreSet)obj;

        if (this.pop != other.pop) {
            return false;
        }
        if (this.rock != other.rock) {
            return false;
        }
        return this.country == other.country;
    }


    /**
     * 
     * Determines whether the given GenreSet is within the range of another
     * GenreSet.
     * 
     * @param set1
     *            the GenreSet to check
     * @param set2
     *            the GenreSet to compare to
     * @return true if the given GenreSet is within the range of the other
     *         GenreSet,
     *         false otherwise
     */
    public boolean isWithinRange(GenreSet set1, GenreSet set2) {
        if (set1 == null || set2 == null) {
            return false;
        }
        return set1.isLessThanOrEqualTo(this) && this.isLessThanOrEqualTo(set2);
    }


    /**
     * 
     * Determines whether the current GenreSet object is less than or equal to
     * another GenreSet object.
     * 
     * @param other
     *            the GenreSet object to compare to
     * @return true if the current GenreSet object is less than or equal to the
     *         other GenreSet object, false otherwise
     */
    private boolean isLessThanOrEqualTo(GenreSet other) {
        if (other == null) {
            return false;
        }
        return this.pop <= other.pop && this.rock <= other.rock
            && this.country <= other.country;
    }


    /**
     * Getter for country
     * 
     * @return country
     */
    public int getCountry() {
        return country;
    }


    /**
     * compare the current object with another object of the same type. It
     * returns a
     * negative integer, zero, or a positive integer depending on whether the
     * current object sum of pop, rock and country is less than, equal to, or
     * greater than the other object sum
     * 
     * @param other
     * @return negative integer, zero, or a positive integer depending on
     *         whether
     *         the current object is less than, equal to, or greater than the
     *         sum of
     *         other object pop, rock and country
     */
    @Override
    public int compareTo(GenreSet other) {
        return this.pop + this.rock + this.country - other.pop - other.rock
            - other.country;
    }
}
