package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public final class zzk extends zzav {
   private zzd zzaHg;
   private final int zzaHh;

   public zzk(@NonNull zzd var1, int var2) {
      this.zzaHg = var1;
      this.zzaHh = var2;
   }

   @BinderThread
   public final void zza(int var1, @Nullable Bundle var2) {
      Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
   }

   @BinderThread
   public final void zza(int var1, @NonNull IBinder var2, @Nullable Bundle var3) {
      zzbo.zzb(this.zzaHg, "onPostInitComplete can be called only once per call to getRemoteService");
      this.zzaHg.zza(var1, var2, var3, this.zzaHh);
      this.zzaHg = null;
   }
}
