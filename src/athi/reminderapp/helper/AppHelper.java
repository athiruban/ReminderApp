package athi.reminderapp.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppHelper {
	public static boolean isValidDate(String datetocheck){
		/*
		 * SimpleDateFormat is a heavy weight object
		 * need to replace this with a new method...
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		try{
			sdf.parse(datetocheck);
			return true;
		}
		catch (ParseException pe){
			return false;
		}
	}
	
	public static boolean isValidTime(String timetocheck){
		final String TIME_24HR_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
		Pattern pattern = Pattern.compile(TIME_24HR_PATTERN);
		Matcher matcher = pattern.matcher(timetocheck);
		return matcher.matches();
	}
}
