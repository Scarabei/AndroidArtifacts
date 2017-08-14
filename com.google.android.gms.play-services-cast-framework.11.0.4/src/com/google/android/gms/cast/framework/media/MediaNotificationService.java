package com.google.android.gms.cast.framework.media;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzauj;
import com.google.android.gms.internal.zzayo;

public class MediaNotificationService extends Service {
   private static final zzayo zzarK = new zzayo("MediaNotificationService");
   public static final String ACTION_UPDATE_NOTIFICATION = "com.google.android.gms.cast.framework.action.UPDATE_NOTIFICATION";
   private zzd zzatM;

   public void onCreate() {
      CastOptions var1 = CastContext.getSharedInstance(this).getCastOptions();
      this.zzatM = zzauj.zza((Service)this, (IObjectWrapper)CastContext.getSharedInstance(this).zznp(), (IObjectWrapper)com.google.android.gms.dynamic.zzn.zzw((Object)null), (CastMediaOptions)var1.getCastMediaOptions());

      try {
         this.zzatM.onCreate();
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"onCreate", zzd.class.getSimpleName()});
      }

      super.onCreate();
   }

   public int onStartCommand(Intent var1, int var2, int var3) {
      try {
         return this.zzatM.onStartCommand(var1, var2, var3);
      } catch (RemoteException var5) {
         zzarK.zzb(var5, "Unable to call %s on %s.", new Object[]{"onStartCommand", zzd.class.getSimpleName()});
         return 1;
      }
   }

   public IBinder onBind(Intent var1) {
      try {
         return this.zzatM.onBind(var1);
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"onBind", zzd.class.getSimpleName()});
         return null;
      }
   }

   public void onDestroy() {
      try {
         this.zzatM.onDestroy();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"onDestroy", zzd.class.getSimpleName()});
      }

      super.onDestroy();
   }
}
