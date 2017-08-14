package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IInterface;
import android.support.annotation.BinderThread;
import com.google.android.gms.common.ConnectionResult;

abstract class zze extends zzi {
   private int statusCode;
   private Bundle zzaHd;
   // $FF: synthetic field
   private zzd zzaHe;

   @BinderThread
   protected zze(zzd var1, int var2, Bundle var3) {
      this.zzaHe = var1;
      super(var1, true);
      this.statusCode = var2;
      this.zzaHd = var3;
   }

   protected abstract boolean zzrj();

   protected abstract void zzj(ConnectionResult var1);

   // $FF: synthetic method
   protected final void zzs(Object var1) {
      Boolean var3 = (Boolean)var1;
      if (var3 == null) {
         zzd.zza((zzd)this.zzaHe, 1, (IInterface)null);
      } else {
         switch(this.statusCode) {
         case 0:
            if (!this.zzrj()) {
               zzd.zza((zzd)this.zzaHe, 1, (IInterface)null);
               this.zzj(new ConnectionResult(8, (PendingIntent)null));
               return;
            }
            break;
         case 10:
            zzd.zza((zzd)this.zzaHe, 1, (IInterface)null);
            throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
         default:
            zzd.zza((zzd)this.zzaHe, 1, (IInterface)null);
            PendingIntent var4 = null;
            if (this.zzaHd != null) {
               var4 = (PendingIntent)this.zzaHd.getParcelable("pendingIntent");
            }

            this.zzj(new ConnectionResult(this.statusCode, var4));
         }

      }
   }
}
