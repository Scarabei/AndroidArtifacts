package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

public final class zzn extends zze {
   private IBinder zzaHi;
   // $FF: synthetic field
   private zzd zzaHe;

   @BinderThread
   public zzn(zzd var1, int var2, IBinder var3, Bundle var4) {
      this.zzaHe = var1;
      super(var1, var2, var4);
      this.zzaHi = var3;
   }

   protected final void zzj(ConnectionResult var1) {
      if (zzd.zzg(this.zzaHe) != null) {
         zzd.zzg(this.zzaHe).onConnectionFailed(var1);
      }

      this.zzaHe.onConnectionFailed(var1);
   }

   protected final boolean zzrj() {
      String var1;
      try {
         var1 = this.zzaHi.getInterfaceDescriptor();
      } catch (RemoteException var4) {
         Log.w("GmsClient", "service probably died");
         return false;
      }

      if (!this.zzaHe.zzdc().equals(var1)) {
         String var5 = String.valueOf(this.zzaHe.zzdc());
         Log.e("GmsClient", (new StringBuilder(34 + String.valueOf(var5).length() + String.valueOf(var1).length())).append("service descriptor mismatch: ").append(var5).append(" vs. ").append(var1).toString());
         return false;
      } else {
         IInterface var2;
         if ((var2 = this.zzaHe.zzd(this.zzaHi)) == null || !zzd.zza(this.zzaHe, 2, 4, var2) && !zzd.zza(this.zzaHe, 3, 4, var2)) {
            return false;
         } else {
            zzd.zza((zzd)this.zzaHe, (ConnectionResult)null);
            Bundle var3 = this.zzaHe.zzoC();
            if (zzd.zze(this.zzaHe) != null) {
               zzd.zze(this.zzaHe).onConnected(var3);
            }

            return true;
         }
      }
   }
}
