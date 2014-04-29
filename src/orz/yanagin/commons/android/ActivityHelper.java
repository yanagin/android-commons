package orz.yanagin.commons.android;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public class ActivityHelper {

	@SuppressWarnings("unchecked")
	public static <T> T getSystemService(Context context, String name, T... type) {
		return (T)context.getSystemService(name);
	}

	@SuppressWarnings("unchecked")
	public static <T extends View> T findViewById(Activity activity, int id, T... type) {
		return (T)activity.findViewById(id);
	}

	public static void startActivity(Context context, Class<? extends Activity> type) {
		context.startActivity(new Intent(context, type));
	}

	public static void startService(Context context, Class<? extends Service> type) {
		context.startService(new Intent(context, type));
	}

	public static void stopService(Context context, Class<? extends Service> type) {
		context.stopService(new Intent(context, type));
	}

}
