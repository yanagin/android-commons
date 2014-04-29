package orz.yanagin.commons.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class LocationService extends GenericService {

	private LocationManager locationManager;

	private LocationListener locationListener;

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		debug("LocationService Start!", getApplicationContext());

		// LocationManagerの取得
        locationManager = getSystemServiceType(LOCATION_SERVICE);
        if (locationManager == null) {
        	warn("LocationManagerがnullです。");
        	return;
        }

        // ローケーション取得条件の設定
        Criteria criteria = new Criteria();
		criteria.setBearingRequired(false);	// 方位不要
		criteria.setSpeedRequired(false);	// 速度不要
		criteria.setAltitudeRequired(false);	// 高度不要

		// Provider(GPS/WiFi)
		String provider = locationManager.getBestProvider(criteria, true);
        if (provider == null) {
        	warn("Providerがnullです。");
        	return;
        }

        // LocationListenerの生成
        locationListener = new LocationListener() {
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
			}

			@Override
			public void onProviderEnabled(String provider) {
			}

			@Override
			public void onProviderDisabled(String provider) {
			}

			@Override
			public void onLocationChanged(android.location.Location location) {
				debug("位置情報が更新されました lat:" + location.getLatitude() + " lng:" + location.getLongitude(), LocationService.this);
				sendBroadcast(location);
			}
		};

        debug("位置情報の更新を開始します。", getApplicationContext());
		locationManager.requestLocationUpdates(provider, 1 * 1000, 0, locationListener);
	}

	void sendBroadcast(android.location.Location location) {
		try {
			Intent intent = new Intent(getClass().getName());
			intent.putExtra("provider", location.getProvider());
			intent.putExtra("latitude", location.getLatitude());
			intent.putExtra("longitude", location.getLongitude());
			intent.putExtra("time", location.getTime());

			sendBroadcast(intent);
			debug("Send Broadcast Success!", getApplicationContext());
		} catch (RuntimeException e) {
			debug("Send Broadcast Error!", getApplicationContext());
			throw e;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		debug("位置情報の更新を終了します。");
		locationManager.removeUpdates(locationListener);
	}

	abstract public static class LocationChangeBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Location location = new Location(intent.getExtras().getString("provider"));
			location.setLatitude(intent.getExtras().getDouble("latitude"));
			location.setLongitude(intent.getExtras().getDouble("longitude"));
			location.setTime(intent.getExtras().getLong("time"));
			onReceive(context, location);
		}

		abstract public void onReceive(Context context, Location location);

	}

}
