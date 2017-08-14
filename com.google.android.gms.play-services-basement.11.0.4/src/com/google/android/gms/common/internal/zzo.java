package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

public final class zzo extends zze {
   // $FF: synthetic field
   private zzd zzaHe;

   @BinderThread
   public zzo(zzd var1, @Nullable int var2, Bundle var3) {
      this.zzaHe = var1;
      super(var1, var2, (Bundle)null);
   }

   protected final void zzj(ConnectionResult var1) {
      this.zzaHe.zzaGQ.zzf(var1);
      this.zzaHe.onConnectionFailed(var1);
   }

   protected final boolean zzrj() {
      this.zzaHe.zzaGQ.zzf(ConnectionResult.zzazX);
      return true;
   }
}
