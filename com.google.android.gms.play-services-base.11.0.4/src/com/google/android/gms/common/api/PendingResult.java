package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.TimeUnit;

public abstract class PendingResult {
   @NonNull
   public abstract Result await();

   @NonNull
   public abstract Result await(long var1, @NonNull TimeUnit var3);

   public abstract void cancel();

   public abstract boolean isCanceled();

   public abstract void setResultCallback(@NonNull ResultCallback var1);

   public abstract void setResultCallback(@NonNull ResultCallback var1, long var2, @NonNull TimeUnit var4);

   public void zza(@NonNull PendingResult.zza var1) {
      throw new UnsupportedOperationException();
   }

   @NonNull
   public TransformedResult then(@NonNull ResultTransform var1) {
      throw new UnsupportedOperationException();
   }

   @Nullable
   public Integer zzpo() {
      throw new UnsupportedOperationException();
   }

   public interface zza {
      void zzo(Status var1);
   }
}
