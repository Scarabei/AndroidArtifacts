package com.google.android.gms.cast;

import android.content.Context;
import android.content.ServiceConnection;
import android.view.Display;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

final class zzw implements ResultCallback {
   // $FF: synthetic field
   private CastRemoteDisplayLocalService zzapJ;

   zzw(CastRemoteDisplayLocalService var1) {
      this.zzapJ = var1;
      super();
   }

   // $FF: synthetic method
   public final void onResult(Result var1) {
      CastRemoteDisplay.CastRemoteDisplaySessionResult var3 = (CastRemoteDisplay.CastRemoteDisplaySessionResult)var1;
      zzw var2 = this;
      if (!var3.getStatus().isSuccess()) {
         CastRemoteDisplayLocalService.zznf().zzc("Connection was not successful");
         CastRemoteDisplayLocalService.zzc(this.zzapJ);
      } else {
         CastRemoteDisplayLocalService.zznf().zzb("startRemoteDisplay successful");
         synchronized(CastRemoteDisplayLocalService.zznh()) {
            if (CastRemoteDisplayLocalService.zzni() == null) {
               CastRemoteDisplayLocalService.zznf().zzb("Remote Display started but session already cancelled");
               CastRemoteDisplayLocalService.zzc(var2.zzapJ);
               return;
            }
         }

         Display var4;
         if ((var4 = var3.getPresentationDisplay()) != null) {
            CastRemoteDisplayLocalService.zza(this.zzapJ, var4);
         } else {
            CastRemoteDisplayLocalService.zznf().zzc("Cast Remote Display session created without display");
         }

         CastRemoteDisplayLocalService.zzng().set(false);
         if (CastRemoteDisplayLocalService.zzd(this.zzapJ) != null && CastRemoteDisplayLocalService.zze(this.zzapJ) != null) {
            try {
               CastRemoteDisplayLocalService.zzd(var2.zzapJ).unbindService(CastRemoteDisplayLocalService.zze(var2.zzapJ));
            } catch (IllegalArgumentException var6) {
               CastRemoteDisplayLocalService.zznf().zzb("No need to unbind service, already unbound");
            }

            CastRemoteDisplayLocalService.zza(this.zzapJ, (ServiceConnection)null);
            CastRemoteDisplayLocalService.zza(this.zzapJ, (Context)null);
         }

      }
   }
}
