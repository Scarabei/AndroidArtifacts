package com.google.android.gms.cast;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.api.Status;

final class zzs implements ServiceConnection {
   // $FF: synthetic field
   private String zzaoR;
   // $FF: synthetic field
   private CastDevice zzapK;
   // $FF: synthetic field
   private CastRemoteDisplayLocalService.Options zzapL;
   // $FF: synthetic field
   private CastRemoteDisplayLocalService.NotificationSettings zzapM;
   // $FF: synthetic field
   private Context zzapN;
   // $FF: synthetic field
   private CastRemoteDisplayLocalService.Callbacks zzapO;

   zzs(String var1, CastDevice var2, CastRemoteDisplayLocalService.Options var3, CastRemoteDisplayLocalService.NotificationSettings var4, Context var5, CastRemoteDisplayLocalService.Callbacks var6) {
      this.zzaoR = var1;
      this.zzapK = var2;
      this.zzapL = var3;
      this.zzapM = var4;
      this.zzapN = var5;
      this.zzapO = var6;
      super();
   }

   public final void onServiceConnected(ComponentName var1, IBinder var2) {
      CastRemoteDisplayLocalService var3 = ((CastRemoteDisplayLocalService.zza)var2).zzapJ;
      if (((CastRemoteDisplayLocalService.zza)var2).zzapJ == null || !CastRemoteDisplayLocalService.zza(var3, this.zzaoR, this.zzapK, this.zzapL, this.zzapM, this.zzapN, this, this.zzapO)) {
         CastRemoteDisplayLocalService.zznf().zzc("Connected but unable to get the service instance");
         this.zzapO.onRemoteDisplaySessionError(new Status(2200));
         CastRemoteDisplayLocalService.zzng().set(false);

         try {
            this.zzapN.unbindService(this);
            return;
         } catch (IllegalArgumentException var4) {
            CastRemoteDisplayLocalService.zznf().zzb("No need to unbind service, already unbound");
         }
      }

   }

   public final void onServiceDisconnected(ComponentName var1) {
      CastRemoteDisplayLocalService.zznf().zzb("onServiceDisconnected");
      this.zzapO.onRemoteDisplaySessionError(new Status(2201, "Service Disconnected"));
      CastRemoteDisplayLocalService.zzng().set(false);

      try {
         this.zzapN.unbindService(this);
      } catch (IllegalArgumentException var2) {
         CastRemoteDisplayLocalService.zznf().zzb("No need to unbind service, already unbound");
      }
   }
}
