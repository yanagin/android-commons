package orz.yanagin.commons.android;

import android.content.Context;
import android.util.Log;

public class Logger {

	public static void debug(Context context, String message) {
		Log.d(getTag(context), message);
	}

	public static void info(Context context, String message) {
		Log.i(getTag(context), message);
	}

	public static void warn(Context context, String message) {
		Log.w(getTag(context), message);
	}

	public static void error(Context context, String message) {
		Log.e(getTag(context), message);
	}

	public static void error(Context context, String message, Throwable throwable) {
		Log.e(getTag(context), message, throwable);
	}

	static String getTag(Context context) {
		return context.getClass().getName();
	}

}
