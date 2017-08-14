package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.app.Notification.BigTextStyle;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.string;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.internal.zzbdk;
import com.google.android.gms.internal.zzbdl;
import com.google.android.gms.internal.zzbdt;
import com.google.android.gms.internal.zzbeb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class GoogleApiAvailability extends zze {
   private static final GoogleApiAvailability zzaAa = new GoogleApiAvailability();
   public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
   public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";

   public static GoogleApiAvailability getInstance() {
      return zzaAa;
   }

   @MainThread
   public Task makeGooglePlayServicesAvailable(Activity var1) {
      zzbo.zzcz("makeGooglePlayServicesAvailable must be called from the main thread");
      int var2;
      if ((var2 = this.isGooglePlayServicesAvailable(var1)) == 0) {
         return Tasks.forResult((Object)null);
      } else {
         zzbeb var3;
         (var3 = zzbeb.zzp(var1)).zzb(new ConnectionResult(var2, (PendingIntent)null), 0);
         return var3.getTask();
      }
   }

   public Dialog getErrorDialog(Activity var1, int var2, int var3) {
      return this.getErrorDialog(var1, var2, var3, (OnCancelListener)null);
   }

   public Dialog getErrorDialog(Activity var1, int var2, int var3, OnCancelListener var4) {
      Intent var5 = zze.zza(var1, var2, "d");
      return zza(var1, var2, (zzt)zzt.zza(var1, var5, var3), (OnCancelListener)var4);
   }

   public boolean showErrorDialogFragment(Activity var1, int var2, int var3) {
      return this.showErrorDialogFragment(var1, var2, var3, (OnCancelListener)null);
   }

   public final boolean zza(Activity var1, @NonNull zzbdt var2, int var3, int var4, OnCancelListener var5) {
      Intent var6 = zze.zza(var1, var3, "d");
      Dialog var7;
      if ((var7 = zza(var1, var3, (zzt)zzt.zza((zzbdt)var2, var6, 2), (OnCancelListener)var5)) == null) {
         return false;
      } else {
         zza(var1, var7, "GooglePlayServicesErrorDialog", var5);
         return true;
      }
   }

   public boolean showErrorDialogFragment(Activity var1, int var2, int var3, OnCancelListener var4) {
      Dialog var5;
      if ((var5 = this.getErrorDialog(var1, var2, var3, var4)) == null) {
         return false;
      } else {
         zza(var1, var5, "GooglePlayServicesErrorDialog", var4);
         return true;
      }
   }

   public void showErrorNotification(Context var1, int var2) {
      PendingIntent var5 = this.zza(var1, var2, 0, "n");
      this.zza(var1, var2, (String)null, (PendingIntent)var5);
   }

   public void showErrorNotification(Context var1, ConnectionResult var2) {
      PendingIntent var3 = this.getErrorResolutionPendingIntent(var1, var2);
      this.zza(var1, var2.getErrorCode(), (String)null, (PendingIntent)var3);
   }

   public final boolean zza(Context var1, ConnectionResult var2, int var3) {
      PendingIntent var4;
      if ((var4 = this.getErrorResolutionPendingIntent(var1, var2)) != null) {
         this.zza(var1, var2.getErrorCode(), (String)null, (PendingIntent)GoogleApiActivity.zza(var1, var4, var3));
         return true;
      } else {
         return false;
      }
   }

   public static Dialog zza(Activity var0, OnCancelListener var1) {
      ProgressBar var2;
      (var2 = new ProgressBar(var0, (AttributeSet)null, 16842874)).setIndeterminate(true);
      var2.setVisibility(0);
      Builder var3;
      (var3 = new Builder(var0)).setView(var2);
      var3.setMessage(zzs.zzi(var0, 18));
      var3.setPositiveButton("", (OnClickListener)null);
      AlertDialog var4 = var3.create();
      zza(var0, var4, "GooglePlayServicesUpdatingDialog", var1);
      return var4;
   }

   @Nullable
   public static zzbdk zza(Context var0, zzbdl var1) {
      IntentFilter var2;
      (var2 = new IntentFilter("android.intent.action.PACKAGE_ADDED")).addDataScheme("package");
      zzbdk var3 = new zzbdk(var1);
      var0.registerReceiver(var3, var2);
      var3.setContext(var0);
      String var4 = "com.google.android.gms";
      if (!zzo.zzy(var0, "com.google.android.gms")) {
         var1.zzpA();
         var3.unregister();
         return null;
      } else {
         return var3;
      }
   }

   public int isGooglePlayServicesAvailable(Context var1) {
      return super.isGooglePlayServicesAvailable(var1);
   }

   public final boolean isUserResolvableError(int var1) {
      return super.isUserResolvableError(var1);
   }

   @Nullable
   public PendingIntent getErrorResolutionPendingIntent(Context var1, int var2, int var3) {
      return super.getErrorResolutionPendingIntent(var1, var2, var3);
   }

   @Nullable
   public PendingIntent getErrorResolutionPendingIntent(Context var1, ConnectionResult var2) {
      return var2.hasResolution() ? var2.getResolution() : this.getErrorResolutionPendingIntent(var1, var2.getErrorCode(), 0);
   }

   @Nullable
   public String getOpenSourceSoftwareLicenseInfo(Context var1) {
      return super.getOpenSourceSoftwareLicenseInfo(var1);
   }

   public final String getErrorString(int var1) {
      return super.getErrorString(var1);
   }

   static Dialog zza(Context var0, int var1, zzt var2, OnCancelListener var3) {
      if (var1 == 0) {
         return null;
      } else {
         Builder var4 = null;
         TypedValue var5 = new TypedValue();
         var0.getTheme().resolveAttribute(16843529, var5, true);
         String var6 = var0.getResources().getResourceEntryName(var5.resourceId);
         if ("Theme.Dialog.Alert".equals(var6)) {
            var4 = new Builder(var0, 5);
         }

         if (var4 == null) {
            var4 = new Builder(var0);
         }

         var4.setMessage(zzs.zzi(var0, var1));
         if (var3 != null) {
            var4.setOnCancelListener(var3);
         }

         String var7;
         if ((var7 = zzs.zzk(var0, var1)) != null) {
            var4.setPositiveButton(var7, var2);
         }

         String var8;
         if ((var8 = zzs.zzg(var0, var1)) != null) {
            var4.setTitle(var8);
         }

         return var4.create();
      }
   }

   static void zza(Activity var0, Dialog var1, String var2, OnCancelListener var3) {
      boolean var4;
      if (var4 = var0 instanceof FragmentActivity) {
         FragmentManager var6 = ((FragmentActivity)var0).getSupportFragmentManager();
         SupportErrorDialogFragment.newInstance(var1, var3).show(var6, var2);
      } else {
         android.app.FragmentManager var5 = var0.getFragmentManager();
         ErrorDialogFragment.newInstance(var1, var3).show(var5, var2);
      }
   }

   @TargetApi(20)
   private final void zza(Context var1, int var2, String var3, PendingIntent var4) {
      if (var2 == 18) {
         this.zzar(var1);
      } else if (var4 == null) {
         if (var2 == 6) {
            Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
         }

      } else {
         String var5 = zzs.zzh(var1, var2);
         String var6 = zzs.zzj(var1, var2);
         Resources var7 = var1.getResources();
         Notification var8;
         if (zzj.zzaH(var1)) {
            zzbo.zzae(zzq.zzsd());
            var8 = (new android.app.Notification.Builder(var1)).setSmallIcon(var1.getApplicationInfo().icon).setPriority(2).setAutoCancel(true).setContentTitle(var5).setStyle((new BigTextStyle()).bigText(var6)).addAction(drawable.common_full_open_on_phone, var7.getString(string.common_open_on_phone), var4).build();
         } else {
            var8 = (new android.support.v4.app.NotificationCompat.Builder(var1)).setSmallIcon(17301642).setTicker(var7.getString(string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(var4).setContentTitle(var5).setContentText(var6).setLocalOnly(true).setStyle((new android.support.v4.app.NotificationCompat.BigTextStyle()).bigText(var6)).build();
         }

         char var9;
         switch(var2) {
         case 1:
         case 2:
         case 3:
            var9 = 10436;
            zzo.zzaAs.set(false);
            break;
         default:
            var9 = '魭';
         }

         ((NotificationManager)var1.getSystemService("notification")).notify(var9, var8);
      }
   }

   final void zzar(Context var1) {
      (new GoogleApiAvailability.zza(var1)).sendEmptyMessageDelayed(1, 120000L);
   }

   static {
      GOOGLE_PLAY_SERVICES_VERSION_CODE = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
   }

   @SuppressLint({"HandlerLeak"})
   class zza extends Handler {
      private final Context mApplicationContext;
      // $FF: synthetic field
      private GoogleApiAvailability zzaAb;

      public zza(Context var2) {
         this.zzaAb = GoogleApiAvailability.this;
         super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
         this.mApplicationContext = var2.getApplicationContext();
      }

      public final void handleMessage(Message var1) {
         switch(var1.what) {
         case 1:
            int var2 = this.zzaAb.isGooglePlayServicesAvailable(this.mApplicationContext);
            if (this.zzaAb.isUserResolvableError(var2)) {
               this.zzaAb.showErrorNotification(this.mApplicationContext, var2);
               return;
            }
            break;
         default:
            int var3 = var1.what;
            Log.w("GoogleApiAvailability", (new StringBuilder(50)).append("Don't know how to handle this message: ").append(var3).toString());
         }

      }
   }
}
