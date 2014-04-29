package orz.yanagin.commons.android;

import android.app.Service;
import android.content.Context;

public abstract class GenericService extends Service {

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
		return ServiceHelper.getSystemService(this, name);
	}

}
