package dailymixes;

/**
 * A class called Song.
 * 
 * @author Sicheng Fan
 * @version 03/30/2023
 */
public class Song {

    private String name;
    private String suggestedPlaylist;
    private GenreSet genreSet;

    /**
     * Constructor with all parameters
     * 
     * @param name
     *            name for the song
     * @param pop
     *            pop style of music
     * @param rock
     *            rock style of music
     * @param country
     *            country style of music
     * @param suggestedPlaylist
     *            suggested play list.
     */
    public Song(
        String name,
        int pop,
        int rock,
        int country,
        String suggestedPlaylist) {
        this.name = name;
        this.suggestedPlaylist = suggestedPlaylist;
        this.genreSet = new GenreSet(pop, rock, country);
    }


    /**
     * String representation of Song
     * 
     * @return String representation of Son
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        sb.append(" ");
        sb.append(genreSet.toString());
        if (suggestedPlaylist != null && suggestedPlaylist.length() > 0) {
            sb.append(" Suggested:" + suggestedPlaylist);
        }
        return sb.toString();
    }


    /**
     * Getter for suggestedPlaylist
     * 
     * @return suggestedPlaylist
     */
    public String getPlaylistName() {
        return suggestedPlaylist;
    }


    /**
     * Equals implementation
     * 
     * @return true if given object is equal to the current object
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
        final Song other = (Song)obj;
        if (!this.name.equals(other.name)) {
            return false;
        }
        if (!this.suggestedPlaylist.equals(other.suggestedPlaylist)) {
            return false;
        }
        return this.genreSet.equals(other.genreSet);
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
     * Getter for genreSet
     * 
     * @return genreSet
     */
    public GenreSet getGenreSet() {
        return genreSet;
    }
}
