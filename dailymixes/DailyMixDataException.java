package dailymixes;

/**
 * 
 * This exception is thrown when an error occurs while processing DailyMix data.
 * 
 * @author Sicheng Fan
 * @version 04/01/2023
 */
public class DailyMixDataException extends Exception {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     * Constructs a new DailyMixDataException with the specified detail message.
     * 
     * @param message
     *            the detail message of the exception
     */
    public DailyMixDataException(String message) {
        super(message);
    }

}
