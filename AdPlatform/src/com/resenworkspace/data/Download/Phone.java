package com.resenworkspace.data.Download;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Locale;
import java.util.UUID;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class Phone {
  
	 public static final String MAC     = "mac";
	 public static final String IP      = "ip";
	 public static final String FBL     = "fbl";
	 public static final String CPU     = "cpu";
	 public static final String HARD    = "hard";
	 public static final String MEMORY  = "memory";
	 
	 
	 private static final String PREFS_DEVICE_ID = "device_id";
	 
	 public static boolean isNetworkAvailable(Context ctx) {
			int networkStatePermission = ctx
					.checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE);
			if (networkStatePermission == PackageManager.PERMISSION_GRANTED) {
				ConnectivityManager mConnectivity = (ConnectivityManager) ctx
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				// Skip if no connection, or background data disabled
				NetworkInfo info = mConnectivity.getActiveNetworkInfo();
				if (info == null) {
					return false;
				}
				// Only update if WiFi
				int netType = info.getType();
				// int netSubtype = info.getSubtype();
				if ((netType == ConnectivityManager.TYPE_WIFI)
						|| (netType == ConnectivityManager.TYPE_MOBILE)) {
					return info.isConnected();
				} else {
					return false;
				}
			} else {
				return true;
			}
		}

		public static String getConnectionType(Context context) {
			int networkStatePermission = context
					.checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE);
			if (networkStatePermission == PackageManager.PERMISSION_GRANTED) {
				ConnectivityManager cm = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = cm.getActiveNetworkInfo();
				if (info == null) {
					return Const.CONNECTION_TYPE_UNKNOWN;
				}
				int netType = info.getType();
				int netSubtype = info.getSubtype();
				if (netType == ConnectivityManager.TYPE_WIFI) {
					return Const.CONNECTION_TYPE_WIFI;
				} else if (netType == 6) {
					return Const.CONNECTION_TYPE_WIMAX;
				} else if (netType == ConnectivityManager.TYPE_MOBILE) {
					switch (netSubtype) {
					case TelephonyManager.NETWORK_TYPE_1xRTT:
						return Const.CONNECTION_TYPE_MOBILE_1xRTT;
					case TelephonyManager.NETWORK_TYPE_CDMA:
						return Const.CONNECTION_TYPE_MOBILE_CDMA;
					case TelephonyManager.NETWORK_TYPE_EDGE:
						return Const.CONNECTION_TYPE_MOBILE_EDGE;
					case TelephonyManager.NETWORK_TYPE_EVDO_0:
						return Const.CONNECTION_TYPE_MOBILE_EVDO_0;
					case TelephonyManager.NETWORK_TYPE_EVDO_A:
						return Const.CONNECTION_TYPE_MOBILE_EVDO_A;
					case TelephonyManager.NETWORK_TYPE_GPRS:
						return Const.CONNECTION_TYPE_MOBILE_GPRS;
					case TelephonyManager.NETWORK_TYPE_UMTS:
						return Const.CONNECTION_TYPE_MOBILE_UMTS;
					case 14:
						return Const.CONNECTION_TYPE_MOBILE_EHRPD;
					case 12:
						return Const.CONNECTION_TYPE_MOBILE_EVDO_B;
					case 8:
						return Const.CONNECTION_TYPE_MOBILE_HSDPA;
					case 10:
						return Const.CONNECTION_TYPE_MOBILE_HSPA;
					case 15:
						return Const.CONNECTION_TYPE_MOBILE_HSPAP;
					case 9:
						return Const.CONNECTION_TYPE_MOBILE_HSUPA;
					case 11:
						return Const.CONNECTION_TYPE_MOBILE_IDEN;
					case 13:
						return Const.CONNECTION_TYPE_MOBILE_LTE;
					default:
						return Const.CONNECTION_TYPE_MOBILE_UNKNOWN;
					}
				} else {
					return Const.CONNECTION_TYPE_UNKNOWN;
				}
			} else {
				return Const.CONNECTION_TYPE_UNKNOWN;
			}
		}

		public static String getLocalIpAddress() {
			try {
				for (Enumeration<NetworkInterface> en = NetworkInterface
						.getNetworkInterfaces(); en.hasMoreElements();) {
					NetworkInterface intf = en.nextElement();
					for (Enumeration<InetAddress> enumIpAddr = intf
							.getInetAddresses(); enumIpAddr.hasMoreElements();) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							return inetAddress.getHostAddress().toString();
						}
					}
				}
			} catch (SocketException ex) {
			}
			return null;
		}

		public static String getTelephonyDeviceId(Context context) {
			int telephonyPermission = context
					.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE);

			if (telephonyPermission == PackageManager.PERMISSION_GRANTED) {
				TelephonyManager tm = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				return tm.getDeviceId();
			}
			return "";
		}

		public static String getDeviceId(Context context) {
			String androidId = Secure.getString(context.getContentResolver(),
					Secure.ANDROID_ID);
			if ((androidId == null) || (androidId.equals("9774d56d682e549c"))
					|| (androidId.equals("0000000000000000"))) {
				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(context);
				androidId = prefs.getString(PREFS_DEVICE_ID, null);
				if (androidId == null) {
					try {
						String uuid = UUID.randomUUID().toString();
						MessageDigest digest = MessageDigest.getInstance("MD5");
						digest.update(uuid.getBytes(), 0, uuid.length());
						androidId = String
								.format("%032X",
										new Object[] { new BigInteger(1, digest
												.digest()) }).substring(0, 16);
					} catch (Exception e) {
						androidId = "9774d56d682e549c";
					}
					prefs.edit().putString(PREFS_DEVICE_ID, androidId).commit();
				}
			}
			return androidId;
		}

		public static Location getLocation(Context context) {
			int isAccessFineLocation = context
					.checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
			int isAccessCoarseLocation = context
					.checkCallingOrSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
			if ((isAccessFineLocation == PackageManager.PERMISSION_GRANTED)
					|| (isAccessCoarseLocation == PackageManager.PERMISSION_GRANTED)) {
				LocationManager locationManager = (LocationManager) context
						.getSystemService(Context.LOCATION_SERVICE);
				if (locationManager != null) {
					if (isAccessFineLocation == PackageManager.PERMISSION_GRANTED) {
						boolean isGpsEnabled = locationManager
								.isProviderEnabled(LocationManager.GPS_PROVIDER);
						if (isGpsEnabled) {
							return locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
						}
					}
					if (isAccessCoarseLocation == PackageManager.PERMISSION_GRANTED) {
						boolean isNetworkEnabled = locationManager
								.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

						if (isNetworkEnabled) {
							return locationManager
									.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						}
					}
				}
			}
			return null;
		}

		public static String getDefaultUserAgentString(Context context) {
//			String userAgent = System.getProperty("http.agent");
			try {
				Constructor<WebSettings> constructor = WebSettings.class
						.getDeclaredConstructor(Context.class, WebView.class);
				constructor.setAccessible(true);
				try {
					WebSettings settings = constructor.newInstance(context, null);
					return settings.getUserAgentString();
				} finally {
					constructor.setAccessible(false);
		}
			} catch (Exception e) {
				return new WebView(context).getSettings().getUserAgentString();
			}
		}

		public static String buildUserAgent() {
			String androidVersion = Build.VERSION.RELEASE;
			String model = Build.MODEL;
			String androidBuild = Build.ID;
			final Locale l = Locale.getDefault();
			final String language = l.getLanguage();
			String locale = "en";
			if (language != null) {
				locale = language.toLowerCase();
				final String country = l.getCountry();
				if (country != null) {
					locale += "-" + country.toLowerCase();
				}
			}

			String userAgent = String.format(Const.USER_AGENT_PATTERN,
					androidVersion, locale, model, androidBuild);
			return userAgent;
		}

		public static int getMemoryClass(Context context) {
			try {
				Method getMemoryClassMethod = ActivityManager.class
						.getMethod("getMemoryClass");
				ActivityManager ac = (ActivityManager) context
						.getSystemService(Context.ACTIVITY_SERVICE);
				return (Integer) getMemoryClassMethod.invoke(ac, new Object[] {});
			} catch (Exception ex) {
				return 16;
			}
		}

		public static String GetDeviceName(){
			return new Build().MODEL;
		}
		
		public static String GetMacAddress(Context context){
			String macAddress = null;
			WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = (null == wifiMgr ? null : wifiMgr
					.getConnectionInfo());
			if (info != null) {
				macAddress = info.getMacAddress();
			}
			return macAddress;
		}
	 
}
