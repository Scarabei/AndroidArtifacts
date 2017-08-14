package android.support.v7.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import java.util.Calendar;

class TwilightManager {
   private static final String TAG = "TwilightManager";
   private static final int SUNRISE = 6;
   private static final int SUNSET = 22;
   private static TwilightManager sInstance;
   private final Context mContext;
   private final LocationManager mLocationManager;
   private final TwilightManager.TwilightState mTwilightState = new TwilightManager.TwilightState();

   static TwilightManager getInstance(@NonNull Context context) {
      if (sInstance == null) {
         context = context.getApplicationContext();
         sInstance = new TwilightManager(context, (LocationManager)context.getSystemService("location"));
      }

      return sInstance;
   }

   @VisibleForTesting
   static void setInstance(TwilightManager twilightManager) {
      sInstance = twilightManager;
   }

   @VisibleForTesting
   TwilightManager(@NonNull Context context, @NonNull LocationManager locationManager) {
      this.mContext = context;
      this.mLocationManager = locationManager;
   }

   boolean isNight() {
      TwilightManager.TwilightState state = this.mTwilightState;
      if (this.isStateValid()) {
         return state.isNight;
      } else {
         Location location = this.getLastKnownLocation();
         if (location != null) {
            this.updateState(location);
            return state.isNight;
         } else {
            Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(11);
            return hour < 6 || hour >= 22;
         }
      }
   }

   private Location getLastKnownLocation() {
      Location coarseLoc = null;
      Location fineLoc = null;
      int permission = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION");
      if (permission == 0) {
         coarseLoc = this.getLastKnownLocationForProvider("network");
      }

      permission = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION");
      if (permission == 0) {
         fineLoc = this.getLastKnownLocationForProvider("gps");
      }

      if (fineLoc != null && coarseLoc != null) {
         return fineLoc.getTime() > coarseLoc.getTime() ? fineLoc : coarseLoc;
      } else {
         return fineLoc != null ? fineLoc : coarseLoc;
      }
   }

   private Location getLastKnownLocationForProvider(String provider) {
      if (this.mLocationManager != null) {
         try {
            if (this.mLocationManager.isProviderEnabled(provider)) {
               return this.mLocationManager.getLastKnownLocation(provider);
            }
         } catch (Exception var3) {
            Log.d("TwilightManager", "Failed to get last known location", var3);
         }
      }

      return null;
   }

   private boolean isStateValid() {
      return this.mTwilightState != null && this.mTwilightState.nextUpdate > System.currentTimeMillis();
   }

   private void updateState(@NonNull Location location) {
      TwilightManager.TwilightState state = this.mTwilightState;
      long now = System.currentTimeMillis();
      TwilightCalculator calculator = TwilightCalculator.getInstance();
      calculator.calculateTwilight(now - 86400000L, location.getLatitude(), location.getLongitude());
      long yesterdaySunset = calculator.sunset;
      calculator.calculateTwilight(now, location.getLatitude(), location.getLongitude());
      boolean isNight = calculator.state == 1;
      long todaySunrise = calculator.sunrise;
      long todaySunset = calculator.sunset;
      calculator.calculateTwilight(now + 86400000L, location.getLatitude(), location.getLongitude());
      long tomorrowSunrise = calculator.sunrise;
      long nextUpdate = 0L;
      if (todaySunrise != -1L && todaySunset != -1L) {
         if (now > todaySunset) {
            nextUpdate += tomorrowSunrise;
         } else if (now > todaySunrise) {
            nextUpdate += todaySunset;
         } else {
            nextUpdate += todaySunrise;
         }

         nextUpdate += 60000L;
      } else {
         nextUpdate = now + 43200000L;
      }

      state.isNight = isNight;
      state.yesterdaySunset = yesterdaySunset;
      state.todaySunrise = todaySunrise;
      state.todaySunset = todaySunset;
      state.tomorrowSunrise = tomorrowSunrise;
      state.nextUpdate = nextUpdate;
   }

   private static class TwilightState {
      boolean isNight;
      long yesterdaySunset;
      long todaySunrise;
      long todaySunset;
      long tomorrowSunrise;
      long nextUpdate;
   }
}
