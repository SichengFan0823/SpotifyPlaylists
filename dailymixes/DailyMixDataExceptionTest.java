package dailymixes;

/**
 * A test class for DailyMixDataException called DailyMixDataExceptionTest
 * 
 * @author Sicheng Fan
 * @version 04/10/2023
 *
 */
public class DailyMixDataExceptionTest extends student.TestCase {

	/**
	 * test for constructor
	 */
	public void testDailyMixDataException() {
		Exception thrown = null;
		try {
			throw new DailyMixDataException("Message");
		} catch (Exception e) {
			thrown = e;
		}
		assertNotNull(thrown);
		assertEquals(thrown.getClass().getName(), "dailymixes.DailyMixDataException");
		assertEquals(thrown.getMessage(), "Message");
	}

}
