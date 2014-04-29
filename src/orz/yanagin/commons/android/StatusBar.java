package orz.yanagin.commons.android;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StatusBar {

	private final Context context;

	private final int icon;

	private final Class<? extends Activity> type;

	private final CharSequence title;

	private final CharSequence text;

	private final String tag;

	private final int id;

	private final NotificationManager notificationManager;

	public StatusBar(Context context, int icon, Class<? extends Activity> type,
			CharSequence title, CharSequence text) {
		this.context = context;
		this.icon = icon;
		this.type = type;
		this.title = title;
		this.text = text;

		tag = getTag();
		id = getRandomInt();

		notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	String getTag() {
		return getClass().getName() + "_" + context.getClass().getName();
	}

	int getRandomInt() {
		return (int)(Math.random() * 10000);
	}

	public void add() {
		Notification notification = createStatusbarNotification(icon, type,
				title, text);

		Toast.makeText(context, "Add notification tag:" + tag + " id:" + id,
				Toast.LENGTH_SHORT).show();

		notificationManager.notify(tag, icon, notification);
	}

	public void remove() {
		Toast.makeText(context, "Remove notification tag:" + tag + " id:" +
				id, Toast.LENGTH_SHORT).show();

		//           notificationManager.cancel(tag, id);
		notificationManager.cancelAll();        // こうしないと消えない
	}

	Notification createStatusbarNotification(int icon, Class<?> type,
			CharSequence contentTitle, CharSequence contentText) {
		Notification notification = new Notification(
				icon,
				"",
				System.currentTimeMillis()
				);

		PendingIntent pendingIntent = PendingIntent.getActivity(
				context,
				0,
				new Intent(context, type),
				0
				);
		notification.setLatestEventInfo(
				context,
				contentTitle,
				contentText,
				pendingIntent
				);

		notification.flags = notification.flags
				| Notification.FLAG_NO_CLEAR         // クリアボタンを表示しない ※ユーザがクリアできない
				| Notification.FLAG_ONGOING_EVENT;   // 継続的イベント領域に表示 ※「実行中」領域
		notification.number = 0;

		return notification;
	}

}
