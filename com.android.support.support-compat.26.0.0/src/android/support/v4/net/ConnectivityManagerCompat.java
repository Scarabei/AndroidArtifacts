package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConnectivityManagerCompat {
   public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
   public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;
   public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
   private static final ConnectivityManagerCompat.ConnectivityManagerCompatImpl IMPL;

   @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
   public static boolean isActiveNetworkMetered(ConnectivityManager cm) {
      return IMPL.isActiveNetworkMetered(cm);
   }

   public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager cm, Intent intent) {
      NetworkInfo info = (NetworkInfo)intent.getParcelableExtra("networkInfo");
      return info != null ? cm.getNetworkInfo(info.getType()) : null;
   }

   public static int getRestrictBackgroundStatus(ConnectivityManager cm) {
      return IMPL.getRestrictBackgroundStatus(cm);
   }

   static {
      if (VERSION.SDK_INT >= 24) {
         IMPL = new ConnectivityManagerCompat.ConnectivityManagerCompatApi24Impl();
      } else if (VERSION.SDK_INT >= 16) {
         IMPL = new ConnectivityManagerCompat.ConnectivityManagerCompatApi16Impl();
      } else {
         IMPL = new ConnectivityManagerCompat.ConnectivityManagerCompatBaseImpl();
      }

   }

   @RequiresApi(24)
   static class ConnectivityManagerCompatApi24Impl extends ConnectivityManagerCompat.ConnectivityManagerCompatApi16Impl {
      public int getRestrictBackgroundStatus(ConnectivityManager cm) {
         return cm.getRestrictBackgroundStatus();
      }
   }

   @RequiresApi(16)
   static class ConnectivityManagerCompatApi16Impl extends ConnectivityManagerCompat.ConnectivityManagerCompatBaseImpl {
      public boolean isActiveNetworkMetered(ConnectivityManager cm) {
         return cm.isActiveNetworkMetered();
      }
   }

   static class ConnectivityManagerCompatBaseImpl implements ConnectivityManagerCompat.ConnectivityManagerCompatImpl {
      public boolean isActiveNetworkMetered(ConnectivityManager cm) {
         NetworkInfo info = cm.getActiveNetworkInfo();
         if (info == null) {
            return true;
         } else {
            int type = info.getType();
            switch(type) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
               return true;
            case 1:
            case 7:
            case 9:
               return false;
            case 8:
            default:
               return true;
            }
         }
      }

      public int getRestrictBackgroundStatus(ConnectivityManager cm) {
         return 3;
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface RestrictBackgroundStatus {
   }

   interface ConnectivityManagerCompatImpl {
      boolean isActiveNetworkMetered(ConnectivityManager var1);

      int getRestrictBackgroundStatus(ConnectivityManager var1);
   }
}
