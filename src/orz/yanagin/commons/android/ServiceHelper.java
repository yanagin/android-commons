package orz.yanagin.commons.android;

import android.app.Service;

public class ServiceHelper {

	@SuppressWarnings("unchecked")
	public static <T> T getSystemService(Service service, String name, T... type) {
		return (T)service.getSystemService(name);
	}

}
