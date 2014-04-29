package orz.yanagin.commons.android;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;

public abstract class GenericActivity extends Activity {

	protected void debug(String message, Context... contexts) {
		Logger.debug(this, message);
	}

	protected void info(String message) {
		Logger.info(this, message);
	}

	protected void warn(String message) {
		Logger.warn(this, message);
	}

	protected void error(String message) {
		Logger.error(this, message);
	}

	protected void error(String message, Throwable throwable) {
		Logger.error(this, message, throwable);
	}

	@SuppressWarnings("unchecked")
	public <T> T getSystemServiceType(String name, T... type) {
		return ActivityHelper.getSystemService(this, name);
	}

	public <T extends View> T findViewByIdType(int id, T... type) {
		return ActivityHelper.findViewById(this, id, type);
	}

	protected void startActivity(Class<? extends Activity> type) {
		ActivityHelper.startActivity(this, type);
	}

	protected void startService(Class<? extends Service> type) {
		ActivityHelper.startService(this, type);
	}

	protected void stopService(Class<? extends Service> type) {
		ActivityHelper.stopService(this, type);
	}

}
