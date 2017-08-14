package com.google.android.gms.gcm;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import java.util.MissingFormatArgumentException;
import org.json.JSONArray;
import org.json.JSONException;

final class zza {
   static zza zzbfw;
   private final Context mContext;

   static synchronized zza zzaX(Context var0) {
      if (zzbfw == null) {
         zzbfw = new zza(var0);
      }

      return zzbfw;
   }

   static boolean zzaY(Context var0) {
      if (((KeyguardManager)var0.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
         return false;
      } else {
         int var1 = Process.myPid();
         List var2;
         if ((var2 = ((ActivityManager)var0.getSystemService("activity")).getRunningAppProcesses()) != null) {
            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
               RunningAppProcessInfo var4;
               if ((var4 = (RunningAppProcessInfo)var3.next()).pid == var1) {
                  if (var4.importance == 100) {
                     return true;
                  }

                  return false;
               }
            }
         }

         return false;
      }
   }

   static void zzu(Bundle var0) {
      Bundle var1 = new Bundle();
      Iterator var2 = var0.keySet().iterator();

      String var3;
      while(var2.hasNext()) {
         var3 = (String)var2.next();
         String var4 = var0.getString(var3);
         if (var3.startsWith("gcm.notification.")) {
            var3 = var3.replace("gcm.notification.", "gcm.n.");
         }

         if (var3.startsWith("gcm.n.")) {
            if (!"gcm.n.e".equals(var3)) {
               var1.putString(var3.substring(6), var4);
            }

            var2.remove();
         }
      }

      if ((var3 = var1.getString("sound2")) != null) {
         var1.remove("sound2");
         var1.putString("sound", var3);
      }

      if (!var1.isEmpty()) {
         var0.putBundle("notification", var1);
      }

   }

   static String zze(Bundle var0, String var1) {
      String var2;
      if ((var2 = var0.getString(var1)) == null) {
         var2 = var0.getString(var1.replace("gcm.n.", "gcm.notification."));
      }

      return var2;
   }

   private zza(Context var1) {
      this.mContext = var1.getApplicationContext();
   }

   final boolean zzv(Bundle var1) {
      String var5;
      String var6;
      String var13;
      int var10000;
      label66: {
         var5 = this.zzf(var1, "gcm.n.title");
         var6 = this.zzf(var1, "gcm.n.body");
         var13 = zze(var1, "gcm.n.icon");
         if (!TextUtils.isEmpty(var13)) {
            Resources var14;
            int var15;
            if ((var15 = (var14 = this.mContext.getResources()).getIdentifier(var13, "drawable", this.mContext.getPackageName())) != 0) {
               var10000 = var15;
               break label66;
            }

            if ((var15 = var14.getIdentifier(var13, "mipmap", this.mContext.getPackageName())) != 0) {
               var10000 = var15;
               break label66;
            }

            Log.w("GcmNotification", (new StringBuilder(57 + String.valueOf(var13).length())).append("Icon resource ").append(var13).append(" not found. Notification will use app icon.").toString());
         }

         int var20;
         if ((var20 = this.mContext.getApplicationInfo().icon) == 0) {
            var20 = 17301651;
         }

         var10000 = var20;
      }

      int var7 = var10000;
      String var8 = zze(var1, "gcm.n.color");
      var13 = zze(var1, "gcm.n.sound2");
      Uri var21;
      if (TextUtils.isEmpty(var13)) {
         var21 = null;
      } else if (!"default".equals(var13) && this.mContext.getResources().getIdentifier(var13, "raw", this.mContext.getPackageName()) != 0) {
         String var16 = String.valueOf("android.resource://");
         String var17 = String.valueOf(this.mContext.getPackageName());
         var21 = Uri.parse((new StringBuilder(5 + String.valueOf(var16).length() + String.valueOf(var17).length() + String.valueOf(var13).length())).append(var16).append(var17).append("/raw/").append(var13).toString());
      } else {
         var21 = RingtoneManager.getDefaultUri(2);
      }

      Uri var9 = var21;
      PendingIntent var10 = this.zzw(var1);
      Builder var11 = (new Builder(this.mContext)).setAutoCancel(true).setSmallIcon(var7);
      if (!TextUtils.isEmpty(var5)) {
         var11.setContentTitle(var5);
      } else {
         var11.setContentTitle(this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager()));
      }

      if (!TextUtils.isEmpty(var6)) {
         var11.setContentText(var6);
      }

      if (!TextUtils.isEmpty(var8)) {
         var11.setColor(Color.parseColor(var8));
      }

      if (var9 != null) {
         var11.setSound(var9);
      }

      if (var10 != null) {
         var11.setContentIntent(var10);
      }

      Notification var2 = var11.build();
      String var4 = zze(var1, "gcm.n.tag");
      if (Log.isLoggable("GcmNotification", 3)) {
         Log.d("GcmNotification", "Showing notification");
      }

      NotificationManager var18 = (NotificationManager)this.mContext.getSystemService("notification");
      if (TextUtils.isEmpty(var4)) {
         long var19 = SystemClock.uptimeMillis();
         var4 = (new StringBuilder(37)).append("GCM-Notification:").append(var19).toString();
      }

      var18.notify(var4, 0, var2);
      return true;
   }

   private final String zzf(Bundle var1, String var2) {
      String var3;
      if (!TextUtils.isEmpty(var3 = zze(var1, var2))) {
         return var3;
      } else {
         String var10001 = String.valueOf(var2);
         String var10002 = String.valueOf("_loc_key");
         String var10003;
         if (var10002.length() != 0) {
            var10001 = var10001.concat(var10002);
         } else {
            var10003 = new String;
            var10002 = var10001;
            var10001 = var10003;
            var10003.<init>(var10002);
         }

         String var4;
         if (TextUtils.isEmpty(var4 = zze(var1, var10001))) {
            return null;
         } else {
            Resources var5;
            int var6;
            String var7;
            if ((var6 = (var5 = this.mContext.getResources()).getIdentifier(var4, "string", this.mContext.getPackageName())) == 0) {
               var10001 = String.valueOf(var2);
               var10002 = String.valueOf("_loc_key");
               if (var10002.length() != 0) {
                  var10001 = var10001.concat(var10002);
               } else {
                  var10003 = new String;
                  var10002 = var10001;
                  var10001 = var10003;
                  var10003.<init>(var10002);
               }

               var7 = String.valueOf(var10001.substring(6));
               Log.w("GcmNotification", (new StringBuilder(49 + String.valueOf(var7).length() + String.valueOf(var4).length())).append(var7).append(" resource not found: ").append(var4).append(" Default value will be used.").toString());
               return null;
            } else {
               var10001 = String.valueOf(var2);
               var10002 = String.valueOf("_loc_args");
               if (var10002.length() != 0) {
                  var10001 = var10001.concat(var10002);
               } else {
                  var10003 = new String;
                  var10002 = var10001;
                  var10001 = var10003;
                  var10003.<init>(var10002);
               }

               if (TextUtils.isEmpty(var7 = zze(var1, var10001))) {
                  return var5.getString(var6);
               } else {
                  try {
                     JSONArray var8;
                     String[] var9 = new String[(var8 = new JSONArray(var7)).length()];

                     for(int var14 = 0; var14 < var9.length; ++var14) {
                        var9[var14] = (String)var8.opt(var14);
                     }

                     return var5.getString(var6, var9);
                  } catch (JSONException var12) {
                     var10001 = String.valueOf(var2);
                     var10002 = String.valueOf("_loc_args");
                     if (var10002.length() != 0) {
                        var10001 = var10001.concat(var10002);
                     } else {
                        var10003 = new String;
                        var10002 = var10001;
                        var10001 = var10003;
                        var10003.<init>(var10002);
                     }

                     String var10 = String.valueOf(var10001.substring(6));
                     Log.w("GcmNotification", (new StringBuilder(41 + String.valueOf(var10).length() + String.valueOf(var7).length())).append("Malformed ").append(var10).append(": ").append(var7).append("  Default value will be used.").toString());
                  } catch (MissingFormatArgumentException var13) {
                     Log.w("GcmNotification", (new StringBuilder(58 + String.valueOf(var4).length() + String.valueOf(var7).length())).append("Missing format argument for ").append(var4).append(": ").append(var7).append(" Default value will be used.").toString(), var13);
                  }

                  return null;
               }
            }
         }
      }
   }

   private final PendingIntent zzw(Bundle var1) {
      Intent var2;
      String var3;
      if (!TextUtils.isEmpty(var3 = zze(var1, "gcm.n.click_action"))) {
         (var2 = new Intent(var3)).setPackage(this.mContext.getPackageName());
         var2.setFlags(268435456);
      } else if ((var2 = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName())) == null) {
         Log.w("GcmNotification", "No activity found to launch app");
         return null;
      }

      GcmListenerService.zzt(var1 = new Bundle(var1));
      var2.putExtras(var1);
      Iterator var4 = var1.keySet().iterator();

      while(true) {
         String var5;
         do {
            if (!var4.hasNext()) {
               return PendingIntent.getActivity(this.mContext, (int)SystemClock.uptimeMillis(), var2, 1073741824);
            }
         } while(!(var5 = (String)var4.next()).startsWith("gcm.n.") && !var5.startsWith("gcm.notification."));

         var2.removeExtra(var5);
      }
   }
}
