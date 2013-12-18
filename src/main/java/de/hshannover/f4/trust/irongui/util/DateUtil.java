package de.hshannover.f4.trust.irongui.util;




import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Simple util class for date transformations
 * 
 */
public class DateUtil {

	private static SimpleDateFormat mDateFormatDump = new SimpleDateFormat(
			"dd-MM-yyyy HH:mm:ss");
	private static Calendar mCalendar = Calendar.getInstance();

	public static String getFormattedDateFromTimestamp(long timestamp) {
		mCalendar.setTimeInMillis(timestamp);
		return mDateFormatDump.format(mCalendar.getTime());
	}
	
	public static String getFormattedDateFromTimestamp(String timestamp) {
		try{
			long time = Long.parseLong(timestamp);
			return getFormattedDateFromTimestamp(time);
		}
		catch (NumberFormatException e) {
			return timestamp;
		}		
	}
}
