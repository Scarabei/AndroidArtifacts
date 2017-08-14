package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.internal.zzbo;
import java.util.concurrent.TimeUnit;

public abstract class zzen extends PendingResult {
   private final PendingResult zzrJ;

   protected zzen(PendingResult var1) {
      this.zzrJ = (PendingResult)zzbo.zzu(var1);
   }

   protected abstract Result zza(Result var1);

   @NonNull
   public Result await() {
      return this.zza(this.zzrJ.await());
   }

   @NonNull
   public Result await(long var1, @NonNull TimeUnit var3) {
      return this.zza(this.zzrJ.await(var1, var3));
   }

   public void cancel() {
      this.zzrJ.cancel();
   }

   public boolean isCanceled() {
      return this.zzrJ.isCanceled();
   }

   public void setResultCallback(@NonNull ResultCallback var1) {
      this.zzrJ.setResultCallback(new zzeo(this, var1));
   }

   public void setResultCallback(@NonNull ResultCallback var1, long var2, @NonNull TimeUnit var4) {
      this.zzrJ.setResultCallback(new zzep(this, var1), var2, var4);
   }

   public final void zza(@NonNull zza var1) {
      this.zzrJ.zza(var1);
   }
}
