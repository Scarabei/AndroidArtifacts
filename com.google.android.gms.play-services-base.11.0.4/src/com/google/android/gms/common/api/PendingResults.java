package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbbe;
import com.google.android.gms.internal.zzbec;
import com.google.android.gms.internal.zzben;

public final class PendingResults {
   public static PendingResult immediatePendingResult(Status var0) {
      zzbo.zzb(var0, "Result must not be null");
      zzben var1;
      (var1 = new zzben(Looper.getMainLooper())).setResult(var0);
      return var1;
   }

   public static PendingResult zza(Status var0, GoogleApiClient var1) {
      zzbo.zzb(var0, "Result must not be null");
      zzben var2;
      (var2 = new zzben(var1)).setResult(var0);
      return var2;
   }

   public static PendingResult zza(Result var0, GoogleApiClient var1) {
      zzbo.zzb(var0, "Result must not be null");
      zzbo.zzb(!var0.getStatus().isSuccess(), "Status code must not be SUCCESS");
      PendingResults.zzb var2;
      (var2 = new PendingResults.zzb(var1, var0)).setResult(var0);
      return var2;
   }

   public static OptionalPendingResult immediatePendingResult(Result var0) {
      zzbo.zzb(var0, "Result must not be null");
      PendingResults.zzc var1;
      (var1 = new PendingResults.zzc((GoogleApiClient)null)).setResult(var0);
      return new zzbec(var1);
   }

   public static OptionalPendingResult zzb(Result var0, GoogleApiClient var1) {
      zzbo.zzb(var0, "Result must not be null");
      PendingResults.zzc var2;
      (var2 = new PendingResults.zzc(var1)).setResult(var0);
      return new zzbec(var2);
   }

   public static PendingResult canceledPendingResult() {
      zzben var0;
      (var0 = new zzben(Looper.getMainLooper())).cancel();
      return var0;
   }

   public static PendingResult canceledPendingResult(Result var0) {
      zzbo.zzb(var0, "Result must not be null");
      zzbo.zzb(var0.getStatus().getStatusCode() == 16, "Status code must be CommonStatusCodes.CANCELED");
      PendingResults.zza var1;
      (var1 = new PendingResults.zza(var0)).cancel();
      return var1;
   }

   static final class zza extends zzbbe {
      private final Result zzaBi;

      public zza(Result var1) {
         super(Looper.getMainLooper());
         this.zzaBi = var1;
      }

      protected final Result zzb(Status var1) {
         if (var1.getStatusCode() != this.zzaBi.getStatus().getStatusCode()) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
         } else {
            return this.zzaBi;
         }
      }
   }

   static final class zzb extends zzbbe {
      private final Result zzaBj;

      public zzb(GoogleApiClient var1, Result var2) {
         super(var1);
         this.zzaBj = var2;
      }

      protected final Result zzb(Status var1) {
         return this.zzaBj;
      }
   }

   static final class zzc extends zzbbe {
      public zzc(GoogleApiClient var1) {
         super(var1);
      }

      protected final Result zzb(Status var1) {
         throw new UnsupportedOperationException("Creating failed results is not supported");
      }
   }
}
