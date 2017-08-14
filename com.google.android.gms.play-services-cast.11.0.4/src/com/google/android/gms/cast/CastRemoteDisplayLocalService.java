package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ServiceInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;
import android.text.TextUtils;
import android.view.Display;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.id;
import com.google.android.gms.R.string;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzayo;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(19)
public abstract class CastRemoteDisplayLocalService extends Service {
   private static final zzayo zzapq = new zzayo("CastRemoteDisplayLocalService");
   private static final int zzapr;
   private static final Object zzaps;
   private static AtomicBoolean zzapt;
   private GoogleApiClient zzapu;
   private CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzapv;
   private String zzaoM;
   private CastRemoteDisplayLocalService.Callbacks zzapw;
   private CastRemoteDisplayLocalService.zzb zzapx;
   private CastRemoteDisplayLocalService.NotificationSettings zzapy;
   private Notification mNotification;
   private boolean zzapz;
   private PendingIntent zzapA;
   private CastDevice zzapB;
   private Display zzPO;
   private Context zzapC;
   private ServiceConnection zzapD;
   private Handler mHandler;
   private MediaRouter zzapE;
   private boolean zzapF = false;
   private final Callback zzapG = new zzp(this);
   private static CastRemoteDisplayLocalService zzapH;
   private final IBinder zzapI = new CastRemoteDisplayLocalService.zza((zzp)null);

   public IBinder onBind(Intent var1) {
      this.zzbp("onBind");
      return this.zzapI;
   }

   public void onCreate() {
      this.zzbp("onCreate");
      super.onCreate();
      this.mHandler = new Handler(this.getMainLooper());
      this.mHandler.postDelayed(new zzr(this), 100L);
   }

   public int onStartCommand(Intent var1, int var2, int var3) {
      this.zzbp("onStartCommand");
      this.zzapF = true;
      return 2;
   }

   protected static void setDebugEnabled() {
      zzapq.zzaa(true);
   }

   protected Display getDisplay() {
      return this.zzPO;
   }

   public abstract void onCreatePresentation(Display var1);

   public abstract void onDismissPresentation();

   public static CastRemoteDisplayLocalService getInstance() {
      Object var0 = zzaps;
      synchronized(zzaps) {
         return zzapH;
      }
   }

   public static void startService(Context var0, Class var1, String var2, CastDevice var3, CastRemoteDisplayLocalService.NotificationSettings var4, CastRemoteDisplayLocalService.Callbacks var5) {
      startServiceWithOptions(var0, var1, var2, var3, new CastRemoteDisplayLocalService.Options(), var4, var5);
   }

   public static void startServiceWithOptions(@NonNull Context var0, @NonNull Class var1, @NonNull String var2, @NonNull CastDevice var3, @NonNull CastRemoteDisplayLocalService.Options var4, @NonNull CastRemoteDisplayLocalService.NotificationSettings var5, @NonNull CastRemoteDisplayLocalService.Callbacks var6) {
      zzapq.zzb("Starting Service");
      Object var7 = zzaps;
      synchronized(zzaps) {
         if (zzapH != null) {
            zzapq.zzf("An existing service had not been stopped before starting one");
            zzR(true);
         }
      }

      Class var10 = var1;
      Context var9 = var0;

      try {
         ComponentName var11 = new ComponentName(var9, var10);
         ServiceInfo var12;
         if ((var12 = var9.getPackageManager().getServiceInfo(var11, 128)) != null && var12.exported) {
            throw new IllegalStateException("The service must not be exported, verify the manifest configuration");
         }
      } catch (NameNotFoundException var13) {
         throw new IllegalStateException("Service not found, did you forget to configure it in the manifest?");
      }

      zzbo.zzb(var0, "activityContext is required.");
      zzbo.zzb(var1, "serviceClass is required.");
      zzbo.zzb(var2, "applicationId is required.");
      zzbo.zzb(var3, "device is required.");
      zzbo.zzb(var4, "options is required.");
      zzbo.zzb(var5, "notificationSettings is required.");
      zzbo.zzb(var6, "callbacks is required.");
      if (var5.mNotification == null && var5.zzapQ == null) {
         throw new IllegalArgumentException("notificationSettings: Either the notification or the notificationPendingIntent must be provided");
      } else if (zzapt.getAndSet(true)) {
         zzapq.zzc("Service is already being started, startService has been called twice");
      } else {
         Intent var15 = new Intent(var0, var1);
         var0.startService(var15);
         var0.bindService(var15, new zzs(var2, var3, var4, var5, var0, var6), 64);
      }
   }

   private final void zzQ(boolean var1) {
      this.zzbp("Stopping Service");
      zzbo.zzcz("stopServiceInstanceInternal must be called on the main thread");
      if (!var1 && this.zzapE != null) {
         this.zzbp("Setting default route");
         this.zzapE.selectRoute(this.zzapE.getDefaultRoute());
      }

      if (this.zzapx != null) {
         this.zzbp("Unregistering notification receiver");
         this.unregisterReceiver(this.zzapx);
      }

      this.zzbp("stopRemoteDisplaySession");
      this.zzbp("stopRemoteDisplay");
      if (this.zzapu != null && this.zzapu.isConnected()) {
         CastRemoteDisplay.CastRemoteDisplayApi.stopRemoteDisplay(this.zzapu).setResultCallback(new zzx(this));
      } else {
         zzapq.zzc("Unable to stop the remote display as the API client is not ready");
      }

      this.onDismissPresentation();
      this.zzbp("Stopping the remote display Service");
      this.stopForeground(true);
      this.stopSelf();
      if (this.zzapE != null) {
         zzbo.zzcz("CastRemoteDisplayLocalService calls must be done on the main thread");
         this.zzbp("removeMediaRouterCallback");
         this.zzapE.removeCallback(this.zzapG);
      }

      if (this.zzapu != null) {
         this.zzapu.disconnect();
         this.zzapu = null;
      }

      if (this.zzapC != null && this.zzapD != null) {
         try {
            this.zzapC.unbindService(this.zzapD);
         } catch (IllegalArgumentException var4) {
            this.zzbp("No need to unbind service, already unbound");
         }

         this.zzapD = null;
         this.zzapC = null;
      }

      this.zzaoM = null;
      this.zzapu = null;
      this.mNotification = null;
      this.zzPO = null;
   }

   private static void zzR(boolean var0) {
      zzapq.zzb("Stopping Service");
      zzapt.set(false);
      Object var2 = zzaps;
      CastRemoteDisplayLocalService var1;
      synchronized(zzaps) {
         if (zzapH == null) {
            zzapq.zzc("Service is already being stopped");
            return;
         }

         var1 = zzapH;
         zzapH = null;
      }

      if (var1.mHandler != null) {
         if (Looper.myLooper() != Looper.getMainLooper()) {
            var1.mHandler.post(new zzt(var1, var0));
            return;
         }

         var1.zzQ(var0);
      }

   }

   public static void stopService() {
      zzR(false);
   }

   public void updateNotificationSettings(CastRemoteDisplayLocalService.NotificationSettings var1) {
      zzbo.zzb(var1, "notificationSettings is required.");
      zzbo.zzb(this.mHandler, "Service is not ready yet.");
      this.mHandler.post(new zzu(this, var1));
   }

   private final void zza(CastRemoteDisplayLocalService.NotificationSettings var1) {
      zzbo.zzcz("updateNotificationSettingsInternal must be called on the main thread");
      if (this.zzapy == null) {
         throw new IllegalStateException("No current notification settings to update");
      } else {
         if (this.zzapz) {
            if (var1.mNotification != null) {
               throw new IllegalStateException("Current mode is default notification, notification attribute must not be provided");
            }

            if (var1.zzapQ != null) {
               this.zzapy.zzapQ = var1.zzapQ;
            }

            if (!TextUtils.isEmpty(var1.zzapR)) {
               this.zzapy.zzapR = var1.zzapR;
            }

            if (!TextUtils.isEmpty(var1.zzapS)) {
               this.zzapy.zzapS = var1.zzapS;
            }

            this.mNotification = this.zzS(true);
         } else {
            zzbo.zzb(var1.mNotification, "notification is required.");
            this.mNotification = var1.mNotification;
            this.zzapy.mNotification = this.mNotification;
         }

         this.startForeground(zzapr, this.mNotification);
      }
   }

   private final void zza(Display var1) {
      this.zzPO = var1;
      if (this.zzapz) {
         this.mNotification = this.zzS(true);
         this.startForeground(zzapr, this.mNotification);
      }

      if (this.zzapw != null) {
         this.zzapw.onRemoteDisplaySessionStarted(this);
         this.zzapw = null;
      }

      this.onCreatePresentation(this.zzPO);
   }

   private final boolean zza(String var1, CastDevice var2, CastRemoteDisplayLocalService.Options var3, CastRemoteDisplayLocalService.NotificationSettings var4, Context var5, ServiceConnection var6, CastRemoteDisplayLocalService.Callbacks var7) {
      this.zzbp("startRemoteDisplaySession");
      zzbo.zzcz("Starting the Cast Remote Display must be done on the main thread");
      Object var8 = zzaps;
      synchronized(zzaps) {
         if (zzapH != null) {
            zzapq.zzf("An existing service had not been stopped before starting one");
            return false;
         }

         zzapH = this;
      }

      this.zzapw = var7;
      this.zzaoM = var1;
      this.zzapB = var2;
      this.zzapC = var5;
      this.zzapD = var6;
      this.zzapE = MediaRouter.getInstance(this.getApplicationContext());
      MediaRouteSelector var17 = (new android.support.v7.media.MediaRouteSelector.Builder()).addControlCategory(CastMediaControlIntent.categoryForCast(this.zzaoM)).build();
      this.zzbp("addMediaRouterCallback");
      this.zzapE.addCallback(var17, this.zzapG, 4);
      this.zzapv = new zzv(this);
      this.mNotification = var4.mNotification;
      this.zzapx = new CastRemoteDisplayLocalService.zzb((zzp)null);
      this.registerReceiver(this.zzapx, new IntentFilter("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT"));
      this.zzapy = new CastRemoteDisplayLocalService.NotificationSettings(var4, (zzp)null);
      if (this.zzapy.mNotification == null) {
         this.zzapz = true;
         this.mNotification = this.zzS(false);
      } else {
         this.zzapz = false;
         this.mNotification = this.zzapy.mNotification;
      }

      this.startForeground(zzapr, this.mNotification);
      CastRemoteDisplay.CastRemoteDisplayOptions.Builder var13 = new CastRemoteDisplay.CastRemoteDisplayOptions.Builder(var2, this.zzapv);
      if (var3 != null) {
         var13.setConfigPreset(var3.zzapn);
      }

      zzy var14 = new zzy(this);
      zzq var15 = new zzq(this);
      this.zzapu = (new com.google.android.gms.common.api.GoogleApiClient.Builder(this, var14, var15)).addApi(CastRemoteDisplay.API, var13.build()).build();
      this.zzapu.connect();
      if (this.zzapw != null) {
         this.zzapw.onServiceCreated(this);
      }

      return true;
   }

   private final void zznd() {
      this.zzbp("startRemoteDisplay");
      if (this.zzapu != null && this.zzapu.isConnected()) {
         CastRemoteDisplay.CastRemoteDisplayApi.startRemoteDisplay(this.zzapu, this.zzaoM).setResultCallback(new zzw(this));
      } else {
         zzapq.zzc("Unable to start the remote display as the API client is not ready");
      }
   }

   private final void zzne() {
      if (this.zzapw != null) {
         this.zzapw.onRemoteDisplaySessionError(new Status(2200));
         this.zzapw = null;
      }

      stopService();
   }

   private final Notification zzS(boolean var1) {
      this.zzbp("createDefaultNotification");
      String var4 = this.zzapy.zzapR;
      String var5 = this.zzapy.zzapS;
      int var2;
      int var3;
      if (var1) {
         var2 = string.cast_notification_connected_message;
         var3 = drawable.cast_ic_notification_on;
      } else {
         var2 = string.cast_notification_connecting_message;
         var3 = drawable.cast_ic_notification_connecting;
      }

      String var6 = TextUtils.isEmpty(var4) ? (String)this.getPackageManager().getApplicationLabel(this.getApplicationInfo()) : var4;
      String var7 = TextUtils.isEmpty(var5) ? this.getString(var2, new Object[]{this.zzapB.getFriendlyName()}) : var5;
      android.support.v4.app.NotificationCompat.Builder var10000 = (new android.support.v4.app.NotificationCompat.Builder(this)).setContentTitle(var6).setContentText(var7).setContentIntent(this.zzapy.zzapQ).setSmallIcon(var3).setOngoing(true);
      String var10002 = this.getString(string.cast_notification_disconnect);
      if (this.zzapA == null) {
         Intent var9;
         (var9 = new Intent("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT")).setPackage(this.zzapC.getPackageName());
         this.zzapA = PendingIntent.getBroadcast(this, 0, var9, 268435456);
      }

      return var10000.addAction(17301560, var10002, this.zzapA).build();
   }

   private final void zzbp(String var1) {
      zzapq.zzb("[Instance: %s] %s", this, var1);
   }

   private final void zzbs(String var1) {
      zzapq.zzc("[Instance: %s] %s", this, var1);
   }

   // $FF: synthetic method
   static void zza(CastRemoteDisplayLocalService var0, String var1) {
      var0.zzbp(var1);
   }

   // $FF: synthetic method
   static CastDevice zza(CastRemoteDisplayLocalService var0) {
      return var0.zzapB;
   }

   // $FF: synthetic method
   static boolean zzb(CastRemoteDisplayLocalService var0) {
      return var0.zzapF;
   }

   // $FF: synthetic method
   static void zzb(CastRemoteDisplayLocalService var0, String var1) {
      var0.zzbs(var1);
   }

   // $FF: synthetic method
   static boolean zza(CastRemoteDisplayLocalService var0, String var1, CastDevice var2, CastRemoteDisplayLocalService.Options var3, CastRemoteDisplayLocalService.NotificationSettings var4, Context var5, ServiceConnection var6, CastRemoteDisplayLocalService.Callbacks var7) {
      return var0.zza(var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   static AtomicBoolean zzng() {
      return zzapt;
   }

   // $FF: synthetic method
   static void zza(CastRemoteDisplayLocalService var0, boolean var1) {
      var0.zzQ(var1);
   }

   // $FF: synthetic method
   static void zza(CastRemoteDisplayLocalService var0, CastRemoteDisplayLocalService.NotificationSettings var1) {
      var0.zza(var1);
   }

   // $FF: synthetic method
   static void zzT(boolean var0) {
      zzR(false);
   }

   // $FF: synthetic method
   static void zzc(CastRemoteDisplayLocalService var0) {
      var0.zzne();
   }

   // $FF: synthetic method
   static Object zznh() {
      return zzaps;
   }

   // $FF: synthetic method
   static CastRemoteDisplayLocalService zzni() {
      return zzapH;
   }

   // $FF: synthetic method
   static void zza(CastRemoteDisplayLocalService var0, Display var1) {
      var0.zza(var1);
   }

   // $FF: synthetic method
   static Context zzd(CastRemoteDisplayLocalService var0) {
      return var0.zzapC;
   }

   // $FF: synthetic method
   static ServiceConnection zze(CastRemoteDisplayLocalService var0) {
      return var0.zzapD;
   }

   // $FF: synthetic method
   static ServiceConnection zza(CastRemoteDisplayLocalService var0, ServiceConnection var1) {
      return var0.zzapD = null;
   }

   // $FF: synthetic method
   static Context zza(CastRemoteDisplayLocalService var0, Context var1) {
      return var0.zzapC = null;
   }

   // $FF: synthetic method
   static Display zzb(CastRemoteDisplayLocalService var0, Display var1) {
      return var0.zzPO = null;
   }

   // $FF: synthetic method
   static void zzf(CastRemoteDisplayLocalService var0) {
      var0.zznd();
   }

   static {
      zzapr = id.cast_notification_id;
      zzaps = new Object();
      zzapt = new AtomicBoolean(false);
   }

   class zza extends Binder {
      private zza() {
      }

      // $FF: synthetic method
      zza(zzp var2) {
         this();
      }
   }

   static final class zzb extends BroadcastReceiver {
      private zzb() {
      }

      public final void onReceive(Context var1, Intent var2) {
         if (var2.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT")) {
            CastRemoteDisplayLocalService.zzapq.zzb("disconnecting");
            CastRemoteDisplayLocalService.stopService();
         }

      }

      // $FF: synthetic method
      zzb(zzp var1) {
         this();
      }
   }

   public static class Options {
      @CastRemoteDisplay.Configuration
      int zzapn = 2;

      public void setConfigPreset(@CastRemoteDisplay.Configuration int var1) {
         this.zzapn = var1;
      }

      public int getConfigPreset() {
         return this.zzapn;
      }
   }

   public static final class NotificationSettings {
      private Notification mNotification;
      private PendingIntent zzapQ;
      private String zzapR;
      private String zzapS;

      private NotificationSettings() {
      }

      private NotificationSettings(CastRemoteDisplayLocalService.NotificationSettings var1) {
         this.mNotification = var1.mNotification;
         this.zzapQ = var1.zzapQ;
         this.zzapR = var1.zzapR;
         this.zzapS = var1.zzapS;
      }

      // $FF: synthetic method
      NotificationSettings(zzp var1) {
         this();
      }

      // $FF: synthetic method
      NotificationSettings(CastRemoteDisplayLocalService.NotificationSettings var1, zzp var2) {
         this(var1);
      }

      public static final class Builder {
         private CastRemoteDisplayLocalService.NotificationSettings zzapT = new CastRemoteDisplayLocalService.NotificationSettings((zzp)null);

         public final CastRemoteDisplayLocalService.NotificationSettings.Builder setNotification(Notification var1) {
            this.zzapT.mNotification = var1;
            return this;
         }

         public final CastRemoteDisplayLocalService.NotificationSettings.Builder setNotificationPendingIntent(PendingIntent var1) {
            this.zzapT.zzapQ = var1;
            return this;
         }

         public final CastRemoteDisplayLocalService.NotificationSettings.Builder setNotificationTitle(String var1) {
            this.zzapT.zzapR = var1;
            return this;
         }

         public final CastRemoteDisplayLocalService.NotificationSettings.Builder setNotificationText(String var1) {
            this.zzapT.zzapS = var1;
            return this;
         }

         public final CastRemoteDisplayLocalService.NotificationSettings build() {
            if (this.zzapT.mNotification != null) {
               if (!TextUtils.isEmpty(this.zzapT.zzapR)) {
                  throw new IllegalArgumentException("notificationTitle requires using the default notification");
               }

               if (!TextUtils.isEmpty(this.zzapT.zzapS)) {
                  throw new IllegalArgumentException("notificationText requires using the default notification");
               }

               if (this.zzapT.zzapQ != null) {
                  throw new IllegalArgumentException("notificationPendingIntent requires using the default notification");
               }
            } else if (TextUtils.isEmpty(this.zzapT.zzapR) && TextUtils.isEmpty(this.zzapT.zzapS) && this.zzapT.zzapQ == null) {
               throw new IllegalArgumentException("At least an argument must be provided");
            }

            return this.zzapT;
         }
      }
   }

   public interface Callbacks {
      void onServiceCreated(CastRemoteDisplayLocalService var1);

      void onRemoteDisplaySessionStarted(CastRemoteDisplayLocalService var1);

      void onRemoteDisplaySessionError(Status var1);
   }
}
