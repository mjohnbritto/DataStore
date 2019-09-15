package com.datastore.main.utils;

/**
 * @author John Britto
 *
 *
 *         Contains the constants
 */
public class Constants {
	public static final String defaultDataStoreLoc = "C:\\Users\\Public\\Documents";
	public static final int MILLISECONDS = 1000;
	public static final int KEY_MAX_LENGTH = 32;

	// Messages
	public static final String FAILURE_KEY_LENGTH_EXCEEDED = "Operation failed due to key length exceeded the limit(32chars)";
	public static final String FAILURE_KEY_ALREADY_AVAILABLE = "Operation failed due to key already available";
	public static final String FAILURE_KEY_NOT_AVAILABLE = "Operation failed due to key not available";
	public static final String SUCCESS_CREATE = "Create operation successful";
	public static final String FAILURE_CREATE = "Create operation failed";
	public static final String FAILURE_READ = "Rread operation failed due to unknown error, please try again later!";
	public static final String SUCCESS_DELETE = "Record deletion successful";
	public static final String FAILURE_DELETE = "Record deletion failed";
}
