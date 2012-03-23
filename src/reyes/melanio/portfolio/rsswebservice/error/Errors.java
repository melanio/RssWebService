package reyes.melanio.portfolio.rsswebservice.error;

public class Errors {

	private Errors() {

	}

	public static final int INVALID_URL = 1;
	public static final int DATABASE_INSERT_ERROR = 2;
	public static final int DATABASE_DELETE_ERROR = 3;
	public static final int DATABASE_UPDATE_ERROR = 4;
	public static final int REQUESTED_FEED_NOT_FOUND = 5;
	public static final int FAILED_TO_RETRIEVE_FEEDITEMS = 6;
	public static final int NO_SUITABLE_PARSER_FOUND = 7;

	public static final String INVALID_URL_TEXT = "Invalid URL";
	public static final String DATABASE_INSERT_ERROR_TEXT = "There was a problem inserting the record into the database.";
	public static final String DATABASE_DELETE_ERROR_TEXT = "There was a problem deleting the record into the database";
	public static final String DATABASE_UPDATE_ERROR_TEXT = "There was a problme update the record into the database";
	public static final String REQUESTED_FEED_NOT_FOUND_TEXT = "The requested feed was not found in the database";
	public static final String FAILED_TO_RETRIEVE_FEEDITEMS_TEXT = "There was a failure when trying to retrieve items from feed";
	public static final String NO_SUITABLE_PARSER_FOUND_TEXT = "No suiteable parser found for the requested feed's format.";
}
