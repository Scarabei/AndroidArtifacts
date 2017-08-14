package com.google.android.gms.cast.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzauj;
import com.google.android.gms.internal.zzayo;

public class ReconnectionService extends Service {
   private static final zzayo zzarK = new zzayo("ReconnectionService");
   private zzs zzass;

   public void onCreate() {
      CastContext var1 = CastContext.getSharedInstance(this);
      this.zzass = zzauj.zza(this, var1.getSessionManager().zznp(), var1.zzno().zznp());

      try {
         this.zzass.onCreate();
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"onCreate", zzs.class.getSimpleName()});
      }

      super.onCreate();
   }

   public int onStartCommand(Intent var1, int var2, int var3) {
      try {
         return this.zzass.onStartCommand(var1, var2, var3);
      } catch (RemoteException var5) {
         zzarK.zzb(var5, "Unable to call %s on %s.", new Object[]{"onStartCommand", zzs.class.getSimpleName()});
         return 1;
      }
   }

   public IBinder onBind(Intent var1) {
      try {
         return this.zzass.onBind(var1);
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"onBind", zzs.class.getSimpleName()});
         return null;
      }
   }

   public void onDestroy() {
      try {
         this.zzass.onDestroy();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"onDestroy", zzs.class.getSimpleName()});
      }

      super.onDestroy();
   }
}
