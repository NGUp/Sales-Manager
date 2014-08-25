package Worker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Timer {
	public static String getHour() {
        Calendar cal = new GregorianCalendar();
        return cal.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + cal.get(Calendar.HOUR_OF_DAY) : "" + cal.get(Calendar.HOUR_OF_DAY);
    }

	public static String getMinute() {
        Calendar cal = new GregorianCalendar();
        return cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : "" + cal.get(Calendar.MINUTE);
    }

    public static String getSecond() {
        Calendar cal = new GregorianCalendar();
        return cal.get(Calendar.SECOND) < 10 ? "0" + cal.get(Calendar.SECOND) : "" + cal.get(Calendar.SECOND);
    }

	public static String getDate() {
		 Calendar cal = new GregorianCalendar();
		 return cal.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + cal.get(Calendar.DAY_OF_MONTH) : "" + cal.get(Calendar.DAY_OF_MONTH);
	}

	public static String getMonth() {
		 Calendar cal = new GregorianCalendar();
		 return (1 + cal.get(Calendar.MONTH)) < 10 ? "0" + (1 + cal.get(Calendar.MONTH)) : "" + (1 + cal.get(Calendar.MONTH));
	}

	public static String getYear(){
		Calendar cal = new GregorianCalendar();
		return cal.get(Calendar.YEAR) + "";
	}
	
	public static String getCurrentTime(){
		return getDate() + "/" + getMonth() + "/" + getYear() + " " + getHour() + ":" + getMinute() + ":" + getSecond();
	}
}
